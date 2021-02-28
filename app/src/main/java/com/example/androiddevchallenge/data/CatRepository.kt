/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CatRepository {
    private val all by lazy {
        listOf(
            fiveCat(
                Cat(name = "Tiger", age = 1, url = "https://cdn2.thecatapi.com/images/cf.jpg"),
                Cat(name = "Jasper", age = 1, url = "https://cdn2.thecatapi.com/images/rh.jpg"),
                Cat(name = "Rosie", age = 1, url = "https://cdn2.thecatapi.com/images/16g.jpg"),
                Cat(name = "Phoebe", age = 1, url = "https://cdn2.thecatapi.com/images/22v.jpg"),
                Cat(name = "Coco", age = 1, url = "https://cdn2.thecatapi.com/images/25s.jpg"),
            ),
            sixCat(
                Cat(name = "Finn", age = 1, url = "https://cdn2.thecatapi.com/images/2ge.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/2m8.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/2sc.jpg"),
                Cat(name = "Zoey", age = 1, url = "https://cdn2.thecatapi.com/images/319.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/31h.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/4f5.jpg"),
            ),
            threeCat(
                Cat(name = "Olive", age = 1, url = "https://cdn2.thecatapi.com/images/5kr.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/60m.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/6an.jpg"),
            ),
            sixCat(
                Cat(name = "Tiger", age = 1, url = "https://cdn2.thecatapi.com/images/6uq.jpg"),
                Cat(name = "Pepper", age = 1, url = "https://cdn2.thecatapi.com/images/73l.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/74n.jpg"),
                Cat(name = "Princess", age = 1, url = "https://cdn2.thecatapi.com/images/7a5.jpg"),
                Cat(name = "Phoebe", age = 1, url = "https://cdn2.thecatapi.com/images/7ni.jpg"),
                Cat(name = "Mia", age = 1, url = "https://cdn2.thecatapi.com/images/86g.jpg"),
            ),
            fiveCat(
                Cat(name = "Ellie", age = 1, url = "https://cdn2.thecatapi.com/images/8o0.jpg"),
                Cat(name = "Henry", age = 1, url = "https://cdn2.thecatapi.com/images/9lb.png"),
                Cat(name = "Shadow", age = 1, url = "https://cdn2.thecatapi.com/images/9qh.jpg"),
                Cat(name = "Willow", age = 1, url = "https://cdn2.thecatapi.com/images/9ut.jpg"),
                Cat(name = "Simon", age = 1, url = "https://cdn2.thecatapi.com/images/a11.jpg"),
            ),
            sixCat(
                Cat(name = "Jasper", age = 1, url = "https://cdn2.thecatapi.com/images/a6o.jpg"),
                Cat(name = "Felix", age = 1, url = "https://cdn2.thecatapi.com/images/a6t.jpg"),
                Cat(name = "Oreo", age = 1, url = "https://cdn2.thecatapi.com/images/aot.png"),
                Cat(name = "Finn", age = 1, url = "https://cdn2.thecatapi.com/images/atc.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/auo.jpg"),
                Cat(name = "Smokey", age = 1, url = "https://cdn2.thecatapi.com/images/b43.jpg"),
            ),
            threeCat(
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/b45.jpg"),
                Cat(name = "Sophie", age = 1, url = "https://cdn2.thecatapi.com/images/b8o.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/b9b.jpg"),
            ),
            sixCat(
                Cat(name = "Stella", age = 1, url = "https://cdn2.thecatapi.com/images/b9c.jpg"),
                Cat(name = "Simba", age = 1, url = "https://cdn2.thecatapi.com/images/bhf.jpg"),
                Cat(name = "Toby", age = 1, url = "https://cdn2.thecatapi.com/images/bl0.jpg"),
                Cat(name = "Max", age = 1, url = "https://cdn2.thecatapi.com/images/blj.jpg"),
                Cat(name = "Lola", age = 1, url = "https://cdn2.thecatapi.com/images/bv5.jpg"),
                Cat(name = "Lily", age = 1, url = "https://cdn2.thecatapi.com/images/c2v.jpg"),
            ),
            fiveCat(
                Cat(name = "Gracie", age = 1, url = "https://cdn2.thecatapi.com/images/c76.jpg"),
                Cat(name = "Jack", age = 1, url = "https://cdn2.thecatapi.com/images/c7h.jpg"),
                Cat(name = "Daisy", age = 1, url = "https://cdn2.thecatapi.com/images/c84.jpg"),
                Cat(name = "Cleo", age = 1, url = "https://cdn2.thecatapi.com/images/cd3.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/cdi.jpg"),
            ),
            sixCat(
                Cat(name = "Chloe", age = 1, url = "https://cdn2.thecatapi.com/images/cje.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/ck4.jpg"),
                Cat(name = "Nala", age = 1, url = "https://cdn2.thecatapi.com/images/cm4.jpg"),
                Cat(name = "Gracie", age = 1, url = "https://cdn2.thecatapi.com/images/cnn.jpg"),
                Cat(name = "Buddy", age = 1, url = "https://cdn2.thecatapi.com/images/cq0.jpg"),
                Cat(name = "Lilly", age = 1, url = "https://cdn2.thecatapi.com/images/cqj.jpg"),
            ),
            threeCat(
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/d35.jpg"),
                Cat(name = "Kitty", age = 1, url = "https://cdn2.thecatapi.com/images/d9o.jpg"),
                Cat(name = "Callie", age = 1, url = "https://cdn2.thecatapi.com/images/da3.jpg"),
            ),
            sixCat(
                Cat(name = "Tigger", age = 1, url = "https://cdn2.thecatapi.com/images/dbg.jpg"),
                Cat(name = "", age = 1, url = "https://cdn2.thecatapi.com/images/dek.jpg"),
                Cat(name = "Winston", age = 1, url = "https://cdn2.thecatapi.com/images/dmh.jpg"),
                Cat(name = "Penny", age = 1, url = "https://cdn2.thecatapi.com/images/dqu.jpg"),
                Cat(name = "Luna", age = 1, url = "https://cdn2.thecatapi.com/images/e94.jpg"),
                Cat(name = "Olive", age = 1, url = "https://cdn2.thecatapi.com/images/cqn.jpg"),
            ),
        )
    }

    suspend fun getAll(): Result<List<CatList>> = withContext(Dispatchers.Default) {
        return@withContext Result.Success(all)
    }

    suspend fun findById(id: String): Result<Cat> = withContext(Dispatchers.Default) {
        val hit = all.flatMap { it.list }.find { it.id == id }
        if (hit != null) {
            Result.Success(hit)
        } else {
            Result.Error(NoSuchElementException("not matched."))
        }
    }
}

private fun fiveCat(vararg cats: Cat): CatList {
    check(cats.size == 5)
    return CatList.FiveCats(cats.toList())
}
private fun sixCat(vararg cats: Cat): CatList {
    check(cats.size == 6)
    return CatList.SixCats(cats.toList())
}
private fun threeCat(vararg cats: Cat): CatList {
    check(cats.size == 3)
    return CatList.ThreeCats(cats.toList())
}

sealed class CatList(val list: List<Cat>) {
    data class SixCats(val cats: List<Cat>) : CatList(cats)
    data class FiveCats(val cats: List<Cat>) : CatList(cats)
    data class ThreeCats(val cats: List<Cat>) : CatList(cats)
}
