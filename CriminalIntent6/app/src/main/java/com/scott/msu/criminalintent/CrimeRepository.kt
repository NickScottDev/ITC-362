package com.scott.msu.criminalintent

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.scott.msu.criminalintent.database.CrimeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.util.UUID

private const val TAG = "CrimeRepository"
private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(
    context:Context,
    private val coroutineScope: CoroutineScope = GlobalScope
    ){

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()

    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)

    suspend fun updateCrime(crime:Crime){
        Log.d(TAG, "Updating crime with ID ${crime.id}")
        coroutineScope.launch{
            database.crimeDao().updateCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE
                ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

}