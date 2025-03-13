import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.juansanz.kmpmovies.data.database.MoviesDao
import org.juansanz.kmpmovies.ui.screens.Navigation

@Composable
@Preview
fun App(moviesDao: MoviesDao) {
    Navigation(moviesDao)
}