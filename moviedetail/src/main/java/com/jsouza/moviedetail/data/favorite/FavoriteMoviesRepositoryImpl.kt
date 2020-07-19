package com.jsouza.moviedetail.data.favorite

import android.content.Context
import com.jsouza.moviedetail.data.favorite.local.FavoriteCache
import com.jsouza.moviedetail.domain.repository.FavoriteMoviesRepository

class FavoriteMoviesRepositoryImpl(
    private val context: Context,
    private var favoriteCache: FavoriteCache
) : FavoriteMoviesRepository {

    override fun getIsFavoriteMovie(): Boolean {
        return favoriteCache.getIsFavorite(context)
    }

    override fun setIsFavoriteMovie(
        isFavoriteMovie: Boolean
    ) {
        favoriteCache.setMovieAsFavorite(isFavoriteMovie, context)
    }
}
