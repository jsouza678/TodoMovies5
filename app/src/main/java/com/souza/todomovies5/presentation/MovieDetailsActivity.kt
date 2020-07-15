package com.souza.todomovies5.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.souza.todomovies5.R

class MovieDetailsActivity : AppCompatActivity() {

    private val adapter = MovieDetailsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.similar_recycler_view_detail_activity)

        recyclerView.adapter = adapter

        initObserver(viewModel)
    }

    private fun initObserver(viewModel: MovieDetailsViewModel) {
        viewModel.apply {
            this.similarList.observe(this@MovieDetailsActivity, Observer {
                adapter.submitList(it)
            })
        }
    }
}
