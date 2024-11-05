package com.jargcode.storechallenge.convention

import com.android.build.api.dsl.LibraryExtension

fun LibraryExtension.setAndroidNamespace(name: String) {
    namespace = "com.jargcode.storechallenge.$name"
}