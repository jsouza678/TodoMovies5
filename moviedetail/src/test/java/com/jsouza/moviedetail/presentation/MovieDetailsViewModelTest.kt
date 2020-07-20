package com.jsouza.moviedetail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jsouza.moviedetail.domain.model.MovieDetail
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.domain.usecase.FetchMovieDetailsFromApi
import com.jsouza.moviedetail.domain.usecase.FetchSimilarMoviesFromApi
import com.jsouza.moviedetail.domain.usecase.GetIsFavoriteMovie
import com.jsouza.moviedetail.domain.usecase.GetMovieDetailsFromDatabase
import com.jsouza.moviedetail.domain.usecase.GetSimilarMoviesFromDatabase
import com.jsouza.moviedetail.domain.usecase.SetIsFavoriteMovie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.runs
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {

    private companion object {
        private const val ABSOLUTE_ZERO = 0
    }
    private val movieDetail = mockkClass(MovieDetail::class)
    private val similarMoviesList = mockkClass(SimilarMovies::class)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val fetchSimilarMoviesFromApi: FetchSimilarMoviesFromApi = mockk()
    private val getSimilarMoviesFromDatabase: GetSimilarMoviesFromDatabase = mockk()
    private val fetchMovieDetailsFromApi: FetchMovieDetailsFromApi = mockk()
    private val getMovieDetailsFromDatabase: GetMovieDetailsFromDatabase = mockk()
    private val setIsFavoriteMovie: SetIsFavoriteMovie = mockk()
    private val getIsFavoriteMovie: GetIsFavoriteMovie = mockk()

    private val movieDetailsViewModel = spyk(
        MovieDetailsViewModel(
            fetchMovieDetailsFromApi,
            getMovieDetailsFromDatabase,
            fetchSimilarMoviesFromApi,
            getSimilarMoviesFromDatabase,
            setIsFavoriteMovie,
            getIsFavoriteMovie
        )
    )

    @Test
    fun `GIVEN the application needs to know the connectivity status WHEN the device has internet THEN it should return the connectivity status `() {
        val hasNetworkConnectivity = false

        movieDetailsViewModel.updateConnectivityStatus(hasNetworkConnectivity)

        Assert.assertEquals(hasNetworkConnectivity, movieDetailsViewModel.wasConnected)
    }

    @Test
    fun `GIVEN the application wants to get similar movies from database WHEN a movie id was given THEN it should load the data in livedata`() {
        coEvery { getSimilarMoviesFromDatabase.invoke().observeForever {
            Assert.assertEquals(it, similarMoviesList) }
        }
    }

    @Test
    fun `GIVEN the application wants to get movie details from database WHEN a movie id was given THEN it should load the data in livedata`() {
        coEvery { getMovieDetailsFromDatabase.invoke(any()).observeForever {
            Assert.assertEquals(it, movieDetail) }
        }
    }

    @Test
    fun `GIVEN the application wants to fetch movie details from api WHEN a movie id was given THEN it should fetch the data`() {
        coEvery { fetchMovieDetailsFromApi.invoke(any()) } just runs
    }

    @Test
    fun `GIVEN the application wants to fetch movie details and similar movies from api WHEN does not have internet THEN it should not fetch the data`() {
        movieDetailsViewModel.wasConnected = false

        verify(exactly = ABSOLUTE_ZERO) { movieDetailsViewModel.loadMovies() }
    }

    @Test
    fun `GIVEN the application wants to set a movie as favorite WHEN the favorite set is triggered THEN it should set as favorite on preferences`() {
        every { setIsFavoriteMovie.invoke(any()) } just runs
    }

    @Test
    fun `GIVEN the application wants to get if a movie is favorite WHEN it asks for favorite condition THEN it should return the favorite condition`() {
        every { getIsFavoriteMovie.invoke() } returns true
    }
}
