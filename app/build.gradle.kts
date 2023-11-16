import java.io.FileNotFoundException
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "net.dyama.droidskk"
  compileSdk = 34

  defaultConfig {
    applicationId = "net.dyama.droidskk"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "0.1"

    vectorDrawables.useSupportLibrary = true
  }

  signingConfigs {
    try {
      val keyPropsFile = rootProject.file("keystore.properties")
      val keyProps = Properties().apply {
        keyPropsFile.inputStream().use { load(it) }
      }

      create("release") {
        keyAlias = keyProps["keyAlias"] as String
        keyPassword = keyProps["storePassword"] as String
        storeFile = file(keyProps["storeFile"] as String)
        storePassword = keyProps["storePassword"] as String
      }
    } catch(e: FileNotFoundException) {
      // key.propertiesが無いとkeyPropsFile.inputStream()がコケる
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      signingConfig = try {
        signingConfigs.getByName("release")
      } catch(e: UnknownDomainObjectException) {
        null // signingConfigが無いとき
      }

      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    freeCompilerArgs += listOf(
      "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    )
  }

  buildFeatures {
    buildConfig = true
    compose = true
    viewBinding = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(files("libs/jdbm-1.0.jar"))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.preference)

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.material3)
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.ui.tooling.preview)

  debugImplementation(libs.compose.ui.test.manifest)
  debugImplementation(libs.compose.ui.tooling)
}
