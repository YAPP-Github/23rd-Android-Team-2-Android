package com.moneymong.moneymong.design_system.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body2

@Composable
fun MDSChip(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTabIndex: Int,
    onChangeSelectedTabIndex: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEachIndexed { index, title ->
            val chipType = if (selectedTabIndex == index) ChipType.Selected else ChipType.UnSelected
            val mdsChipColor = MDSChipColor(chipType)
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = mdsChipColor.borderColor().value,
                        shape = RoundedCornerShape(100)
                    )
                    .clip(RoundedCornerShape(100))
                    .background(mdsChipColor.backgroundColor().value)
                    .clickable {
                        onChangeSelectedTabIndex(index)
                    }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 3.dp),
                    text = title,
                    style = Body2,
                    color = mdsChipColor.textColor().value
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MDSChipPreview() {
    MDSChip(
        tabs = listOf("tab", "tab", "tab"),
        selectedTabIndex = 0,
        onChangeSelectedTabIndex = { 0 })
}