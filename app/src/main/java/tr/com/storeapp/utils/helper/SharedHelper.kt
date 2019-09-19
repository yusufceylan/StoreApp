package tr.com.storeapp.utils.helper

import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Helper class for managing shared preferences
 */
class SharedHelper(private val sharedPreferences: SharedPreferences, private val gson: Gson) {

    /**
     * Helps save Int data
     */
    fun putIntData(@NonNull key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    /**
     * Helps get int data
     */
    fun getIntData(@NonNull key: String, defVal: Int): Int {
        return sharedPreferences.getInt(key, defVal)
    }

    /**
     * Helps save String data
     */
    fun putStringData(@NonNull key: String, @Nullable value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    /**
     * Helps get String data
     */
    fun getStringData(@NonNull key: String, @Nullable defVal: String?): String? {
        return sharedPreferences.getString(key, defVal)
    }

    /**
     * Helps save Long data
     */
    fun putLongData(@NonNull key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    /**
     * Helps get long data
     */
    fun getLongData(@NonNull key: String, @Nullable defVal: Long): Long {
        return sharedPreferences.getLong(key, defVal)
    }

    /**
     * Helps save Boolean data
     */
    fun putBooleanData(@NonNull key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    /**
     * Helps get Boolean data
     */
    fun getBooleanData(@NonNull key: String, defVal: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defVal)
    }

    /**
     * Add object data with given key to Shared preferences
     */
    fun <T> addData(key: String, data: T?) {

        if (data == null)
            throw RuntimeException("Data can not be null")

        val json = gson.toJson(data)

        if (json != null)
            putStringData(key, json)
        else
            throw RuntimeException("Data can not be null")
    }

    /**
     * Retrieve Object model with given key from Shared Preferences
     */
    @Nullable
    fun <T> retrieveData(key: String, clazz: Class<T>): T? {

        if (sharedPreferences.contains(key)) {

            val json = getStringData(key, null) ?: return null

            return gson.fromJson(json, clazz)

        } else {
            return null
        }


    }

    /**
     * Add List of object data with given key to Shared preferences
     */
    fun <T> addListData(key: String, list: List<T>?) {

        if (list == null)
            throw RuntimeException("Data can not be null")

        val json = gson.toJsonTree(list).asJsonArray.toString()

        putStringData(key, json)
    }


    /**
     * Retrieve list of objects with given key from Shared preferences
     */
    fun <T> retrieveListData(key: String, tt: TypeToken<List<T>>): List<T>? {

        val json = getStringData(key, null)

        return if (sharedPreferences.contains(key)) {

            if (json == null) null else gson.fromJson<List<T>>(json, tt.type)

        } else {
            null
        }
    }


    /**
     * Check shared preferences contains given key
     */
    fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    /**
     * Deletes specific data in [SharedPreferences]
     */
    fun removeData(@NonNull key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    /**
     * Clear all shared preferences
     */
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}