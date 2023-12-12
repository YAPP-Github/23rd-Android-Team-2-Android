package com.moneymong.moneymong.feature.sign.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.White


@Composable
fun SignUpGradeView(modifier: Modifier = Modifier, onClick : () -> Unit ) {
    var selectedIndex  by remember { mutableIntStateOf(-1) }

        Column(
            modifier = modifier
                .background(White)
                .padding(top= 32.dp)
        ) {
            Text(
                text = "학년을 선택해주세요",
                style = Body2,
                color = Gray06
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(count = 5) { index ->
                    MDSSelection(
                        modifier = Modifier.weight(1f),
                        text = GradeList[index],
                        isSelected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            onClick()
                        }
                    )
                }
            }
        }

}

val GradeList = listOf("1학년", "2학년", "3학년", "4학년", "5학년 이상")

