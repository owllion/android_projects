package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //textView.text = Datasource().loadAffirmations().size.toString()
        //留存，紀錄正常呼叫list items並計算length

        //Initialize data(list of affirmations)
        val myDataset = Datasource().loadAffirmations()

        // find a reference to the RecyclerView(tag) within the layout.
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = ItemAdapter(this, myDataset)
        //tell the recyclerView to use the ItemAdapter class
        //ItemAdapter first parameter "this" is
        //the context (this) of this (main)activity

        recyclerView.setHasFixedSize(true)
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }
}