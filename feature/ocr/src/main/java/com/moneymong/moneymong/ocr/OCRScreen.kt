package com.moneymong.moneymong.ocr

import android.Manifest.permission.*
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.google.gson.Gson
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.common.ext.hasPermission
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.common.util.DisposableEffectWithLifeCycle
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.ocr.view.OCRCameraPermissionDeniedView
import com.moneymong.moneymong.ocr.view.OCRCaptureView
import com.moneymong.moneymong.ocr.view.OCRDeniedBottomBar
import com.moneymong.moneymong.ocr.view.OCRHelperView
import com.moneymong.moneymong.ocr.view.OCRTopbarView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OCRScreen(
    modifier: Modifier = Modifier,
    viewModel: OCRViewModel = hiltViewModel(),
    navigateToOCRResult: (navOptions: NavOptions?, document: String) -> Unit,
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current
    var hasCameraPermission by remember { mutableStateOf(context.hasPermission(CAMERA)) }

    BackHandler(onBack = { navigateToLedger(false) })

    DisposableEffectWithLifeCycle(
        onResume = {
            hasCameraPermission = context.hasPermission(CAMERA)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.visiblePermissionDialog(hasCameraPermission)
    }

    viewModel.collectSideEffect {
        when (it) {
            is OCRSideEffect.OCRMoveToPermissionSetting -> {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                )
            }

            is OCRSideEffect.OCRPostDocumentApi -> {
                viewModel.postDocumentOCR(it.base64)
            }

            is OCRSideEffect.OCRNavigateToOCRResult -> {
                val documentString = it.document?.let { Gson().toJson(it) }.orEmpty()
                navigateToOCRResult(null, documentString)
            }

            else -> {}
        }
    }

    if (state.showPermissionDialog) {
        MDSModal(
            icon = state.modalType.icon,
            title = state.modalType.title,
            description = state.modalType.description,
            negativeBtnText = "허용 안 함",
            positiveBtnText = "허용",
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
                OCRCameraPermissionDeniedView(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    onClickRequestPermission = {
                        viewModel.eventEmit(
                            OCRSideEffect.OCRMoveToPermissionSetting
                        )
                    })
                OCRDeniedBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
            } else {
                OCRCaptureView(
                    onClickCapture = {
                        viewModel.eventEmit(OCRSideEffect.OCRPostDocumentApi(it))
                    }
                )
                if (!state.isLoading) {
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
                visibleHelp = hasCameraPermission,
                onClickHelp = viewModel::onClickHelper,
                onClickClose = { navigateToLedger(false) }
            )
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Black.copy(alpha = 0.6f))
                        .noRippleClickable { }, // 로딩 중 클릭 이벤트를 방지하기 위해 추가함
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(74.dp),
                        color = Mint03,
                        trackColor = White,
                        strokeWidth = 7.dp
                    )
                }
            }
            if (state.visibleHelper && hasCameraPermission) {
                OCRHelperView(onClickClose = viewModel::onClickHelper)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRScreenPreview() {
    OCRScreen(
        navigateToOCRResult = { navOptions, s -> },
        popBackStack = {},
        navigateToLedger = {})
}