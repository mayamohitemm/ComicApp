package com.example.domain

abstract class Mapper<in E, T> {

    abstract fun mapTo(from: E): T
}
