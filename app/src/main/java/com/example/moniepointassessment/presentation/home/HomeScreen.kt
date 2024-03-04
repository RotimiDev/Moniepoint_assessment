package com.example.moniepointassessment.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.moniepointassessment.presentation.component.BottomNav
import com.example.moniepointassessment.presentation.searchview.SearchQueryList

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    showSearchResultsOnly: Boolean = false,
    onMenuItemClicked: (String) -> Unit = {},
    onReadyToSearch: (Boolean) -> Unit = {}
) {

    if (!showSearchResultsOnly) {
        LocalFocusManager.current.clearFocus(true)
    }
    var showAnimations by remember { mutableStateOf(false) }

    SideEffect {
        showAnimations = true
    }

    ConstraintLayout {
        val (topAppBar, middleSection, bottomAppBar) = createRefs()

        val topModifier = Modifier.constrainAs(topAppBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        val middleModifier = Modifier.constrainAs(middleSection) {
            top.linkTo(topAppBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(bottomAppBar.top)
            height = Dimension.fillToConstraints
        }

        val bottomModifier = Modifier.constrainAs(bottomAppBar) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        // Animation: Slide downwards vertically
        AnimatedVisibility(
            visible = showAnimations,
            modifier = topModifier,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        ) {
            HomeAppBar(
                topModifier,
                showSearchResultsOnly,
                onReadyToSearch
            )
        }

        // Animation: Slide upwards vertically
        AnimatedVisibility(
            visible = showSearchResultsOnly,
            modifier = middleModifier,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 300, easing = LinearEasing)
            )
        ) {
            SearchQueryList(middleModifier)
        }

        if (!showSearchResultsOnly) {
            AnimatedVisibility(visible = showAnimations, modifier = middleModifier) {
                MainContent(
                    middleModifier
                )
            }

            // Animation: Slide upwards vertically
            AnimatedVisibility(
                visible = showAnimations,
                modifier = bottomModifier,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            ) {
                BottomNav(bottomModifier, onMenuItemClicked)
            }
        }
    }
}
