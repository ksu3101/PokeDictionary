package com.swkang.common.exts

private val REGEX_ENG = "^[a-zA-Z]*$"

fun String?.isOnlyEnglish(): Boolean {
    if (this.isNullOrEmpty()) return false
    return REGEX_ENG.toRegex() matches this
}