package com.moneymong.moneymong.ledgerdetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing

@Composable
fun LedgerDetailTopbarView(
    modifier: Modifier = Modifier,
    title: String,
    useEditMode: Boolean,
    enabledDone: Boolean,
    onClickPrev: () -> Unit,
    onClickDelete: () -> Unit,
    onClickDone: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray01)
    ) {
        Icon(
            modifier = Modifier
                .noRippleClickable {
                    onClickPrev()
                }
                .padding(vertical = 18.dp, horizontal = MMHorizontalSpacing)
                .align(Alignment.CenterStart),
            painter = painterResource(id = drawable.ic_chevron_left),
            contentDescription = null,
            tint = Gray07
        )
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.Center),
            text = title,
            style = Heading1,
            color = Gray10
        )
        if (useEditMode) {
            Text(
                modifier = Modifier
                    .noRippleClickable {
                        if (enabledDone) { onClickDone() }
                    }
                    .padding(vertical = 12.dp, horizontal = MMHorizontalSpacing)
                    .align(Alignment.CenterEnd),
                text = "수정완료",
                style = Body3,
                color = if (enabledDone) Blue04 else Gray04
            )
        } else {
            Icon(
                modifier = Modifier
                    .noRippleClickable {
                        onClickDelete()
                    }
                    .padding(vertical = 18.dp, horizontal = MMHorizontalSpacing)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = drawable.ic_delete),
                contentDescription = null,
                tint = Gray07
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerDetailTopbarPreview() {
    LedgerDetailTopbarView(
        title = "지출 상세내역",
        useEditMode = false,
        enabledDone = false,
        onClickPrev = { /*TODO*/ },
        onClickDelete = {}) {

    }
}