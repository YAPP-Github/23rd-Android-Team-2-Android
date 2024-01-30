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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.util.Grade
import com.moneymong.moneymong.feature.sign.util.getGradeNumber
import com.moneymong.moneymong.feature.sign.viewmodel.SignUpViewModel
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun SignUpGradeView(
    modifier: Modifier = Modifier,
    selectedGrade : Grade?,
    selectedGradeChange : (Grade) -> Unit,
    onClick: () -> Unit,
    changeGradeInfor: (Int) -> Unit,
) {

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
            columns = GridCells.Fixed(3),
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
                        selectedGradeChange(grade)
                        onClick()
                        changeGradeInfor(getGradeNumber(grade.text))
                    },
                )
            }
        }
    }
}


