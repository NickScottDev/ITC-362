package com.scott.msu.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.scott.msu.criminalintent.databinding.ListItemCrimeBinding
import com.scott.msu.criminalintent.databinding.ListItemCrimePoliceButtonBinding

class CrimeHolder(private val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeHolderPolice(private val binding: ListItemCrimePoliceButtonBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime){
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        if (crime.requiresPolice) {
            binding.contactPoliceButton.visibility = View.VISIBLE
            binding.contactPoliceButton.setOnClickListener {
                Toast.makeText(binding.root.context, "Contact Police clicked!", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.contactPoliceButton.visibility = View.GONE
        }

        binding.root.setOnClickListener{
            Toast.makeText(binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT).show()
        }
    }
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_POLICE -> {
                val binding = ListItemCrimePoliceButtonBinding.inflate(inflater, parent, false)
                CrimeHolderPolice(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is CrimeHolderPolice -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        return when {
            crimes[position].requiresPolice -> VIEW_TYPE_POLICE
            else -> VIEW_TYPE_NORMAL
        }
    }

    companion object {
        private const val VIEW_TYPE_NORMAL = 1
        private const val VIEW_TYPE_POLICE = 2
    }
}


