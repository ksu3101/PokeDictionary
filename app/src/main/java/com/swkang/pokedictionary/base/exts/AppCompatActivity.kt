package com.swkang.pokedictionary.base.exts

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun AppCompatActivity?.showDialogFragment(
    dialogFragment: DialogFragment,
    fragmentTag: String
) {
    if (this == null || this.isFinishing)
        throw IllegalStateException("Activity cannot be null.")
    dialogFragment.show(supportFragmentManager, fragmentTag)
}

fun AppCompatActivity?.dismissDialogFragment(
    fragmentTag: String
) {
    if (this == null || this.isFinishing)
        throw IllegalStateException("Activity cannot be null.")
    val fm = supportFragmentManager
    val fragment = fm.findFragmentByTag(fragmentTag)
    if (fragment is DialogFragment) {
        fragment.dismiss()
    }
}