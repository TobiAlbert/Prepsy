package app.prepsy.managers

interface AppPreferences {
    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String
}