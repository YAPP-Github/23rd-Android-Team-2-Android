package com.moneymong.moneymong.feature.mymong.component.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Gray10

@Composable
fun MyMongMarkdownView(
    modifier: Modifier = Modifier,
    markdownText: String,
    textColor: Color = Gray10
) {
    var isTable = false
    val tableLines = mutableListOf<String>()

    Column(modifier = modifier) {
        val lines = markdownText.split("\n")

        lines.forEach { line ->
            if (line.startsWith(prefix = tableSymbol)) {
                isTable = true
                tableLines.add(line)
            } else {
                if (isTable) {
                    isTable = false
                    MyMongMarkdownTable(lines = tableLines)
                    tableLines.clear()
                }
                MyMongMarkdownText(
                    line = line,
                    textColor = textColor
                )
            }
        }
    }
}


@Preview
@Composable
fun MyMongMarkdownPreview() {
    MyMongMarkdownView(
        modifier = Modifier.padding(horizontal = 20.dp),
        markdownText = "" +
                "# Heading3\n" +
                "\n" +
                "## Heading2\n" +
                "\n" +
                "### Heading1\n" +
                "\n" +
                "- Bullet point\n" +
                "\n" +
                "Body"
    )
}