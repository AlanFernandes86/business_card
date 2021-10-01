package br.com.interbootcamp.businesscard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import br.com.interbootcamp.businesscard.data.repository.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {


    fun insert(businessCard: BusinessCard) {
        businessCardRepository.insert(businessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll().asLiveData()
    }

}

class MainViewModelFactory(private val repository: BusinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}