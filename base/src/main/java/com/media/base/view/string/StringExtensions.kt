package com.media.base.view.string

private const val quotationMark  = "\""
private const val openingBracket = "("
private const val closingBracket = ")"

fun String.putInQuotationMarks(): String = quotationMark + this + quotationMark

fun String.putInBrackets(): String = openingBracket + this + closingBracket