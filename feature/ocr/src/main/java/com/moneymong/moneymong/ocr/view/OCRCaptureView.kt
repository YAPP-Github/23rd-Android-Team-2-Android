package com.moneymong.moneymong.ocr.view

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.moneymong.moneymong.common.ext.encodingBase64
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.ocr.util.bounceClick
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OCRCaptureView(
    modifier: Modifier = Modifier,
    onClickCapture: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Scaffold(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
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
            val shadowBlurRadius = 12.dp
            val shadowColor = Mint03.copy(alpha = 0.57f).toArgb()
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Black.copy(alpha = 0.6f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // TODO MVP 에서는 제외되는 기능입니다.
//                    Box(
//                        modifier = Modifier
//                            .padding(start = 36.dp)
//                            .size(40.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .border(width = 1.dp, color = Gray02, shape = RoundedCornerShape(8.dp))
//                            .background(Gray05),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        if (false) { // TODO 갤러리 접근 권한이 있다면
//                            Image(
//                                modifier = Modifier.size(40.dp),
//                                painter = painterResource(id = 0), // TODO
//                                contentDescription = null
//                            )
//                        }
//
//                    }
                    Icon(
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .size(60.dp)
                            .bounceClick {
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
                                            val base64String = outputFileResults.savedUri?.encodingBase64(context).orEmpty()
                                            onClickCapture(base64String)
                                        }

                                        override fun onError(exception: ImageCaptureException) {
                                        }
                                    }
                                )
                            },
                        painter = painterResource(id = R.drawable.ic_camera_btn),
                        contentDescription = null,
                        tint = Color.White
                    )
                    //Spacer(modifier = Modifier.width(76.dp))
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 106.dp, end = 20.dp)
                        .drawBehind {
                            drawIntoCanvas {
                                val paint = Paint()
                                val frameworkPaint = paint.asFrameworkPaint()
                                frameworkPaint.color = shadowColor
                                frameworkPaint.maskFilter = BlurMaskFilter(
                                    shadowBlurRadius.toPx(),
                                    BlurMaskFilter.Blur.NORMAL
                                )
                                it.drawRect(
                                    left = 0f,
                                    top = 0f,
                                    right = size.width,
                                    bottom = size.height,
                                    paint = paint
                                )
                            }
                        }
                        .height(4.dp)
                        .clip(RoundedCornerShape(100))
                        .background(Mint03)
                )
            }
        }
    }
}