package com.nawrot.mateusz.campaignapp.base

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE

}