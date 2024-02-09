package com.example.member.component

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.member.util.MemberRoundRectShadow
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.tag.MDSTag
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity

@Composable
fun MemberCardView(
    modifier: Modifier = Modifier,
    agencyId: Int,
    memberList: List<AgencyUserEntity>,
    memberMyInfoId: Long,
    memberMyInfo: AgencyUserEntity,
    memberMyInfoChanged: (Long, Long, String, String) -> Unit,
    invitationCode: String,
    isReInvitationCode: (Long) -> Unit,
    onCopyChange: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("invitation_code", text)
        clipboard.setPrimaryClip(clip)
    }

    memberList.find { it.userId == memberMyInfoId }?.let {
        memberMyInfoChanged(it.id, it.userId, it.nickname, it.agencyUserRole)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .MemberRoundRectShadow()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(44.dp),
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = memberMyInfo.nickname,
                    style = Body4,
                    color = Gray10
                )

                MDSTag(
                    modifier = Modifier.padding(start = 6.dp),
                    text = if (memberMyInfo.agencyUserRole == "MEMBER") "일반멤버" else "운영진",
                    backgroundColor = if (memberMyInfo.agencyUserRole == "MEMBER") Mint03 else Blue04,
                    contentColor = White,
                )

            }

            if (memberMyInfo.agencyUserRole == "STAFF") {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = Gray02
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "초대코드 $invitationCode",
                        style = Body3,
                        color = Gray10
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 63.dp)
                            .noRippleClickable {
                                onCopyChange(true)
                                copyToClipboard(invitationCode)
                            },
                    ) {
                        Text(
                            text = "복사",
                            style = Body3,
                            color = Blue04
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.img_copy),
                            modifier = Modifier
                                .padding(start = 2.dp, top = 1.dp, bottom = 1.dp)
                                .size(18.dp),
                            contentDescription = null,
                            tint = Blue04
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .noRippleClickable {
                                isReInvitationCode(agencyId.toLong())
                            }
                    ) {
                        Text(
                            text = "재발급",
                            style = Body3,
                            color = Blue04
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.img_reissue),
                            modifier = Modifier
                                .padding(start = 2.dp, top = 1.dp, bottom = 1.dp)
                                .size(18.dp),
                            contentDescription = null,
                            tint = Blue04

                        )
                    }

                }

            }

        }
    }
}