package com.dexstudio.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dexstudio.core.sharedui.catalog.CatalogApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Enforce Rule 9: Edge-to-Edge compliance
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        setContent {
            CatalogApp()
        }
    }
}
