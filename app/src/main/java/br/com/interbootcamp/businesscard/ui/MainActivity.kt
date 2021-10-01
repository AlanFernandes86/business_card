package br.com.interbootcamp.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.interbootcamp.businesscard.App
import br.com.interbootcamp.businesscard.databinding.ActivityMainBinding
import br.com.interbootcamp.businesscard.ui.adapter.BusinessCardAdapter
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModel
import br.com.interbootcamp.businesscard.ui.viewmodel.MainViewModelFactory
import br.com.interbootcamp.businesscard.utils.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { BusinessCardAdapter() }
    private val mViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.adapter = adapter

        initializeObservers()
        initializeListeners()
    }

    private fun initializeObservers() {
        mViewModel.getAll().observe(this, {
            adapter.submitList(it)
        })
    }

    private fun initializeListeners() {
        binding.fabMain.setOnClickListener {
            val intent = Intent(this, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {
            Image.share(this, it)
        }
    }


}