package com.example.adapterlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapterlista.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : StringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerView()
    }

    private fun createRecyclerView() {
        val num = Random.nextInt(5, 9)
        var listaPc  = mutableListOf("PC - 0")

        for (i in 1..num){
            var nom = Random.nextInt(0, 100)
            listaPc.add("PC - $nom")
        }

        adapter = StringAdapter(listaPc)

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = adapter
    }
}