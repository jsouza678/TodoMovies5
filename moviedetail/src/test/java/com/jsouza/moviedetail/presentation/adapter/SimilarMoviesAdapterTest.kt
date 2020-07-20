package com.jsouza.moviedetail.presentation.adapter

import android.view.View
import android.view.ViewGroup
import com.jsouza.moviedetail.domain.model.SimilarMovies
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.spyk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class SimilarMoviesAdapterTest {

    private val similarMoviesAdapter: SimilarMoviesAdapter = mockk()
    private val view: View = mockk()
    private val viewHolder = spyk(SimilarMoviesAdapter.ViewHolder(view))
    private val listOfSimilarMovies = mutableListOf<SimilarMovies>()
    private val viewGroup: ViewGroup = mockk()

    private companion object {
        private const val VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0
        private const val VIEW_TYPE_OBJECT_VIEW = 1
        private val SIMILAR_MOVIE = mockkClass(SimilarMovies::class)
        private const val NUMBER_ONE = 1
        private const val ABSOLUTE_ZERO = 0
    }

    @Before
    fun setUp() {
        listOfSimilarMovies.add(SIMILAR_MOVIE)
        listOfSimilarMovies.add(SIMILAR_MOVIE)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN the adapter created WHEN the list is empty THEN it return an empty view`() {
        every { similarMoviesAdapter.itemCount == ABSOLUTE_ZERO } returns (viewHolder.itemViewType == VIEW_TYPE_EMPTY_LIST_PLACEHOLDER)
    }

    @Test
    fun `GIVEN the adapter created WHEN the list is empty THEN it return a default view`() {
        every { similarMoviesAdapter.itemCount == ABSOLUTE_ZERO } returns (viewHolder.itemViewType == VIEW_TYPE_OBJECT_VIEW)
    }

    @Test
    fun `GIVEN the adapter created WHEN the list is empty THEN it should return the list size`() {

        every { (similarMoviesAdapter.itemCount) } returns listOfSimilarMovies.size
    }

    @Test
    fun `GIVEN the adapter created WHEN the list is empty THEN it should return one item to show the empty list`() {
        listOfSimilarMovies.clear()

        every { (similarMoviesAdapter.itemCount) } returns NUMBER_ONE
    }

    @Test
    fun `GIVEN the adapter created WHEN it receives a view type with objects THEN it should return the correct view`() {
        every { similarMoviesAdapter.onCreateViewHolder(viewGroup, VIEW_TYPE_OBJECT_VIEW) } returns SimilarMoviesAdapter.ViewHolder(view)
    }
}
