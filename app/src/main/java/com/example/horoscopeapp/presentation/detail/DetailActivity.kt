package com.example.horoscopeapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscopeapp.databinding.ActivityDetailBinding
import com.example.horoscopeapp.presentation.detail.model.DetailUiState
import com.example.horoscopeapp.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context) = Intent(context, DetailActivity::class.java)
    }

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        viewModel.getHoroscope()
    }

    private fun initUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is DetailUiState.Error -> {
                            binding.loading.isVisible = false
                        }
                        DetailUiState.Loading -> {
                            binding.loading.isVisible = true
                        }
                        is DetailUiState.Success -> {
                            binding.loading.isVisible = false
                            Toast.makeText(this@DetailActivity, "Ha Funcionado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
