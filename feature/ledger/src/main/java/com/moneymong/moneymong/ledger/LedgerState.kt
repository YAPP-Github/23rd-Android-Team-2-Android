package com.moneymong.moneymong.ledger

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerDetailEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity
import com.moneymong.moneymong.domain.entity.member.MemberListEntity
import com.moneymong.moneymong.ledger.view.LedgerTransactionType
import java.time.LocalDate

data class LedgerState(
    val isAgencyExistLoading: Boolean = true,
    val isLedgerTransactionLoading: Boolean = true,
    val isMyAgencyLoading: Boolean = true,
    val isAgencyMemberLoading: Boolean = true,
    val agencyId: Int = 0,
    val userId: Int = 0,
    val isExistLedger: Boolean = false,
    val showBottomSheet: Boolean = false,
    val ledgerTransaction: LedgerTransactionListEntity? = null,
    val transactionType: LedgerTransactionType = LedgerTransactionType.전체,
    val currentDate: LocalDate = LocalDate.now(),
    val visibleError: Boolean = false,
    val visibleSnackbar: Boolean = false,
    val agencyList: List<MyAgencyEntity> = emptyList(),
    val memberList: List<AgencyUserEntity> = emptyList()
) : State {

    val filterTransactionList: List<LedgerDetailEntity>
        get() = if (transactionType == LedgerTransactionType.전체) {
            ledgerTransaction?.ledgerDetails.orEmpty()
        } else {
            ledgerTransaction?.ledgerDetails?.filter { it.fundType == transactionType.type }.orEmpty()
        }

    val hasTransaction: Boolean
        get() = filterTransactionList.isNotEmpty()

    val existAgency: Boolean
        get() = agencyList.isNotEmpty()

    val currentAgency: MyAgencyEntity?
        get() = agencyList.find { it.id == agencyId }

    val isStaff: Boolean
        get() = memberList.find { it.userId.toInt() == userId }?.agencyUserRole.orEmpty() == "STAFF"

    val isLoading: Boolean
        get() = isAgencyExistLoading || isLedgerTransactionLoading || isMyAgencyLoading || isAgencyMemberLoading
}
