package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.R
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.databinding.ActivityMainBinding
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.adapter.CharacterAdapter
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.presenter.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter:CharacterAdapter
    private val characterViewModel:CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAdapter = CharacterAdapter()

        binding.recyclerView.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(this@MainActivity,1)
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            characterViewModel.state.collect{
                when{
                    it.loading->{
                        binding.progressBar.visibility =View.VISIBLE
                    }
                    it.fail.isNotBlank()->{
                        binding.progressBar.visibility =View.GONE
                        Toast.makeText(this@MainActivity, it.fail, Toast.LENGTH_SHORT).show()
                    }
                    it.ok.isNotEmpty()->{
                        binding.progressBar.visibility =View.GONE
                        characterAdapter.submitList(it.ok)
                    }
                }
            }
        }

    }
}