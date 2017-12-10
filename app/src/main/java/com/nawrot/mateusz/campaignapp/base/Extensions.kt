package com.nawrot.mateusz.campaignapp.base

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG, action: String? = null, actionListener: (View) -> Unit = {}, actionColor: Int? = null) {
    val snack = Snackbar.make(this, message, length)

    action?.let { snack.setAction(action, actionListener) }
    actionColor?.let { snack.setActionTextColor(actionColor) }

    snack.show()
}

fun View.changeVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}