package com.souza.todomovies5.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.souza.todomovies5.R

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val recyclerView = findViewById<RecyclerView>(R.id.similar_recycler_view_detail_activity)

        recyclerView.adapter = MovieDetailsAdapter()
    }
}
