package sk.valovic.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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

    fun copyToClipBoard(){
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("Calculator text", displayedText.value)

        clipboard.setPrimaryClip(clip)
        Toast.makeText(context,"Copied",Toast.LENGTH_SHORT).show()
    }

}