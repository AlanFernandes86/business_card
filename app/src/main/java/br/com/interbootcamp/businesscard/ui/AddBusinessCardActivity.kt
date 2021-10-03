package br.com.interbootcamp.businesscard.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.interbootcamp.businesscard.App
import br.com.interbootcamp.businesscard.R
import br.com.interbootcamp.businesscard.data.local.room.entity.BusinessCard
import br.com.interbootcamp.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModel
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModelFactory
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener


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
                company = binding.tilAddCardCompany.editText?.text.toString()
            )
            if(mViewModel.setBusinessCard(businessCard)){
                mViewModel.insert()
                finish()
            }
            else {
                Toast.makeText(this, getString(R.string.alert_select_color), Toast.LENGTH_SHORT).show()
            }
        }
        binding.tilAddCardColor.setEndIconOnClickListener {
            val colorPicker = ColorPicker(this)
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    mViewModel.setCardBackgroundColor(color)
                    binding.tilAddCardColor.editText?.setText(color.toString())
                }
                override fun onCancel() {
                    Toast.makeText(application.baseContext, getString(R.string.alert_select_color), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}