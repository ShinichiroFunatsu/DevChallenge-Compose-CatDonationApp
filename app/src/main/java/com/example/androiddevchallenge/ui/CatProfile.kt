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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.model.Cat
import com.example.androiddevchallenge.utils.produceUiState
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CatProfile(
    navController: NavController,
    catId: String,
    catRepository: CatRepository,
) {
    val (cat) = produceUiState(catRepository, key = catId) {
        findById(catId)
    }
    val catData = cat.value.data ?: return
    Box(modifier = Modifier.fillMaxHeight()) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CatProfileContent(cat = catData, onAdoptionClick = { /* TODO Click */ })
            }
        }
        ProfileBackFab(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = { navController.popBackStack() }
        )
    }
}

@Composable
fun CatProfileContent(cat: Cat, onAdoptionClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CatLargeImage(
                url = cat.url,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.2f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.4f),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            ) {
                CatDescription(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    cat = cat,
                    onAdoptionClick = onAdoptionClick
                )
            }
        }
    }
}

@Composable
fun CatDescription(
    modifier: Modifier,
    cat: Cat,
    onAdoptionClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = modifier.padding(top = 44.dp))
        Text(text = cat.nameOrDefault("[No Name]"), style = MaterialTheme.typography.h4)
        Text(
            text = "Age: ${cat.age}",
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = modifier.padding(top = 24.dp))
        Text(
            text = "About:",
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.h5
        )
        Text(
            text = cat.description,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = modifier.weight(1f))
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onAdoptionClick,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(200.dp)
                    .height(72.dp)
                    .padding(bottom = 24.dp),
            ) {
                Text(text = "Adopt")
            }
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

@Composable
fun ProfileBackFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .padding(16.dp)
            .height(48.dp)
            .widthIn(min = 48.dp)
            .alpha(0.6f),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "back icon"
        )
    }
}
