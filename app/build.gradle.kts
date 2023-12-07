import org.jetbrains.kotlin.base.kapt3.KaptOptions
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.application)
    alias(libs.plugins.sample.compose)
}

android {
    namespace = "com.example.jetpackcomposeapp"

    defaultConfig {
        applicationId = "com.example.jetpackcomposeapp"
    }

//    dynamicFeatures += setOf(":dynamic")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":feature:list"))
    implementation(project(":feature:reminder"))
    implementation(project(":feature:registration"))
    implementation(project(":feature:onboarding"))

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Arch Components
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}