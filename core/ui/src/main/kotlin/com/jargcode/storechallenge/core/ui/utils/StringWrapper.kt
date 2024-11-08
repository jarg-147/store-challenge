package com.jargcode.storechallenge.core.ui.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class StringWrapper(
    val text: String? = null,
    @StringRes val resId: Int? = null,
    val args: Array<Any> = arrayOf()
) {

    val value: String
        @Composable
        get() = when {
            !text.isNullOrBlank() -> text
            resId != null -> stringResource(id = resId, *args)
            else -> ""
        }

    @Composable
    fun isNotBlank(): Boolean {
        return value.isNotBlank()
    }

}