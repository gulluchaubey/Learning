package com.example.learning.pager3Demo.data

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)