package tr.com.storeapp.utils.extension

import android.view.View

fun View.setVisible(){
    this.visibility = View.VISIBLE
}

fun View.setGone(){
    this.visibility = View.GONE
}

fun View.setInvisible(){
    this.visibility = View.INVISIBLE
}