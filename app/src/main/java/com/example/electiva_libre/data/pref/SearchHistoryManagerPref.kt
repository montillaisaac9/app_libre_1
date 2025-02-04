package alcaravan.guiriri.contribuyente.data.pref

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SearchHistoryManagerPref {
    private const val PREFERENCES_FILE_KEY = "com.example.search_history"
    private const val SEARCH_HISTORY_KEY = "SEARCH_HISTORY"

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())


    fun addSearchQuery(context: Context, query: String) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        val newItem = listOf(query)
        val historyList =getSearchHistory(context).toList().minus(query)

        var history = historyList.plus(newItem)

        if (history.size > 3) {
            history = history.minus(history[0])
        }

        if (query.trim().isNotEmpty()) {
            with(sharedPref.edit()) {
                putString(SEARCH_HISTORY_KEY, history.joinToString(separator = ","))
                apply()
            }
        }
    }

    fun searchHistoryFlow(context: Context): StateFlow<List<String>> {

        _searchHistory.value =  getSearchHistory(context).asReversed().minus("")
        return _searchHistory.asStateFlow()
    }

    private fun getSearchHistory(context: Context): List<String> {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(SEARCH_HISTORY_KEY, "")?.split(",") ?: emptyList()
    }

    fun clearHistory(context: Context) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(SEARCH_HISTORY_KEY)
            apply()
        }
        _searchHistory.value = emptyList()
    }
}