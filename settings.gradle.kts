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

rootProject.name = "My Application"

include(":app")

val useLocalCore = true // Simulate local dev environment
if (useLocalCore) {
    includeBuild("company-core-sdk") {
        dependencySubstitution {
            substitute(module("com.company.core:shared-ui")).using(project(":shared-ui"))
            substitute(module("com.company.core:shared-data")).using(project(":shared-data"))
        }
    }
}
