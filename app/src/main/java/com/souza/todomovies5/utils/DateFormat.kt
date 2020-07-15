package com.souza.todomovies5.utils

import java.text.SimpleDateFormat

fun dateFormat(date: String): String {
    return date.substringBefore("-")
}