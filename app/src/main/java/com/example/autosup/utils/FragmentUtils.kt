package com.example.autosup.utils

import androidx.fragment.app.Fragment

fun getValueFromPreviousFragment(fragment: Fragment, key: String): String? {
    return fragment.arguments?.getString(key)
}