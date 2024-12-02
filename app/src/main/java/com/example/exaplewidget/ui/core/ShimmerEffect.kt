package com.example.exaplewidget.ui.core

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

/**
 * Добавляет эффект шиммера для UI элемента.
 *
 * Эффект создается с использованием линейного градиента, который плавно перемещается
 * по горизонтали.
 *
 * Пример использования:
 * ```kotlin
 * Box(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .height(100.dp)
 *         .shimmerEffect()
 * )
 * ```
 *
 * @return Modifier с примененным эффектом шиммера.
 */
fun Modifier.shimmerEffect() = shimmerEffect(
    listOf(
        Color(0xFFF2F2F2),
        Color(0xFFF8F8F8),
        Color(0xFFF2F2F2),
    )
)

/**
 * Добавляет эффект шиммера с возможностью кастомизации цветов градиента.
 *
 * @param colors Список цветов, используемых для создания анимационного градиента.
 *               Обычно состоит из трех оттенков: базовый, акцентный, базовый.
 *
 * Пример использования:
 * ```kotlin
 * Box(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .height(100.dp)
 *         .shimmerEffect(
 *             colors = listOf(
 *                 Color.Gray,
 *                 Color.LightGray,
 *                 Color.Gray
 *             )
 *         )
 * )
 * ```
 *
 * @return Modifier с примененным эффектом шиммера.
 */
fun Modifier.shimmerEffect(colors: List<Color>): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}