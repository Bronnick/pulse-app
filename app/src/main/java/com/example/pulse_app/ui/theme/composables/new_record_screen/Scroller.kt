package com.example.pulse_app.ui.theme.composables.new_record_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
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
    val containerColor = if(isSystemInDarkTheme()) Color.DarkGray else Color.White
    val currentPageLinesColor = if(isSystemInDarkTheme()) Color.White else Color.LightGray

    val displayMetrics = LocalContext.current.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density

    val pagerState = rememberPagerState(
        pageCount = { 121 }
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

    Surface(
        modifier = Modifier
            .padding(all = 8.dp)
            .width(100.dp)
    ) {
        Column(
             modifier = Modifier
                 .background(
                     color = containerColor,
                     shape = RoundedCornerShape(10.dp)
                 )
                 .width(50.dp)
                 .padding(top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = paramName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "($measurementUnit)",
                fontSize=14.sp,
                textAlign = TextAlign.Center
            )

            BoxWithConstraints(
                modifier = Modifier.heightIn(10.dp, 200.dp),
                contentAlignment = Alignment.BottomEnd
            ) {

                val contentPadding = (maxHeight - 50.dp) / 2
                val offSet = maxHeight / 5
                val itemSpacing = 20.dp
                val elementSize = 55
                /*CenterCircle(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center),
            fillColor = Color.Magenta,
            strokeWidth = 2.dp
        )*/

                VerticalPager(
                    modifier = Modifier
                        .drawBehind {
                        val borderSize = 1.dp
                        drawLine(
                            color = currentPageLinesColor,
                            start = Offset(-20f, 230f),
                            end = Offset(size.width+20f, 230f),
                            strokeWidth = borderSize.toPx(),
                            pathEffect = PathEffect.cornerPathEffect(5.0f)
                        )
                        drawLine(
                            color = currentPageLinesColor,
                            start = Offset(-20f, 370f),
                            end = Offset(size.width+20f, 370f),
                            strokeWidth = borderSize.toPx(),
                            pathEffect = PathEffect.cornerPathEffect(5.0f)
                        )
                    },
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
                            .size(elementSize.dp)
                            .graphicsLayer {
                                val pageOffset = ((pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction).absoluteValue
                                val percentFromCenter = 1.0f - (pageOffset / (2.25f / 2f))
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
                            },
                            contentAlignment = Alignment.Center) {
                        Text(
                            text = "$page",
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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