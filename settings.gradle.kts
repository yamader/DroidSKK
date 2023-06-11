pluginManagement {
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

  versionCatalogs {
    create("libs") {
      // versions
      version("android-plugin", "8.1.1")
      version("compose-compiler", "1.5.3")
      version("kotlin", "1.9.10")

      // libraries
      library("androidx-appcompat", "androidx.appcompat:appcompat:1.6.1")
      library("androidx-preference", "androidx.preference:preference-ktx:1.2.0")

      // plugins
      plugin("android-application", "com.android.application").versionRef("android.plugin")
      plugin("android-library", "com.android.library").versionRef("android.plugin")
      plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
    }
  }
}

rootProject.name = "DroidSKK"

include(":app")
