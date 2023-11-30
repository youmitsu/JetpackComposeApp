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
include(":feature:lists")
include(":feature:list")
include(":data:api")
include(":data:repository")
include(":feature:reminder")
include(":core:util")
