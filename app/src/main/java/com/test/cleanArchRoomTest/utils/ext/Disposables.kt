package com.test.cleanArchRoomTest.utils.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


@JvmName("addToComposite")
fun Disposable.addTo(disposableComposite: CompositeDisposable) {
    disposableComposite.add(this)
}