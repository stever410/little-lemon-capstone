package com.example.littlelemon

interface Destinations {
    val route: String
}

object HomeDest : Destinations {
    override val route: String = "Home"
}

object OnboardingDest : Destinations {
    override val route: String = "Onboarding"
}

object ProfileDest : Destinations {
    override val route: String = "Profile"
}