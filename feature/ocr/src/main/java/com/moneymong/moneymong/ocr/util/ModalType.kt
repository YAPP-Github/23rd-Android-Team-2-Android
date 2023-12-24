package com.moneymong.moneymong.ocr.util

import com.moneymong.moneymong.design_system.R.*

enum class ModalType(
    val icon: Int,
    val title: String,
    val description: String
) {
    CameraPermission(
        icon = drawable.ic_camera_modal,
        title = "카메라 접근을 허용하시겠습니까?",
        description = "영수증 스캔을 위해 필요합니다"
    ),
    GalleryPermission(
        icon = drawable.ic_photo_modal,
        title = "갤러리 접근 관한이 필요합니다",
        description = "설정에서 변경해주세요"
    )
}