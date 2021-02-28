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
package com.example.androiddevchallenge.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

val LocalImageLoader = compositionLocalOf<ImageLoader> {
    error("Not image loader provided")
}

fun createGifAnimLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder())
            } else {
                add(GifDecoder())
            }
        }
        .build()
}

@Composable
fun ProvideImageLoader(imageLoader: ImageLoader, block: @Composable () -> Unit) {
    CompositionLocalProvider(LocalImageLoader provides imageLoader, content = block)
}

fun getGifUri(@RawRes rawResId: Int): Uri {
    return Uri.parse("android.resource://com.example.androiddevchallenge/$rawResId")
}
