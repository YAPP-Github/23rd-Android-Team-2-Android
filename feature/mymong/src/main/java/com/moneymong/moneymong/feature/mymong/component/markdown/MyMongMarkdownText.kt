package com.moneymong.moneymong.feature.mymong.component.markdown

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Heading1 as MDSHeading1
import com.moneymong.moneymong.design_system.theme.Heading2 as MDSHeading2
import com.moneymong.moneymong.design_system.theme.Heading3 as MDSHeading3

private enum class MarkdownLineType(val prefix: String, val style: TextStyle) {
    Heading3(prefix = "# ", style = MDSHeading3),
    Heading2(prefix = "## ", style = MDSHeading2),
    Heading1(prefix = "### ", style = MDSHeading1),
    BulletPoint(prefix = "- ", style = Body3),
    Body(prefix = "", style = Body3),
}

@Composable
internal fun MyMongMarkdownText(
    modifier: Modifier = Modifier,
    line: String,
    textColor: Color
) {
    val lineType = MarkdownLineType.values()
        .filter { type -> type != MarkdownLineType.Body }
        .find { type -> line.startsWith(type.prefix) } ?: MarkdownLineType.Body

    val markdownLine = when (lineType) {
        MarkdownLineType.BulletPoint -> line.replaceFirst("- ", "▪ ")
        else -> line.removePrefix(lineType.prefix)
    }

    Text(
        modifier = modifier,
        text = markdownLine,
        color = textColor,
        style = lineType.style
    )
}