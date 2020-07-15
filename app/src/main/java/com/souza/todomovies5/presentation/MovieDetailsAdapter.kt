package com.souza.todomovies5.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.souza.todomovies5.R
import com.souza.todomovies5.data.todomovies.remote.response.SimilarMovies
import com.souza.todomovies5.extensions.loadPosterImage
import com.souza.todomovies5.utils.dateFormat

class MovieDetailsAdapter : RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {

    private val movies =  mutableListOf<SimilarMovies>()

    fun submitList(newData: List<SimilarMovies>) {
        if(movies.isNotEmpty()){
            movies.clear()
        }
        movies.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_list_item,
                parent,
                false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitle: TextView = itemView.findViewById(R.id.title_text_view_movie_list_item)
        private val movieReleaseDate: TextView = itemView.findViewById(R.id.release_date_text_view_movie_list_item)
        private val movieGenre: TextView = itemView.findViewById(R.id.genre_text_view_movie_list_item)
        private val moviePoster: ImageView = itemView.findViewById(R.id.poster_image_view_movie_list_item)

        fun itemBind(movie: SimilarMovies) {
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.releaseDate?.let { dateFormat(it) }
            movieGenre.text = movie.genres.toString()
            moviePoster.loadPosterImage(movie.getImageUrl())
        }
    }
}
