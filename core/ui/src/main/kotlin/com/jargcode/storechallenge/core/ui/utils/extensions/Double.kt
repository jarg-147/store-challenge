package com.jargcode.storechallenge.core.ui.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toFormattedPrice(): String = with(NumberFormat.getNumberInstance(Locale.getDefault())) {
    minimumFractionDigits = 2
    maximumFractionDigits = 2
    "${format(this@toFormattedPrice)}€"
}