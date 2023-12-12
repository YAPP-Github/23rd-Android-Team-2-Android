package com.moneymong.moneymong.feature.sign.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpGradeView(modifier : Modifier = Modifier ) {

}

@Composable
fun GridOfButtons() {
    val buttons = listOf(
        Icons.Default.Person to "Person",
        Icons.Default.Favorite to "Favorite",
        Icons.Default.MailOutline to "Mail",
        Icons.Default.Phone to "Phone",
        Icons.Default.Person to "Another Person"
    )

    LazyColumn {
        items(items = buttons.chunked(3)) { rowButtons ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                for ((icon, label) in rowButtons) {
                    GridButton(icon = icon, label = label)
                }
            }
        }
    }
}

@Composable
fun GridButton(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
//            .weight(1f)
    ) {
        IconButton(onClick = { /* Handle button click here */ }) {
            Icon(imageVector = icon, contentDescription = label)
        }
        Text(text = label, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun GridOfButtonsPreview() {
    GridOfButtons()
}