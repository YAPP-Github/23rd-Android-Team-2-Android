package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.user.UserEntity
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(

) : BaseUseCase<Unit, Result<UserEntity>>() {
    //TODO 마이몽 유저 정보조회 api
    override suspend fun invoke(data: Unit): Result<UserEntity> {
        return Result.success(
            UserEntity(
                id = 3,
                name = "김기서",
                email = "hihi",
                university = "길동대학교",
                grade = 4
            )
        )
    }
}