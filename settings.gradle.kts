pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "AppHide"

include(":apps:apphide:android")
include(":apps:apphide:desktop")
include(":apps:catalog:android")
include(":apps:catalog:desktop")
include(":apps:calculator:android")

val useLocalCore = true // Simulate local dev environment
if (useLocalCore) {
    include(":dexstudio-core-sdk:shared-ui")
    include(":dexstudio-core-sdk:shared-data")
}
