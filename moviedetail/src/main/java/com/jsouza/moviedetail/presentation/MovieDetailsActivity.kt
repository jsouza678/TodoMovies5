package com.jsouza.moviedetail.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jsouza.moviedetail.R
import com.jsouza.moviedetail.databinding.ActivityMovieDetailsBinding
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.extensions.loadBackdropImage
import com.jsouza.moviedetail.presentation.adapter.MovieDetailsAdapter
import com.jsouza.moviedetail.utils.dateFormat
import kotlinx.android.synthetic.main.activity_movie_details.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val adapter =
        MovieDetailsAdapter()
    private val viewModel by viewModel<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        setupRecyclerView()
        setupLikeButton()
        initObserver(viewModel)

        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        binding.similarRecyclerViewDetailActivity.adapter = adapter
    }

    private fun setupLikeButton() {
        binding.likeMovieImageViewDetailActivity.setOnClickListener {
            it.like_movie_image_view_detail_activity.setImageResource(R.drawable.ic_favorite_filled)
        }
    }

    private fun initObserver(viewModel: MovieDetailsViewModel) {
        viewModel.apply {
            this.returnSimilarMoviesOnLiveData().observe(this@MovieDetailsActivity, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
            this.returnMovieDetailOnLiveData().observe(this@MovieDetailsActivity, Observer {
                it?.let {
                    fillDetails(it)
                }
            })
        }
    }

    private fun fillDetails(movieDetail: MovieDetail) {
        binding.posterImageViewDetailActivity.loadBackdropImage(movieDetail.posterImage)
        binding.titleMovieTextViewDetailActivity.text = movieDetail.title
        binding.movieDateTextViewDetailActivity.text = movieDetail.releaseDate?.let { dateFormat(it) }
        val duration = "${movieDetail.runtime} min"
        binding.movieDurationTextViewDetailActivity.text = duration
        binding.genreMovieTextViewDetailActivity.text = movieDetail.genres
    }
}
