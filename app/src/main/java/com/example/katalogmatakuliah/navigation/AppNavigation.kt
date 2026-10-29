package com.example.katalogmatakuliah.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.katalogmatakuliah.ui.screens.DetailScreen
import com.example.katalogmatakuliah.ui.screens.FormScreen
import com.example.katalogmatakuliah.ui.screens.HomeScreen
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
) { NavHost(navController = navController,
        startDestination = HomeRoute) { composable<HomeRoute> {
            HomeScreen(
                onCourseClick = { courseId ->
                    navController.navigate(DetailRoute(courseId)) },
                onAddCourseClick = {
                    navController.navigate(FormRoute(courseId = null)) }) }

        composable<DetailRoute> { backStackEntry ->
            val route: DetailRoute = backStackEntry.toRoute()
            DetailScreen(
                courseId = route.courseId,
                onBackClick = {
                    navController.popBackStack()
                }, onEditClick = { courseId ->
                    navController.navigate(FormRoute(courseId = courseId)) }) }

        composable<FormRoute> { backStackEntry ->
            val route: FormRoute = backStackEntry.toRoute()
            FormScreen(
                courseId = route.courseId,
                onBackClick = {
                    navController.popBackStack() },
                onSaveSuccess = {
                    navController.popBackStack() }) } } }
