pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JetpackComposeApp"
include(":app")
include(":model")
include(":core:ui")
include(":feature:onboarding")
include(":feature:meigen")
include(":feature:reminder")
include(":data:api")
include(":data:repository")
include(":core:util")
include(":data:preferences")
include(":data:local")
