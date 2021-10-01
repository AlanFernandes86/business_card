package br.com.interbootcamp.businesscard.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.interbootcamp.businesscard.data.local.room.dao.BusinessCardDAO
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard

@Database(entities = [BusinessCard::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun businessCardDao() : BusinessCardDAO

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        fun getDatabase(context: Context) : AppRoomDatabase {
            return INSTANCE ?: synchronized(this ){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "bussinescard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}