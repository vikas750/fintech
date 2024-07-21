package com.fintech.demo.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fintech.demo.framework.db.dao.FintechDao
import com.fintech.demo.framework.db.entity.CardDetailsEntity

@Database(
    version = 1, entities = [CardDetailsEntity::class]
)
abstract class FintechAppDatabase : RoomDatabase() {

    abstract fun taskHistoryDao(): FintechDao

    companion object {
        private var INSTANCE: FintechAppDatabase? = null
        private val mLock = Any()

        fun getInstance(context: Context): FintechAppDatabase = INSTANCE ?: synchronized(mLock) {
            Room.databaseBuilder(
                context.applicationContext, FintechAppDatabase::class.java, "com.fintech.demo"
            ).build().apply { INSTANCE = this }
        }
    }
}