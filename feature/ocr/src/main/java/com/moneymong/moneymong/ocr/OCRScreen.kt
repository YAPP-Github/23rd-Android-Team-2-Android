package com.moneymong.moneymong.ocr

import android.Manifest.permission.*
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.common.ext.hasPermission
import com.moneymong.moneymong.ocr.view.OCRCameraPermissionDeniedView
import com.moneymong.moneymong.ocr.view.OCRCaptureView
import com.moneymong.moneymong.ocr.view.OCRHelperView
import com.moneymong.moneymong.ocr.view.OCRInteractionView
import com.moneymong.moneymong.ocr.view.OCRTopbarView
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun OCRScreen(
    modifier: Modifier = Modifier,
    viewModel: OCRViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val sideEffect = viewModel.container.sideEffectFlow
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current // TODO
    var hasCameraPermission by remember { mutableStateOf(context.hasPermission(CAMERA)) }
    var visibleHelper by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.visiblePermissionDialog(hasCameraPermission)
    }

    LaunchedEffect(viewModel) {
        sideEffect.collect {
            when (it) {
                OCRSideEffect.OCRMoveToPermissionSetting -> {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                    )
                }
                else -> {}
            }
        }
    }





    if (state.showPermissionDialog) {
        MDSModal(
            icon = state.modalType.icon,
            title = state.modalType.title,
            description = state.modalType.description,
            negativeBtnText = "확인",
            positiveBtnText = "이동",
            onClickNegative = viewModel::onClickDialogNegative,
            onClickPositive = viewModel::onClickDialogPositive
        )
    }

    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(White)
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (!hasCameraPermission) {
                OCRCameraPermissionDeniedView(onClickRequestPermission = { /* TODO */ })
            } else {
                OCRCaptureView()
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "영수증의 처음과 끝이\n모두 포함되게 촬영해주세요",
                    style = Heading1,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                if (visibleHelper) { // TODO 최초 진입 시, 혹은 도움말 아이콘 클릭 시
                    OCRHelperView(onClickClose = { visibleHelper = !visibleHelper })
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
            if (false) { // TODO ocr loading
                CircularProgressIndicator(
                    modifier = Modifier.size(74.dp),
                    color = Mint03,
                    trackColor = White,
                    strokeWidth = 7.dp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRScreenPreview() {
    OCRScreen()
}