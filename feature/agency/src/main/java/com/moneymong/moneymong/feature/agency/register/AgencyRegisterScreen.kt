package com.moneymong.moneymong.feature.agency.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.AgencyType
import com.moneymong.moneymong.feature.agency.register.view.AgencyResisterContentView

@Composable
fun AgencyRegisterScreen(
    modifier: Modifier = Modifier
) {
    var agencyType: AgencyType? by remember { mutableStateOf(null) }
    var agencyName by remember { mutableStateOf(TextFieldValue()) }
    var nameTextFieldIsError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MMHorizontalSpacing)
            .background(color = White)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 10.dp)
                .size(24.dp)
                .clickable {
                    /*TODO navigate to back*/
                },
            painter = painterResource(id = R.drawable.ic_close_default),
            tint = Gray07,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        AgencyResisterContentView(
            modifier = Modifier.weight(1f),
            agencyType = agencyType,
            onAgencyTypeChange = { agencyType = it },
            agencyName = agencyName,
            onAgencyNameChange = { agencyName = it },
            changeNameTextFieldIsError = { nameTextFieldIsError = it },
        )
        MDSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            onClick = { /*TODO navigate to 소속 등록 성공*/ },
            text = "등록하기",
            enabled = agencyType != null && agencyName.text.isNotEmpty() && nameTextFieldIsError.not()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AgencyRegisterScreenPreview() {
    AgencyRegisterScreen()
}