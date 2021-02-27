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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Cat
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CatList(cats: List<Cat>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = cats) { cat ->
            CatOverView(cat = cat)
        }
    }
}

@Composable
fun CatOverView(cat: Cat) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                // TODO: Transition to Detail
            },
    ) {

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {

            CatImage(
                modifier = Modifier.fillMaxSize(),
                url = cat.url
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Text(text = cat.name.takeUnless { it.isNullOrEmpty() } ?: "[No Name]")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Age: ${cat.age}")
                }
            }
        }
    }
}

@Composable
fun CatImage(url: String, modifier: Modifier) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "cat image",
        fadeIn = true,
        error = {
            Box(
                Modifier.matchParentSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${it.throwable.cause}", fontSize = 8.sp)
            }
        },
        loading = {
            Box(Modifier.matchParentSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    )
}
