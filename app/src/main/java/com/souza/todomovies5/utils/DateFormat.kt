package com.souza.todomovies5.utils

fun dateFormat(date: String): String {
    return date.substringBefore("-")
}