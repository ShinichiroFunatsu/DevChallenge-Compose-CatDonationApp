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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.CatList
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.model.Cat
import com.example.androiddevchallenge.utils.produceUiState
import dev.chrisbanes.accompanist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatList(
    catRepository: CatRepository,
    modifier: Modifier = Modifier,
    onItemClick: (cat: Cat) -> Unit = {},
) {
    val (catLists) = produceUiState(catRepository, key = null) {
        getAll()
    }
    catLists.value.data ?: return

    LazyColumn(modifier = modifier) {
        items(items = catLists.value.data!!) { item: CatList ->
            when (item) {
                is CatList.FiveCats -> {
                    FiveContent(
                        modifier = Modifier.aspectRatio(1.5f),
                        contents = item.cats.map { cat ->
                            provideCatEasyProfileTemplate(cat, onItemClick)
                        }
                    )
                }
                is CatList.SixCats -> {
                    SixContent(
                        modifier = Modifier.aspectRatio(1.5f),
                        contents = item.cats.map { cat ->
                            provideCatEasyProfileTemplate(cat, onItemClick)
                        }
                    )
                }
                is CatList.ThreeCats -> {
                    ThreeContent(
                        modifier = Modifier.aspectRatio(1.5f),
                        contents = item.cats.map { cat ->
                            provideCatEasyProfileTemplate(cat, onItemClick)
                        }
                    )
                }
            }
        }
    }
}

private fun provideCatEasyProfileTemplate(
    cat: Cat,
    onItemClick: (cat: Cat) -> Unit,
): @Composable () -> Unit = {
    CatEasyProfile(
        cat = cat,
        modifier = Modifier.padding(4.dp),
        cornerRadius = 8.dp,
        onItemClick = onItemClick,
    )
}

@Composable
fun CatEasyProfile(
    cat: Cat,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 8.dp,
    onItemClick: (cat: Cat) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clickable { onItemClick(cat) },
            contentAlignment = Alignment.Center
        ) {
            CatImage(
                modifier = Modifier.fillMaxSize(),
                url = cat.url
            )
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                CatEasyDescription(cat = cat)
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

@Composable
fun CatEasyDescription(cat: Cat) {
    Row(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.4f))
            .padding(horizontal = 12.dp, vertical = 4.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = cat.name.takeUnless { it.isNullOrEmpty() } ?: "[No Name]",
            fontSize = 9.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Age: ${cat.age}",
            fontSize = 8.sp,
            color = Color.White.copy(alpha = 0.6f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FiveContent(
    modifier: Modifier = Modifier,
    contents: List<@Composable () -> Unit>,
) {
    // 5 content
    // 1x1 * 4
    // 1x2 * 1
    check(contents.size == 5)
    Row(modifier = modifier) {
        // 1x1 * 4
        Column(Modifier.weight(2f)) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    contents[0]()
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    contents[1]()
                }
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    contents[2]()
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    contents[3]()
                }
            }
        }
        // 1x2 * 1
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(0.5f)
        ) {
            contents[4]()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SixContent(
    modifier: Modifier = Modifier,
    contents: List<@Composable () -> Unit>,
) {
    // 3x2 grid
    // total 6 content
    // 1x1 * 6
    check(contents.size == 6)
    // 1x1 * 6
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[0]()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[1]()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[2]()
            }
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[3]()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[4]()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[5]()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ThreeContent(
    modifier: Modifier = Modifier,
    contents: List<@Composable () -> Unit>,
) {
    // 3 content
    // 2x2 * 1
    // 1x1 * 2
    check(contents.size == 3)
    Row(modifier = modifier) {
        // 2x2 * 1
        Box(
            modifier = Modifier
                .weight(2f)
                .aspectRatio(1f)
        ) {
            contents[0]()
        }
        // 1x1 * 2
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[1]()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                contents[2]()
            }
        }
    }
}
