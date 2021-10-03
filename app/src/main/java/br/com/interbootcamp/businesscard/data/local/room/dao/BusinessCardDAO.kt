package br.com.interbootcamp.businesscard.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessCardDAO {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): Flow<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessCard: BusinessCard)

}