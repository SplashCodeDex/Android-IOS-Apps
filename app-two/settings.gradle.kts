rootProject.name = "app-two"
include(":app")

val useLocalCore = true // Simulate local dev environment
if (useLocalCore) {
    includeBuild("../company-core-sdk") {
        dependencySubstitution {
            substitute(module("com.company.core:shared-ui")).using(project(":shared-ui"))
            substitute(module("com.company.core:shared-data")).using(project(":shared-data"))
        }
    }
}
