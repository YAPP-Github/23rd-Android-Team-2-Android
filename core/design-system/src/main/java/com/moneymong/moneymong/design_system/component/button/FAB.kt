package com.moneymong.moneymong.design_system.component.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MDSFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconResource: Int,
    containerColor: Color,
    contentColor: Color = White
) {
    FloatingActionButton(
        modifier = modifier.size(48.dp),
        onClick = onClick,
        shape = CircleShape,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = iconResource),
            contentDescription = "Floating Action Button"
        )
    }
}