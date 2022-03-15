package sk.valovic.calculator

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(
    var state: SavedStateHandle,
    val context: Context
): ViewModel(){
    val isCalculated = state.getLiveData<Boolean>("isCalculated", false)
    val displayedText = state.getLiveData<String>("displayedText", "")

    fun clearDisplray(){
        state.set("displayedText", "")
        state.set("isCalculated", false)
    }

    fun addSymbol(symbol: String) {
        state.set("displayedText", displayedText.value.plus(symbol))
        state.set("isCalculated", false)
    }

    fun calculate(){
        if(displayedText.value?.length ?: 0 > 0) {
            val result: String = Calculator.getResult(displayedText.value, context.getString(R.string.general_error) )
            state.set("displayedText", result)
            state.set("isCalculated", true)
        }
    }

    fun deleteLast(){
        if(displayedText.value?.length ?: 0 > 0) {
            if(displayedText.value?.contains("Error")!!){
                clearDisplray()
            } else {
                state.set(
                    "displayedText",
                    displayedText.value?.substring(0, (displayedText.value?.length?.minus(1)!!))
                ) ?: ""
            }
            state.set("isCalculated", false)
        }
    }

}