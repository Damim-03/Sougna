import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.sougna.presentation.view.pages.HomePage
import com.example.sougna.presentation.view.IntroScreen
import com.example.sougna.presentation.view.pages.AddProductScreen
import com.example.sougna.presentation.view.pages.ProfilePage

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "intro") {
        composable("intro") { IntroScreen(navController) }
        composable("home") { HomePage(navController) }
        composable("profile") { ProfilePage(navController) }
        composable("addProduct") { AddProductScreen(navController) } // ✅ Ensure route matches the button
        composable("productList") { ProductListScreen() }
    }
}

