package alcaravan.guiriri.contribuyente.data.pref

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ThemeSettings {
    private const val PREFERENCES_FILE_KEY = "com.example.settings"
    private const val DARK_THEME_KEY = "DARK_THEME"
    private val _isDarkTheme = MutableStateFlow(false)

    fun setDarkTheme(context: Context, isDarkTheme: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putBoolean(DARK_THEME_KEY, isDarkTheme)
            apply()
        }
        _isDarkTheme.value = isDarkTheme
    }

    fun isDarkThemeFlow(context: Context): Flow<Boolean> {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        _isDarkTheme.value = sharedPref.getBoolean(DARK_THEME_KEY, false)
        return _isDarkTheme.asStateFlow()
    }
    fun isDarkTheme(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        _isDarkTheme.value = sharedPref.getBoolean(DARK_THEME_KEY, false)
        return _isDarkTheme.value
    }
}
