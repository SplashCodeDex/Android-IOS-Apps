package com.dexstudio.core.sharedui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import apphide.dexstudio_core_sdk.shared_ui.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DeXLottieAnimation(
    resourcePath: String,
    modifier: Modifier = Modifier,
    iterations: Int = Compottie.IterateForever,
    contentDescription: String? = null
) {
    // Note: resourcePath should be just the file name inside composeResources/files/ (e.g. "lottie_loading.json")
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/$resourcePath").decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations
    )
    Image(
        painter = rememberLottiePainter(
            composition = composition,
            progress = { progress }
        ),
        contentDescription = contentDescription,
        modifier = modifier
    )
}
