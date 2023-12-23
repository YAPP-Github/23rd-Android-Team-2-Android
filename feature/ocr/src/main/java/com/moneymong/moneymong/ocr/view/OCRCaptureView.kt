package com.moneymong.moneymong.ocr.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.common.ext.toMultipart
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.ocr.OCRSideEffect
import com.moneymong.moneymong.ocr.OCRViewModel
import org.orbitmvi.orbit.compose.collectAsState
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OCRCaptureView(
    modifier: Modifier = Modifier,
    viewModel: OCRViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val sideEffect = viewModel.container.sideEffectFlow
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    LaunchedEffect(viewModel) {
        sideEffect.collect {
            when (it) {
                is OCRSideEffect.OCRTakePicture -> {
                    val imageFile = File(context.externalCacheDir, "temp_${System.currentTimeMillis()}.jpeg")
                    val outputFileOptions = ImageCapture.OutputFileOptions
                        .Builder(imageFile)
                        .build()
                    val mainExecutor = ContextCompat.getMainExecutor(context)
                    cameraController.takePicture(
                        outputFileOptions,
                        mainExecutor,
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                outputFileResults.savedUri
                                Log.d("OCRCaptureView", "${imageFile.toMultipart()}")
                            }

                            override fun onError(exception: ImageCaptureException) {
                            }
                        }
                    )
                }
            }
        }
    }

    Scaffold(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    setBackgroundColor(Gray10.toArgb())
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )
    }
}