package tr.com.storeapp.utils.extension

import android.view.View
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun View.setVisible(){
    this.visibility = View.VISIBLE
}

fun View.setGone(){
    this.visibility = View.GONE
}

fun View.setInvisible(){
    this.visibility = View.INVISIBLE
}

/**
 * Format Double for currency
 */
fun Double.currencyFormat(): String {
    val locale = Locale("tr", "TR")

    val nf = NumberFormat.getCurrencyInstance(locale)
    val decimalFormatSymbols = (nf as DecimalFormat).decimalFormatSymbols
    // decimalFormatSymbols.currencySymbol = ""
    nf.decimalFormatSymbols = decimalFormatSymbols

    return nf.format(this)
}

/**
 * Get Month name from month number
 */
fun convertMonthName(month: Int): String {
    val local = Locale("tr", "TR")
    val monthParse = SimpleDateFormat("MM", local)
    val monthDisplay = SimpleDateFormat("MMMM", local)
    return monthDisplay.format(monthParse.parse(month.toString()))
}