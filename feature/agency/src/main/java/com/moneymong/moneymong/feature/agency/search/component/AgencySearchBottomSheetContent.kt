package com.moneymong.moneymong.feature.agency.search.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.feature.agency.search.item.BottomSheetItem


@Composable
internal fun AgencySearchBottomSheetContent(
    modifier: Modifier = Modifier,
    checkedType: AgencyBottomSheetType?,
    changeType: (AgencyBottomSheetType?) -> Unit,
    onConfirm: () -> Unit
) {

    Column(modifier = modifier.padding(vertical = 24.dp, horizontal = 20.dp)) {
        BottomSheetItem(
            imgRes = AgencyBottomSheetType.CLUB_OR_COUNCIL.imgRes,
            message = AgencyBottomSheetType.CLUB_OR_COUNCIL.message,
            isChecked = checkedType == AgencyBottomSheetType.CLUB_OR_COUNCIL,
            onClick = { changeType(AgencyBottomSheetType.CLUB_OR_COUNCIL) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        BottomSheetItem(
            imgRes = AgencyBottomSheetType.AUDITOR.imgRes,
            message = AgencyBottomSheetType.AUDITOR.message,
            enabled = false,
        )
        Spacer(modifier = Modifier.height(20.dp))
        MDSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onConfirm,
            text = "확인",
            enabled = checkedType != null
        )
    }
}


enum class AgencyBottomSheetType(
    @DrawableRes val imgRes: Int,
    val message: String
) {
    CLUB_OR_COUNCIL(imgRes = R.drawable.img_club, message = "동아리 or 학생회를 등록할게요"),
    AUDITOR(imgRes = R.drawable.img_auditor, message = "감사위원회를 등록할게요")
}