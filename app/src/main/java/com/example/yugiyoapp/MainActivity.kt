package com.example.yugiyoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiyoapp.adapter.RecyclerViewAdapter
import com.example.yugiyoapp.data.RestoranData
import com.example.yugiyoapp.model.Restoran

class MainActivity : AppCompatActivity() {

    private  lateinit var restoranAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onRestart() {
        super.onRestart()
        restoranAdapter.notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    private fun configureRecyclerView() {
        val jumlahText = findViewById<TextView>(R.id.restoran_jumlah)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_list)

        restoranAdapter = RecyclerViewAdapter(RestoranData.restorans, object : RecyclerViewAdapter.OnAdapterListener {
            override fun onClick(result: Restoran) {
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("intent_result", result)
                )
            }
        })

        jumlahText.text = "("+restoranAdapter.itemCount.toString()+")"

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = restoranAdapter
        }
    }
}