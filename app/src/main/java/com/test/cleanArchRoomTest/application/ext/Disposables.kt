package com.test.cleanArchRoomTest.application.ext

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

@JvmName("addToComposite")
fun Disposable.addTo(disposableComposite: CompositeDisposable) {
    disposableComposite.add(this)
}