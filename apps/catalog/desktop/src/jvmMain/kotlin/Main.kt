import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import com.dexstudio.core.sharedui.catalog.CatalogApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DeXStudio Experimental Hub",
        state = WindowState(size = DpSize(1200.dp, 800.dp))
    ) {
        CatalogApp()
    }
}
