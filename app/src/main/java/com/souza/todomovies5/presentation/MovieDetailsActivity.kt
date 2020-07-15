package com.souza.todomovies5.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.souza.todomovies5.R
import com.souza.todomovies5.data.todomovies.remote.response.MovieDetailResponse
import com.souza.todomovies5.databinding.ActivityMovieDetailsBinding
import com.souza.todomovies5.extensions.loadBackdropImage
import com.souza.todomovies5.utils.dateFormat
import kotlinx.android.synthetic.main.activity_movie_details.view.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val adapter = MovieDetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        binding.similarRecyclerViewDetailActivity.adapter = adapter
        binding.likeMovieImageViewDetailActivity.setOnClickListener {
            it.like_movie_image_view_detail_activity.setImageResource(R.drawable.ic_favorite_filled)
        }

        initObserver(viewModel)

        setContentView(binding.root)
    }

    private fun initObserver(viewModel: MovieDetailsViewModel) {
        viewModel.apply {
            this.similarList.observe(this@MovieDetailsActivity, Observer {
                adapter.submitList(it)
            })
            this.movieDetail.observe(this@MovieDetailsActivity, Observer {
                fillDetails(it)
            })
        }
    }

    private fun fillDetails(movieDetail: MovieDetailResponse) {
        binding.posterImageViewDetailActivity.loadBackdropImage(movieDetail.getImageUrl())
        binding.titleMovieTextViewDetailActivity.text = movieDetail.title
        binding.movieDateTextViewDetailActivity.text = movieDetail.releaseDate?.let { dateFormat(it) }
        val duration = "${movieDetail.runtime.toString()} min"
        binding.movieDurationTextViewDetailActivity.text = duration
        binding.genreMovieTextViewDetailActivity.text = movieDetail.getGenres()
    }
}
