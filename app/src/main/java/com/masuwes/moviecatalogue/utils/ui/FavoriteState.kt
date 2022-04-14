package com.masuwes.moviecatalogue.utils.ui

sealed class FavoriteState {
    data class FavoriteFound(val state: Boolean): FavoriteState()
    object AddFavorite: FavoriteState()
    object RemoveFavorite: FavoriteState()
}