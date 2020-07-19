package com.jsouza.moviedetail.utils

fun dateFormat(date: String): String {
    return date.substringBefore("-")
}
