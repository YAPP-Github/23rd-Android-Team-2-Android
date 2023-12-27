package com.moneymong.moneymong.design_system.component.textfield.util

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Red03

internal interface MDSTextFieldAttribute {
    val titleColor: Color
    val textColor: Color
    val underLineColor: Color
    val countVisible: Boolean
}

internal sealed interface MDSTextFieldState : MDSTextFieldAttribute {
    data object Default :
        MDSTextFieldState {
        override val titleColor: Color = Gray06
        override val textColor: Color = Gray04
        override val underLineColor: Color = Gray02
        override val countVisible: Boolean = true
    }

    data object Active :
        MDSTextFieldState {
        override val titleColor: Color = Blue04
        override val textColor: Color = Gray08
        override val underLineColor: Color = Blue04
        override val countVisible: Boolean = true
        val countColor = Gray05
    }

    data object Error :
        MDSTextFieldState {
        override val titleColor: Color = Red03
        override val textColor: Color = Gray08
        override val underLineColor: Color = Red03
        override val countVisible: Boolean = true
        val errorColor = Red03
        val countColor = Red03
    }

    data object Filled :
        MDSTextFieldState {
        override val titleColor: Color = Gray06
        override val textColor: Color = Gray10
        override val underLineColor: Color = Gray02
        override val countVisible: Boolean = true
    }
}

internal fun getMDSTextFieldState(
    text: String,
    isError: Boolean = false,
    isFilled: Boolean
): MDSTextFieldState {
    return if (text.isEmpty()) {
        MDSTextFieldState.Default
    } else if (isError) {
        MDSTextFieldState.Error
    } else if (isFilled) {
        MDSTextFieldState.Filled
    } else {
        MDSTextFieldState.Active
    }
}