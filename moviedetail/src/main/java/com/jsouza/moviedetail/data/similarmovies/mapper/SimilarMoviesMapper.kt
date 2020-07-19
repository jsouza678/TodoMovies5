package com.jsouza.moviedetail.data.similarmovies.mapper

import com.jsouza.moviedetail.data.similarmovies.local.entities.SimilarMoviesEntity
import com.jsouza.moviedetail.data.similarmovies.remote.response.SimilarMoviesResponse
import com.jsouza.moviedetail.domain.model.SimilarMovies

class SimilarMoviesMapper {

    companion object {
        fun toDatabaseModel(response: SimilarMoviesResponse): Array<SimilarMoviesEntity>? {
            return response.results?.map {
                SimilarMoviesEntity(
                    _id = it.id,
                    title = it.title,
                    posterPath = it.getImageUrl(),
                    genres = it.getGenres(),
                    releaseDate = it.releaseDate
                )
            }?.toTypedArray()
        }

        fun toDomainModel(entity: List<SimilarMoviesEntity>?): List<SimilarMovies>? {
            return entity?.map {
                SimilarMovies(
                    id = it._id,
                    title = it.title,
                    posterPath = it.posterPath,
                    genres = it.genres,
                    releaseDate = it.releaseDate
                )
            }
        }
    }
}
