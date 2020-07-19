package com.jsouza.moviedetail.data.favorite.local

import android.content.Context
import androidx.preference.PreferenceManager

class FavoriteCache {

    private companion object {
        private const val FAVORITE = "FAVORITE"
        private const val DEFAULT_VALUE = false
    }

    fun setMovieAsFavorite(
        isFavorite: Boolean,
        context: Context
    ) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putBoolean(FAVORITE, isFavorite)
        editor.apply()
    }

    fun getIsFavorite(
        context: Context
    ): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(FAVORITE, DEFAULT_VALUE)
    }
}
