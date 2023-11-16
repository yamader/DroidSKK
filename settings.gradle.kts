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

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven("https://androidx.dev/storage/compose-compiler/repository/")
  }

  versionCatalogs {
    create("libs") {
      // versions
      version("android-plugin", "8.3.0-alpha13")
      version("kotlin", "2.0.0-Beta1")
      version("compose-compiler", "1.5.5-dev-k2.0.0-Beta1-06b8ae672a4")

      // libraries
      library("androidx-activity-compose", "androidx.activity:activity-compose:1.8.1")
      library("androidx-appcompat", "androidx.appcompat:appcompat:1.6.1")
      library("androidx-core", "androidx.core:core-ktx:1.12.0")
      library("androidx-lifecycle-runtime", "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
      library("androidx-preference", "androidx.preference:preference-ktx:1.2.1")

      library("compose-bom", "androidx.compose:compose-bom:2023.10.01")
      library("compose-material3", "androidx.compose.material3", "material3").withoutVersion()
      library("compose-ui", "androidx.compose.ui", "ui").withoutVersion()
      library("compose-ui-graphics", "androidx.compose.ui", "ui-graphics").withoutVersion()
      library("compose-ui-test-manifest", "androidx.compose.ui", "ui-test-manifest").withoutVersion()
      library("compose-ui-tooling", "androidx.compose.ui", "ui-tooling").withoutVersion()
      library("compose-ui-tooling-preview", "androidx.compose.ui", "ui-tooling-preview").withoutVersion()

      // plugins
      plugin("android-application", "com.android.application").versionRef("android.plugin")
      plugin("android-library", "com.android.library").versionRef("android.plugin")
      plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
    }
  }
}

rootProject.name = "DroidSKK"

include(":app")
