package com.jargcode.storechallenge.navigation.transitions

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.navigation.NavBackStackEntry

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(400)
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutLinearInEasing
        )
    )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(400)
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutLinearInEasing
        )
    )
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(400)
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutLinearInEasing
        )
    )
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(400)
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutLinearInEasing
        )
    )
}

val fadeInTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )
}

val fadeOutTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        )
    )
}