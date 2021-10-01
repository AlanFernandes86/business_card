package br.com.interbootcamp.businesscard

import android.app.Application
import br.com.interbootcamp.businesscard.data.local.room.AppRoomDatabase
import br.com.interbootcamp.businesscard.data.repository.BusinessCardRepository

class App : Application() {
    val database by lazy { AppRoomDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessCardDao()) }
}