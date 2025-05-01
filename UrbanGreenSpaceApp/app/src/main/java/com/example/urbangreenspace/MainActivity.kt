package com.example.urbangreenspace

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.urbangreenspace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView adapter
        val adapter = GreenSpaceAdapter(emptyList()) { greenSpace ->
            // TODO: Handle item click, e.g., show details
        }
        binding.greenSpaceList.adapter = adapter

        // Observe ViewModel data
        viewModel.greenSpaces.observe(this) { greenSpaces ->
            adapter.updateList(greenSpaces)
        }

        // TODO: Setup map and search functionality
    }
}
