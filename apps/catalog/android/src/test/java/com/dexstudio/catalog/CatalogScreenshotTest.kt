package com.dexstudio.catalog

import androidx.compose.ui.test.junit4.createComposeRule
import com.dexstudio.core.sharedui.catalog.CatalogApp
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = RobolectricDeviceQualifiers.Pixel7)
class CatalogScreenshotTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCatalogAppBaseline() {
        composeTestRule.setContent {
            CatalogApp()
        }
        
        // Capture baseline screenshot via Roborazzi
        composeTestRule.onNode(androidx.compose.ui.test.hasTestTag("CatalogApp")).captureRoboImage()
    }
}
