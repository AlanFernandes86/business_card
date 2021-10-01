package br.com.interbootcamp.businesscard.data.repository

import br.com.interbootcamp.businesscard.data.local.room.dao.BusinessCardDAO
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDAO) {


    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO){
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()

}