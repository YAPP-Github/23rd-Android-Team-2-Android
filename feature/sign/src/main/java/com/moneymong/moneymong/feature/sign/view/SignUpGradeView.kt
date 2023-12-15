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
import androidx.compose.runtime.mutableStateOf
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
fun SignUpGradeView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    gradeInfor : (String) -> Unit
) {
    var selectedGrade by remember { mutableStateOf<Grade?>(null) }

    Column(
        modifier = modifier
            .background(White)
            .padding(top = 32.dp)
    ) {
        Text(
            text = "학년을 선택해주세요",
            style = Body2,
            color = Gray06
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            columns  = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(Grade.values().size) { index ->
                val grade = Grade.values()[index]
                MDSSelection(
                    modifier = Modifier.weight(1f),
                    text = grade.text,
                    isSelected = grade == selectedGrade,
                    onClick = {
                        selectedGrade = grade
                        onClick()
                        gradeInfor(grade.text)
                    },
                )
            }
        }
    }

}


enum class Grade(val text: String) {
    FIRST("1학년"),
    SECOND("2학년"),
    THIRD("3학년"),
    FOURTH("4학년"),
    FIFTH_OR_ABOVE("5학년 이상")
}