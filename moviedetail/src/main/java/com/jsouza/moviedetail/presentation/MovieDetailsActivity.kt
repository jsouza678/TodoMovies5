package com.jsouza.moviedetail.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jsouza.connectivity.Connectivity
import com.jsouza.moviedetail.R
import com.jsouza.moviedetail.databinding.ActivityMovieDetailsBinding
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.extensions.loadBackdropImage
import com.jsouza.moviedetail.presentation.adapter.SimilarMoviesAdapter
import com.jsouza.moviedetail.utils.Constants.Companion.ABSOLUTE_ZERO
import com.jsouza.moviedetail.utils.dateFormat
import kotlin.math.roundToInt
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var connectivitySnackbar: Snackbar
    private lateinit var checkConnectivity: Connectivity
    private var hasNetworkConnectivity = true
    private lateinit var binding: ActivityMovieDetailsBinding
    private val adapter by inject<SimilarMoviesAdapter>()
    private val viewModel by viewModel<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        viewModel.loadMovies()
        setupSwipeRefresh()
        initConnectivityCallback()
        initConnectivityObserver()
        setupRecyclerView()
        isFavoriteMovie()
        setupFavoriteButton()
        initMovieDataObservers()

        setContentView(binding.root)
        initConnectivitySnackbar()
    }

    private fun setupSwipeRefresh() {
        binding.movieDetailsSwipeLayoutDetailActivity.setOnRefreshListener {
            viewModel.refreshMovies()
            binding.movieDetailsSwipeLayoutDetailActivity.isRefreshing = false
        }
    }

    private fun initConnectivityObserver() {
        checkConnectivity.observe(this@MovieDetailsActivity,
            Observer { hasNetworkConnectivity ->
                this.hasNetworkConnectivity = hasNetworkConnectivity

                viewModel.updateConnectivityStatus(hasNetworkConnectivity)
            })

        viewModel.apply {
            showConnectivityOnSnackbar().observe(this@MovieDetailsActivity,
                Observer {
                    this@MovieDetailsActivity.showConnectivityOnSnackbar()
                })

            showConnectivityOffSnackbar().observe(this@MovieDetailsActivity,
                Observer {
                    this@MovieDetailsActivity.showConnectivityOffSnackbar()
                })
        }
    }

    private fun initMovieDataObservers() {
        viewModel.apply {
            this.showSimilarMoviesOnLiveData().observe(this@MovieDetailsActivity,
                Observer {
                    it?.let {
                        adapter.submitList(it)
                    }
                })
            this.returnMovieDetailOnLiveData().observe(this@MovieDetailsActivity,
                Observer {
                    it?.let {
                        fillDetails(it)
                    }
                })
        }
    }

    private fun fillDetails(
        movieDetail: MovieDetail
    ) {
        binding.popularityProgressBarDetailActivity.visibility = View.VISIBLE
        binding.dotSeparatorTextViewMovieDetailActivity.visibility = View.VISIBLE
        binding.favoriteMovieToggleButtonDetailActivity.visibility = View.VISIBLE

        val likesCount = "${movieDetail.voteCount} ${getString(R.string.votes_count_label)}"
        binding.likesCountMovieTextViewDetailActivity.text = likesCount

        val popularityToDisplayOnProgressBar = movieDetail.popularity?.roundToInt()
        binding.popularityProgressBarDetailActivity.progress =
            popularityToDisplayOnProgressBar ?: ABSOLUTE_ZERO

        binding.posterImageViewDetailActivity.loadBackdropImage(movieDetail.posterImage)
        binding.titleMovieTextViewDetailActivity.text = movieDetail.title
        binding.movieDateTextViewDetailActivity.text = movieDetail.releaseDate?.let {
            dateFormat(it)
        }
        val duration = "${movieDetail.runtime} ${getString(R.string.minutes_duration_label)}"
        binding.movieDurationTextViewDetailActivity.text = duration

        binding.genreMovieTextViewDetailActivity.text = movieDetail.genres
    }

    private fun initConnectivityCallback() {
        checkConnectivity = Connectivity(application)
    }

    private fun initConnectivitySnackbar() {
        connectivitySnackbar =
            Snackbar.make(
                binding.movieDetailsSwipeLayoutDetailActivity,
                getString(R.string.snackbar_message_internet_back),
                Snackbar.LENGTH_SHORT
            )
    }

    private fun showConnectivityOnSnackbar() {
        connectivitySnackbar
            .view.setBackgroundColor(
                ContextCompat
                    .getColor(this, R.color.success_color)
            )
        connectivitySnackbar.setText(getString(R.string.snackbar_message_internet_back))
        connectivitySnackbar.show()
    }

    private fun showConnectivityOffSnackbar() {
        connectivitySnackbar
            .view
            .setBackgroundColor(
                ContextCompat
                    .getColor(this, R.color.error_color)
            )
        connectivitySnackbar.setText(getString(R.string.snackbar_internet_off))
        connectivitySnackbar.show()
    }

    private fun setupRecyclerView() {
        binding.similarRecyclerViewDetailActivity.adapter = adapter
    }

    private fun isFavoriteMovie() {
        binding.favoriteMovieToggleButtonDetailActivity.isChecked = viewModel
            .getIsFavoriteMovieFromCache()
    }

    private fun setupFavoriteButton() {
        binding.favoriteMovieToggleButtonDetailActivity
            .setOnCheckedChangeListener { _, isFavorite ->
                when (isFavorite) {
                    true -> viewModel.setMovieAsFavoriteOnCache(true)
                    false -> viewModel.setMovieAsFavoriteOnCache(false)
                }
            }
    }
}
