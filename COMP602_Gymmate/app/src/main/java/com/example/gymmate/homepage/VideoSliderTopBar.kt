package com.example.gymmate.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VideoSliderTopbar(
    viewModel: HomepageViewModel,
    modifier: Modifier = Modifier
) {
    val exerciseList = listOf(
        ListItem("Push up", "SeCKUmcrWt0"),
        ListItem("Squat", "xqvCmoLULNY"),
        // Add more items with different video IDs
    )
    OutlinedCard() {
        VideoPlayer(items = exerciseList, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayer(items: List<ListItem>, modifier: Modifier = Modifier) {
    var openBottomSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ListItem?>(null) }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(5.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { item ->
            Card(
                onClick = {
                    openBottomSheet = !openBottomSheet
                    selectedItem = item
                },
                modifier = Modifier
                    .fillMaxSize()
                    .width(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp)

                    )
                }
            }
        }
    }
    if (openBottomSheet) {
        val windowInsets = if (openBottomSheet)
            WindowInsets(0) else BottomSheetDefaults.windowInsets
        val skipPartiallyExpanded by remember { mutableStateOf(false) }
        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = skipPartiallyExpanded
        )

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            windowInsets = windowInsets,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            selectedItem?.let { item ->
                YoutubePlayer(
                    youtubeVideoID = item.videoId,
                    lifecycleOwner = LocalLifecycleOwner.current
                )
            }
        }
    }
}

data class ListItem(
    val name: String,
    val videoId: String
)