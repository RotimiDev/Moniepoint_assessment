package com.example.moniepointassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moniepointassessment.presentation.calculate.CalculateSuccessView
import com.example.moniepointassessment.data.BottomNavItem
import com.example.moniepointassessment.presentation.calculate.CalculateView
import com.example.moniepointassessment.presentation.home.HomeScreen
import com.example.moniepointassessment.presentation.shipment.ShipmentScreen
import com.example.moniepointassessment.ui.theme.MoniepointAssessmentTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoniepointAssessmentTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            testTagsAsResourceId = true
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppView(navController)
                }
            }
        }
    }
}

@Composable
private fun AppView(navController: NavHostController) {
    var showSearchResultsOnly by remember { mutableStateOf(false) }

    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(showSearchResultsOnly, {
                if (it.isNotEmpty()) {
                    navController.navigate(it)
                }
            }, {
                showSearchResultsOnly = it
            })
        }

        composable(BottomNavItem.Calculate.route) {
            CalculateView(
                { navController.navigate(BottomNavItem.Complete.route) },
                { navController.popBackStack() })
        }

        composable(BottomNavItem.Shipment.route) {
            ShipmentScreen(onBackPressed = {
                navController.popBackStack()
            })
        }

        composable(BottomNavItem.Complete.route) {
            CalculateSuccessView {
                navController.navigate(BottomNavItem.Home.route)
            }
        }
    }
}