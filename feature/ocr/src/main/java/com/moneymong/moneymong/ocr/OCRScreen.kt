package com.moneymong.moneymong.ocr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.ocr.view.OCRCameraPermissionDeniedView
import com.moneymong.moneymong.ocr.view.OCRCaptureView
import com.moneymong.moneymong.ocr.view.OCRHelperView
import com.moneymong.moneymong.ocr.view.OCRInteractionView
import com.moneymong.moneymong.ocr.view.OCRTopbarView

@Composable
fun OCRScreen(
    modifier: Modifier = Modifier
) {
    var visibleHelper by remember { mutableStateOf(true) } // TODO
    if (false) { // TODO 카메라 권한 요청 모달
        MDSModal(
            icon = R.drawable.ic_camera_modal,
            title = "카메라 접근을 허용하시겠습니까?",
            description = "영수증 스캔을 위해 필요합니다",
            negativeBtnText = "허용 안함",
            positiveBtnText = "허용",
            onClickNegative = { /*TODO*/ },
            onClickPositive = { /*TODO*/ }
        )
    }

    if (false) { // TODO 갤러리 권한 요청 모달
        MDSModal(
            icon = R.drawable.ic_photo_modal,
            title = "갤러리 접근 권한이 필요합니다",
            description = "설정에서 변경해주세요",
            negativeBtnText = "확인",
            positiveBtnText = "이동",
            onClickNegative = { /*TODO*/ },
            onClickPositive = { /*TODO*/ })
    }

    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(White)
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (false) { // TODO 카메라 접근 권한이 없다면
                OCRCameraPermissionDeniedView(onClickRequestPermission = { /* TODO */ })
            } else {
                OCRCaptureView()
                if (true) { // TODO 영수증이 보일 때 숨기기
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "영수증의 처음과 끝이\n모두 포함되게 촬영해주세요",
                        style = Heading1,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            OCRTopbarView(
                modifier = Modifier.align(Alignment.TopCenter),
                onClickHelp = { /*TODO*/ },
                onClickClose = { /* TODO */ }
            )
            OCRInteractionView(
                modifier = Modifier.align(Alignment.BottomCenter),
            )
            if (visibleHelper) { // TODO 최초 진입 시, 혹은 도움말 아이콘 클릭 시
                OCRHelperView(onClickClose = { visibleHelper = !visibleHelper })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRScreenPreview() {
    OCRScreen()
}