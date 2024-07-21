package com.fintech.demo.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.fintech.demo.framework.db.entity.CardDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FintechDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(items: List<CardDetailsEntity>)

    @Query("SELECT COUNT(*) FROM Card_Details")
    suspend fun getCount(): Int

    @Query("SELECT * from Card_Details")
    fun getCardDetailsAsFlow(): Flow<List<CardDetailsEntity>>
}