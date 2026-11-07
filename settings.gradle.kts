pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") { content { includeGroup("com.github.topjohnwu.libsu") } }
    }
}
rootProject.name = "Forkly"
include(":app")
if (providers.gradleProperty("distribution").orNull == "play") include(":local_gpu")
