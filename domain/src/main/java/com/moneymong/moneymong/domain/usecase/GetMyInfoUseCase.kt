package com.moneymong.moneymong.domain.usecase

import com.moneymong.moneymong.domain.entity.mymong.MyInfoEntity
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor() {
    suspend operator fun invoke(): Result<MyInfoEntity> {
        return Result.success(
            MyInfoEntity(
                name = "마이몽",
                email = "mymong@yapp.com",
                university = "마이몽대학교",
                grade = 999
            )
        )
    }
}