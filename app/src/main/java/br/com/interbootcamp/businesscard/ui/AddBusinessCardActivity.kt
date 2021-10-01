package br.com.interbootcamp.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.interbootcamp.businesscard.App
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import br.com.interbootcamp.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModel
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModelFactory

class AddBusinessCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBusinessCardBinding
    private val mViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeListeners()
    }

    private fun initializeListeners() {
        binding.btnCloseAddCard.setOnClickListener {
            finish()
        }
        binding.btnSaveAddCard.setOnClickListener {
            val businessCard = BusinessCard(
                    name = binding.tilAddCardName.editText?.text.toString(),
                    phone = binding.tilAddCardPhone.editText?.text.toString(),
                    email = binding.tilAddCardEmail.editText?.text.toString(),
                    company = binding.tilAddCardCompany.editText?.text.toString(),
                    backgroundColor = binding.tilAddCardColor.editText?.text.toString()
            )
            mViewModel.insert(businessCard)
            finish()
        }
    }

}