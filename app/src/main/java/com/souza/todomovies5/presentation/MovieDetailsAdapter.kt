package com.souza.todomovies5.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.souza.todomovies5.R
import com.souza.todomovies5.data.todomovies.remote.response.Movie

class MovieDetailsAdapter : RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {

    private val movies =  mutableListOf<Movie>(
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla"),
        Movie("Teste de Filme", "genero", "2005", "bla bla")
    )

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

        fun itemBind(movie: Movie) {
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.releaseDate
            movieGenre.text = movie.releaseDate
            //load image extension with glide.. bla bla
        }
    }

}
