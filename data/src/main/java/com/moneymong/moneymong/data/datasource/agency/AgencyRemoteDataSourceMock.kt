package com.moneymong.moneymong.data.datasource.agency

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import com.moneymong.moneymong.network.response.agency.AgencyGetResponse
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import com.moneymong.moneymong.network.response.agency.MyAgencyResponse
import com.moneymong.moneymong.network.response.agency.RegisterAgencyResponse
import kotlinx.coroutines.delay

class AgencyRemoteDataSourceMock : AgencyRemoteDataSource {

    override suspend fun registerAgency(request: AgencyRegisterRequest): Result<RegisterAgencyResponse> {
        return Result.success(RegisterAgencyResponse(0))
    }

    override suspend fun getAgencies(page: Int, size: Int): Result<AgenciesGetResponse> {
        delay(1000L)
        return agenciesMockOfSuccess[page]
    }

    override suspend fun fetchMyAgencyList(): Result<List<MyAgencyResponse>> {
        return Result.success(emptyList())
    }

    override suspend fun agencyCodeNumbers(
        agencyId: Long,
        data: AgencyJoinRequest
    ): Result<AgencyJoinResponse> {
        return Result.success(
            AgencyJoinResponse(
                certified = true,
            )
        )
    }

    private companion object {
        val agenciesMockOfSuccess = listOf(
            Result.success(
                AgenciesGetResponse(
                    agencies = listOf(
                        AgencyGetResponse(
                            id = 1,
                            name = "agency1",
                            headCount = 1,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 2,
                            name = "agency2",
                            headCount = 2,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 3,
                            name = "agency3",
                            headCount = 3,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 4,
                            name = "agency4",
                            headCount = 4,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 5,
                            name = "agency5",
                            headCount = 5,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 6,
                            name = "agency6",
                            headCount = 6,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 7,
                            name = "agency7",
                            headCount = 7,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 8,
                            name = "agency8",
                            headCount = 8,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 9,
                            name = "agency9",
                            headCount = 9,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 10,
                            name = "agency10",
                            headCount = 10,
                            type = "STUDENT_COUNCIL"
                        ),
                    )
                )
            ),
            Result.success(
                AgenciesGetResponse(
                    agencies = listOf(
                        AgencyGetResponse(
                            id = 11,
                            name = "agency11",
                            headCount = 11,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 12,
                            name = "agency12",
                            headCount = 12,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 13,
                            name = "agency13",
                            headCount = 13,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 14,
                            name = "agency14",
                            headCount = 14,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 15,
                            name = "agency15",
                            headCount = 15,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 16,
                            name = "agency16",
                            headCount = 16,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 17,
                            name = "agency17",
                            headCount = 17,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 18,
                            name = "agency18",
                            headCount = 18,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 19,
                            name = "agency19",
                            headCount = 19,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 20,
                            name = "agency20",
                            headCount = 20,
                            type = "STUDENT_COUNCIL"
                        )
                    )
                )
            ),
            Result.success(
                AgenciesGetResponse(
                    agencies = listOf(
                        AgencyGetResponse(
                            id = 21,
                            name = "agency21",
                            headCount = 21,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 22,
                            name = "agency22",
                            headCount = 22,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 23,
                            name = "agency23",
                            headCount = 23,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 24,
                            name = "agency24",
                            headCount = 24,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 25,
                            name = "agency25",
                            headCount = 25,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 26,
                            name = "agency26",
                            headCount = 26,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 27,
                            name = "agency27",
                            headCount = 27,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 28,
                            name = "agency28",
                            headCount = 28,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 29,
                            name = "agency29",
                            headCount = 29,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 30,
                            name = "agency30",
                            headCount = 30,
                            type = "STUDENT_COUNCIL"
                        )
                    )
                )
            ),
            Result.success(
                AgenciesGetResponse(
                    agencies = listOf(
                        AgencyGetResponse(
                            id = 31,
                            name = "agency31",
                            headCount = 31,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 32,
                            name = "agency32",
                            headCount = 32,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 33,
                            name = "agency33",
                            headCount = 33,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 34,
                            name = "agency34",
                            headCount = 34,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 35,
                            name = "agency35",
                            headCount = 35,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 36,
                            name = "agency36",
                            headCount = 36,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 37,
                            name = "agency37",
                            headCount = 37,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 38,
                            name = "agency38",
                            headCount = 38,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 39,
                            name = "agency39",
                            headCount = 39,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 40,
                            name = "agency40",
                            headCount = 40,
                            type = "STUDENT_COUNCIL"
                        )
                    )
                )
            ),
            Result.success(
                AgenciesGetResponse(
                    agencies = listOf(
                        AgencyGetResponse(
                            id = 41,
                            name = "agency41",
                            headCount = 41,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 42,
                            name = "agency42",
                            headCount = 42,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 43,
                            name = "agency43",
                            headCount = 43,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 44,
                            name = "agency44",
                            headCount = 44,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 45,
                            name = "agency45",
                            headCount = 45,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 46,
                            name = "agency46",
                            headCount = 46,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 47,
                            name = "agency47",
                            headCount = 47,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 48,
                            name = "agency48",
                            headCount = 48,
                            type = "STUDENT_COUNCIL"
                        ),
                        AgencyGetResponse(
                            id = 49,
                            name = "agency49",
                            headCount = 49,
                            type = "IN_SCHOOL_CLUB"
                        ),
                        AgencyGetResponse(
                            id = 50,
                            name = "agency50",
                            headCount = 50,
                            type = "STUDENT_COUNCIL"
                        )
                    )
                )
            ),
            Result.failure(
                Throwable("Mock Failure Message")
            )
        )
    }
}