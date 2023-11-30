@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.sample.compose)
}

android {
    namespace = "com.example.feature.list"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":data:repository"))
    implementation(project(":model"))

    // Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
}