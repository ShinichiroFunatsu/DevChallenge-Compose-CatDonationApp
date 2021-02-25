package com.example.androiddevchallenge.model

import java.util.*

data class Cat(
    val id: String = uuid(),
    val name: String? = null,
    val age: Int
)

private fun uuid() = UUID.randomUUID().toString()