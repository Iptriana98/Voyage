package com.iptriana.voyage.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iptriana.voyage.R
import com.iptriana.voyage.ui.theme.VoyageTheme
import com.iptriana.voyage.ui.theme.captionTextStyle


@Composable
fun SimpleUserInput(
    text: String? = null,
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null
) {
    VoyageUserInput(
        caption = if (text == null) caption else null,
        text = text ?: "",
        vectorImageId = vectorImageId
    )
}

@Composable
fun VoyageUserInput(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    tint: Color = LocalContentColor.current
) {
    VoyageBaseUserInput(
        modifier = modifier,
        onClick = onClick,
        caption = caption,
        vectorImageId = vectorImageId,
        tintIcon = { text.isNotEmpty() },
        tint = tint
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge.copy(color = tint))
    }
}

@Composable
fun VoyageBaseUserInput(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    showCaption: () -> Boolean = { true },
    tintIcon: () -> Boolean,
    tint: Color = LocalContentColor.current,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = MaterialTheme.colorScheme.inversePrimary
    ) {
        Row(Modifier.padding(all = 12.dp)) {
            if (vectorImageId != null) {
                Icon(
                    modifier = Modifier.size(24.dp, 24.dp),
                    painter = painterResource(id = vectorImageId),
                    tint = if (tintIcon()) tint else Color(0x80FFFFFF),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
            }
            if (caption != null && showCaption()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = caption,
                    style = (captionTextStyle).copy(color = tint)
                )
                Spacer(Modifier.width(8.dp))
            }
            Row(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun PreviewInput() {
    VoyageTheme {
        Surface {
            VoyageBaseUserInput(
                tintIcon = { true },
                vectorImageId = R.drawable.ic_plane,
                caption = "Caption",
                showCaption = { true }
            ) {
                Text(text = "text", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
