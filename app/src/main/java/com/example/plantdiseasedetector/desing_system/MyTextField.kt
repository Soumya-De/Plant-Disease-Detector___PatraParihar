package com.example.plantdiseasedetector.desing_system
import com.example.plantdiseasedetector.ui.theme.PlantDiseaseDetectorTheme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {

    if (isPassword) {
        PasswordTextField(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textFieldState = textFieldState,
            hint = hint,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick
        )
    } else {
        TextTextField(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            trailingText = trailingText,
            textFieldState = textFieldState,
            hint = hint,
            keyboardType = keyboardType,
            onLeadingClick = onLeadingClick,
            onTrailingClick = onTrailingClick
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {
    BasicTextField2(
        state = textFieldState,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorator = { innerTextField ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (textFieldState.text.isEmpty()) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        innerTextField()
                    }

                    if (trailingIcon != null) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() }
                        )
                    } else if (trailingText != null) {
                        Text(
                            text = trailingText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    trailingText: String? = null,
    textFieldState: TextFieldState,
    hint: String,
    onLeadingClick: () -> Unit = {},
    onTrailingClick: () -> Unit = {},
) {
    BasicSecureTextField(
        state = textFieldState,
        textObfuscationMode = TextObfuscationMode.Hidden,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        keyboardType = KeyboardType.Password,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        decorator = { innerTextField ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier.clickable { onLeadingClick() }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (textFieldState.text.isEmpty()) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        innerTextField()
                    }

                    if (trailingIcon != null) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() }
                        )
                    } else if (trailingText != null) {
                        Text(
                            text = trailingText,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable { onTrailingClick() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider(modifier = Modifier.alpha(0.7f))
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true)
@Composable
private fun MyTextFieldPreview() {
    PlantDiseaseDetectorTheme {
        MyTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            trailingText = "Forgot?",
            textFieldState = TextFieldState(),
            hint = "EmaEmaiEmaEmailEmailEmailEmaililEmaEmailEmailEmailEmaililEmaEmailEmailEmailEmaililEmaEmailEmailEmailEmaililEmaEmailEmailEmailEmailillEmailEmailEmailil",
        )
    }
}