package com.example.comicapp.data

abstract class Mapper<in E, T> {

    abstract fun mapTo(from: E): T
}