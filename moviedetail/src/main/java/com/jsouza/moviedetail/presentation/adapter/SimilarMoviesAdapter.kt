package com.jsouza.moviedetail.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsouza.moviedetail.R
import com.jsouza.moviedetail.databinding.EmptyMoviesListBinding
import com.jsouza.moviedetail.databinding.MovieListItemBinding
import com.jsouza.moviedetail.domain.model.SimilarMovies
import com.jsouza.moviedetail.extensions.loadPosterImage
import com.jsouza.moviedetail.utils.dateFormat

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0
        private const val VIEW_TYPE_OBJECT_VIEW = 1
    }

    private val movies = mutableListOf<SimilarMovies>()

    fun submitList(
        newData: List<SimilarMovies>
    ) {
        if (movies.isNotEmpty()) {
            movies.clear()
        }
        movies.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies.isEmpty()) {
            VIEW_TYPE_EMPTY_LIST_PLACEHOLDER
        } else {
            VIEW_TYPE_OBJECT_VIEW
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = when (viewType) {
            VIEW_TYPE_OBJECT_VIEW -> LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.movie_list_item,
                    parent,
                    false)
            else -> LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.empty_movies_list,
                    parent,
                    false)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (movies.size == 0) return 1
        return movies.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        when (getItemViewType(position)) {
            VIEW_TYPE_OBJECT_VIEW -> holder.itemBind(movies[position])
            VIEW_TYPE_EMPTY_LIST_PLACEHOLDER -> {
            }
        }
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun onListEmpty() {
            val bindingEmptyList = EmptyMoviesListBinding.bind(itemView)
        }

        fun itemBind(
            movie: SimilarMovies
        ) {
            val bindingMovieList = MovieListItemBinding.bind(itemView)

            bindingMovieList.titleTextViewMovieListItem.text = movie.title
            bindingMovieList.releaseDateTextViewMovieListItem.text = movie.releaseDate?.let { dateFormat(it) }
            bindingMovieList.genreTextViewMovieListItem.text = movie.genres
            bindingMovieList.posterImageViewMovieListItem.loadPosterImage(movie.posterPath)
        }
    }
}
