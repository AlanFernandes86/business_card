package br.com.interbootcamp.businesscard.ui.viewmodel

import androidx.lifecycle.*
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import br.com.interbootcamp.businesscard.data.repository.BusinessCardRepository
import java.lang.IllegalArgumentException
import java.util.*

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    private  var mBusinessCard = BusinessCard()

    fun insert() {
        businessCardRepository.insert(mBusinessCard)
    }

    fun getAll(): LiveData<List<BusinessCard>> {
        return businessCardRepository.getAll().asLiveData()
    }

    fun setBusinessCard(businessCard: BusinessCard) : Boolean {
        var validated = false
        if (mBusinessCard.backgroundColor != 0){
            mBusinessCard.apply {
                name = businessCard.name
                phone = businessCard.phone
                email = businessCard.email
                company = businessCard.company
            }
            validated = true
        }
        return validated
    }

    fun setCardBackgroundColor(color: Int) {
            mBusinessCard.backgroundColor = color
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