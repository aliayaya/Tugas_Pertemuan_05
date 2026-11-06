package com.example.katalogmatakuliah.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.katalogmatakuliah.ui.screens.DetailScreen
import com.example.katalogmatakuliah.ui.screens.FormScreen
import com.example.katalogmatakuliah.ui.screens.HomeScreen
import com.example.katalogmatakuliah.ui.viewmodel.DetailViewModel
import com.example.katalogmatakuliah.ui.viewmodel.FormViewModel
import com.example.katalogmatakuliah.ui.viewmodel.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val courseId: Int)

@Serializable
data class FormRoute(val courseId: Int? = null)

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            val viewModel: HomeViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
                viewModel.loadCourses()
            }

            HomeScreen(
                uiState = uiState,
                onSearchQueryChange = viewModel::onSearchQueryChanged,
                onCourseClick = { courseId ->
                    navController.navigate(DetailRoute(courseId))
                },
                onAddCourseClick = {
                    navController.navigate(FormRoute(courseId = null))
                }
            )
        }

        composable<DetailRoute> { backStackEntry ->
            val route: DetailRoute = backStackEntry.toRoute()
            val viewModel: DetailViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
                viewModel.loadCourse(route.courseId)
            }

            DetailScreen(
                uiState = uiState,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditClick = { courseId ->
                    navController.navigate(FormRoute(courseId = courseId))
                }
            )
        }

        composable<FormRoute> { backStackEntry ->
            val route: FormRoute = backStackEntry.toRoute()
            val viewModel: FormViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(route.courseId) {
                viewModel.initForm(route.courseId)
            }

            LaunchedEffect(uiState.isSaveSuccess) {
                if (uiState.isSaveSuccess) {
                    viewModel.resetSaveSuccess()
                    navController.popBackStack()
                }
            }

            FormScreen(
                uiState = uiState,
                onCodeChange = viewModel::onCodeChanged,
                onNameChange = viewModel::onNameChanged,
                onSksChange = viewModel::onSksChanged,
                onSemesterChange = viewModel::onSemesterChanged,
                onDescriptionChange = viewModel::onDescriptionChanged,
                onSaveClick = {
                    viewModel.saveCourse()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
