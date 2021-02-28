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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.utils.produceUiState
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CatProfile(
    catId: String,
    catRepository: CatRepository,
    modifier: Modifier = Modifier,
) {
    val (cat) = produceUiState(catRepository, key = catId) {
        findById(catId)
    }
    val catData = cat.value.data ?: return
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CatLargeImage(
                url = catData.url,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.2f)
            )
            Text(
                text = catData.name.takeUnless { it.isNullOrEmpty() } ?: "[No Name]",
                style = MaterialTheme.typography.h3,
            )
        }
    }
}

@Composable
fun CatLargeImage(url: String, modifier: Modifier) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = "cat image",
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
