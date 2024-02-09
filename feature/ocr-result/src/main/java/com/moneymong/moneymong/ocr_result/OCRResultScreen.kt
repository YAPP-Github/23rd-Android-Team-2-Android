package com.moneymong.moneymong.ocr_result

import android.util.Base64
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.gson.Gson
import com.moneymong.moneymong.common.ext.base64ToFile
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.ocr_result.view.OCRResultBottomView
import com.moneymong.moneymong.ocr_result.view.OCRResultImageGuideView
import com.moneymong.moneymong.ocr_result.view.OCRResultTopbarView
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OCRResultScreen(
    modifier: Modifier = Modifier,
    viewModel: OCRResultViewModel = hiltViewModel(),
    document: DocumentEntity?,
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    navigateToOCRDetail: (navOptions: NavOptions?, document: String) -> Unit,
    popBackStack: () -> Unit,
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var visibleGuide by remember { mutableStateOf(true) }
    var scale by remember { mutableStateOf(1f) }
    val result = document?.images?.first()?.receipt?.result
    val decodeImage by remember {
        mutableStateOf(
            Base64.decode(
                state.receiptImage,
                Base64.DEFAULT
            )
        )
    }

    LaunchedEffect(Unit) {
        viewModel.updateDocument(document)
        viewModel.reduceReceiptFile(state.receiptImage.base64ToFile(context))
    }

    viewModel.collectSideEffect {
        when (it) {
            is OCRResultSideEffect.OCRResultShowSnackbar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "일부 내용을 스캔하지 못했습니다",
                        withDismissAction = true,
                        actionLabel = ""
                    )
                }
            }

            is OCRResultSideEffect.OCRResultNavigateToLedger -> {
                navigateToLedger(true)
            }

            is OCRResultSideEffect.OCRResultNavigateToOCRDetail -> {
                val documentString = it.document?.let { Gson().toJson(it) }.orEmpty()
                navigateToOCRDetail(null, documentString)
            }
        }
    }

    Scaffold(
        snackbarHost = {
            MDSSnackbarHost(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                hostState = snackbarHostState
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Black)
                .padding(it)
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 214.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            if (visibleGuide) visibleGuide = false
                            scale = (scale * zoom).coerceIn(1f, 3f)
                        }
                    },
                model = decodeImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            OCRResultTopbarView(
                modifier = Modifier.align(Alignment.TopCenter),
                onClickBack = popBackStack,
                onClickClose = popBackStack
            )
            OCRResultBottomView(
                modifier = Modifier.align(Alignment.BottomCenter),
                source = result?.storeInfo?.name?.text.orEmpty(),
                amount = state.formattedPrice,
                date = state.formattedDate,
                time = state.formattedTime,
                btnEnabled = state.btnEnabled,
                onClickRetryOCR = popBackStack,
                onClickRegister = viewModel::postReceiptImage,
                onClickEdit = viewModel::onClickOCREdit
            )
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = visibleGuide,
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = FastOutLinearInEasing
                    )
                )
            ) {
                OCRResultImageGuideView()
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(74.dp)
                        .align(Alignment.Center),
                    color = Blue04,
                    trackColor = Blue01,
                    strokeWidth = 7.dp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultScreenPreview() {
    OCRResultScreen(
        document = null,
        navigateToLedger = {},
        popBackStack = {},
        navigateToOCRDetail = { navOptions, s -> })
}