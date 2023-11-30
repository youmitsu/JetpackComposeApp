@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    namespace = "com.example.core.util"
}
