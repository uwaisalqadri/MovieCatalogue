package com.masuwes.core.data.model.detail.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_detail_tvshow")
data class DetailTvShowEntity(
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String?,
    @ColumnInfo(name = "first_air_date")
    val first_air_date: String?,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "original_language")
    val original_language: String?,
    @ColumnInfo(name = "original_name")
    val original_name: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "poster_path")
    val poster_path: String?,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double?,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int?
)