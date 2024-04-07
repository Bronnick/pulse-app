package com.example.pulse_app.ui.theme.composables.new_record_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulse_app.view_models.HistoryViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scroller(
    viewModel: HistoryViewModel,
    paramName: String,
    measurementUnit: String
) {
    val pagerState = rememberPagerState(
        pageCount = { 10 }
    )

    val scope = rememberCoroutineScope()

    val mutableInteractionSource = remember {
        MutableInteractionSource()
    }
    
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            when(paramName) {
                "Diastolic" -> viewModel.setDiastolic(page)
                "Systolic" -> viewModel.setSystolic(page)
                "Pulse" -> viewModel.setPulseValue(page)
            }
        }
    }

    Column(
        modifier = Modifier.padding(all = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = paramName,
            textAlign = TextAlign.Center
        )
        Text(
            text = measurementUnit,
            textAlign = TextAlign.Center
        )

        BoxWithConstraints(
            modifier = Modifier.heightIn(10.dp, 200.dp),
        ) {

            val contentPadding = (maxHeight - 50.dp) / 2
            val offSet = maxHeight / 3
            val itemSpacing = 20.dp
            /*CenterCircle(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center),
            fillColor = Color.Magenta,
            strokeWidth = 2.dp
        )*/

            VerticalPager(
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    //pagerSnapDistance = PagerSnapDistance.atMost()
                ),
                contentPadding = PaddingValues(vertical = contentPadding),
                pageSpacing = itemSpacing,
            ) { page ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .graphicsLayer {
                            val pageOffset = ((pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction).absoluteValue
                            // Set the item alpha based on the distance from the center
                            val percentFromCenter = 1.0f - (pageOffset / (5f / 2f))
                            val opacity = 0.25f + (percentFromCenter * 0.75f).coerceIn(0f, 1f)

                            alpha = opacity
                            clip = true
                        }
                        .clickable(
                            interactionSource = mutableInteractionSource,
                            indication = null,
                            enabled = true,
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        }) {
                    Text(
                        text = "$page",
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}

@Composable
fun CenterCircle(
    modifier: Modifier = Modifier,
    fillColor: Color,
    strokeWidth: Dp
) {
    Canvas(
        modifier = modifier
            .size(75.dp)
    ) {

        drawArc(
            color = fillColor,
            0f,
            360f,
            true,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
    }
}