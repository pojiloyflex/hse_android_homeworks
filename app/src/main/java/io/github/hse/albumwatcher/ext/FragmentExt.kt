package io.github.hse.albumwatcher.ext

import androidx.fragment.app.Fragment

fun Fragment.lazyStringArgument(propertyName: String) = lazyUnsynchronized {
    requireArguments().getString(propertyName)
}

fun <T> lazyUnsynchronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)