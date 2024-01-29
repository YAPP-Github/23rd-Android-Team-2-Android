package com.moneymong.moneymong.feature.mymong.component.markdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.White

internal const val tableSymbol = "|"

@Composable
internal fun MyMongMarkdownTable(
    modifier: Modifier = Modifier,
    lines: List<String>,
) {
    Column(modifier = modifier.clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))) {
        val titles = lines.first().split(tableSymbol).filter { it.isNotEmpty() }

        Row(
            modifier = Modifier.background(Blue04),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            titles.forEach { title ->
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    color = White,
                    style = Body3
                )
            }
        }

        lines.drop(2).forEach { line ->
            val contents = line.split(tableSymbol).filter { it.isNotEmpty() }

            Row(
                modifier = Modifier
                    .background(Gray01)
                    .height(intrinsicSize = IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                contents.forEachIndexed { index, content ->
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        text = content,
                        textAlign = TextAlign.Center,
                        color = Gray08,
                        style = Body3
                    )
                    if (index != contents.lastIndex) {
                        Divider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                            color = Gray03
                        )
                    }
                }
            }
        }
    }
}