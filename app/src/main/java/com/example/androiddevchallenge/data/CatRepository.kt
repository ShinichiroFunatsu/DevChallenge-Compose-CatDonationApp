package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.model.Cat

object CatRepository {
    private val cats = listOf(
        Cat(name = "Doggy", age = 1),
        Cat(name = "Ken", age = 1),
        Cat(name = "Mew", age = 1),
        Cat(name = "Tama", age = 1),
        Cat(name = "Crow", age = 1),
        Cat(name = "Tommy", age = 1),
        Cat(age = 1),
        Cat(age = 1),
        Cat(age = 1),
    )

    fun getAll() = cats
}