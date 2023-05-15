package com.scott.msu.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scott.msu.criminalintent.databinding.ListItemCrimeBinding
import com.scott.msu.criminalintent.databinding.ListItemCrimePoliceBinding

class CrimeHolder (
    val binding: ListItemCrimeBinding

): RecyclerView.ViewHolder(binding.root){
    fun bind(crime:Crime){
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener{

            Toast.makeText(

                binding.root.context,
                "${crime.title} clicked",
                Toast.LENGTH_SHORT
            ).show()

        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        }
        else {
            View.GONE
        }
    }
}


//class CrimeListAdapter(private val crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ListItemCrimeBinding.inflate(inflater,parent,false)
//        return CrimeHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
//
//        val crime = crimes[position]
//        holder.bind(crime)
//    }
//
//    override fun getItemCount() = crimes.size
//}

class CrimeListAdapter(private val crimes: List<Crime>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_POLICE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_POLICE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                CrimePoliceHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> {
                holder.bind(crime)
            }
            is CrimePoliceHolder -> {
                holder.bind(crime)
            }
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice) VIEW_TYPE_POLICE else VIEW_TYPE_NORMAL
    }

    class CrimeHolder(val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            binding.root.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "${crime.title} clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.crimeSolved.visibility = if (crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    class CrimePoliceHolder(val binding: ListItemCrimePoliceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()
            }
        }
    }

