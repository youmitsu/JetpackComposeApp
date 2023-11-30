plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    alias(libs.plugins.android.library) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

detekt {
    parallel = true
    buildUponDefaultConfig = true

    ignoreFailures = true
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    }
}
