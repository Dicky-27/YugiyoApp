package com.example.yugiyoapp.model

import java.io.Serializable

data class Restoran(
    val id: Int,
    val imageResto: Int,
    val title: String,
    var rating: Float,
    var isRatingDone: Boolean,
    var ratingCount: Int,
    var likeCount: Int,
    var distance: String,
    var shippingPrice: String,
    var menu: Array<String>,
    var time: String,
    var isFavorite: Boolean,
    var minimumPrice: String,
    var shippingPriceReal: String
) : Serializable