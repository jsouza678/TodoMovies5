package com.jsouza.moviedetail.data.mapper

import com.jsouza.moviedetail.data.local.entities.MovieDetailEntity
import com.jsouza.moviedetail.data.remote.response.MovieDetailResponse
import com.jsouza.moviedetail.domain.model.MovieDetail

class MovieDetailMapper {

    companion object {
        fun toDatabaseModel(response: MovieDetailResponse): MovieDetailEntity {
            return MovieDetailEntity(
                _id = response.id,
                title = response.title,
                genres = response.getGenres(),
                releaseDate = response.releaseDate,
                backdropPath = response.getImageUrl(),
                description = response.description,
                runtime = response.runtime,
                popularity = response.popularity,
                voteCount = response.voteCount
            )
        }

        fun toDomainModel(entity: MovieDetailEntity): MovieDetail {
            return MovieDetail(
                _id = entity._id,
                title = entity.title,
                genres = entity.genres,
                releaseDate = entity.releaseDate,
                posterImage = entity.backdropPath,
                description = entity.description,
                runtime = entity.runtime,
                popularity = entity.popularity,
                voteCount = entity.voteCount
            )
        }
    }
}
