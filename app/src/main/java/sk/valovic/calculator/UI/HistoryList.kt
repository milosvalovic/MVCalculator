package sk.valovic.calculator.UI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.valovic.calculator.HistoryItem
import sk.valovic.calculator.MainViewModel

class HistoryList {
    val list = listOf(HistoryItem(1,"5+5","10"),HistoryItem(1,"5+5","10"),HistoryItem(1,"5+5","10"),HistoryItem(1,"5+5","10"),HistoryItem(1,"5+5","10"))

    @Composable
    fun largeList(list: List<HistoryItem>, viewModel: MainViewModel) {
        LazyColumn(
            contentPadding = // 1.
            PaddingValues(horizontal = 16.dp, vertical = 8.dp))
        {
            items(list.size) {
                    index ->
                    Text( // 4.
                        list[index].equation,
                        Modifier // 5.
                            .clickable( // 6.
                                onClick = {

                                },
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(bounded = true), // 7.
                            )
                    )
            }
        }
    }

}