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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.ui.CatList
import com.example.androiddevchallenge.ui.CatProfile
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.ProvideImageLoader
import com.example.androiddevchallenge.utils.createGifAnimLoader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gifAnimLoader = createGifAnimLoader(this)
        setContent {
            MyTheme {
                ProvideImageLoader(imageLoader = gifAnimLoader) {
                    MyApp()
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object CatList : Screen("catList")
    object CatProfile : Screen("catProfile") {
        fun path(catId: String) = "$route/$catId"
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        // fixed paws background
//        CoilImage(
//            data = getGifUri(R.raw.paws),
//            modifier = Modifier.fillMaxSize().alpha(0.5f).rotate(30f),
//            contentScale = ContentScale.FillHeight,
//            imageLoader = LocalImageLoader.current,
//            contentDescription = "paws gif",
//        )
        NavHost(navController, startDestination = Screen.CatList.route) {
            composable(Screen.CatList.route) {
                CatList(
                    catRepository = CatRepository,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onItemClick = { cat ->
                        navController.navigate(Screen.CatProfile.path(cat.id))
                    }
                )
            }
            composable(
                route = "${Screen.CatProfile.route}/{catId}",
                arguments = listOf(
                    navArgument("catId") {
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                CatProfile(
                    navController = navController,
                    catId = backStackEntry.arguments?.getString("catId")!!,
                    catRepository = CatRepository,
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
