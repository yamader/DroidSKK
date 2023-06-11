import java.io.FileNotFoundException
import java.util.*

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
      val keyPropsFile = rootProject.file("key.properties")
      val keyProps = Properties().apply {
        keyPropsFile.inputStream().use { load(it) }
      }

      create("release") {
        storeFile = file(keyProps["storeFile"] as String)
        storePassword = keyProps["storePassword"] as String
        keyAlias = keyProps["keyAlias"] as String
        keyPassword = keyProps["storePassword"] as String
      }
    } catch(e: FileNotFoundException) {
      // key.propertiesが無いとkeyPropsFile.inputStream()がコケる
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"))
      signingConfig = try {
        signingConfigs.getByName("release")
      } catch(e: UnknownDomainObjectException) {
        null // signingConfigが無いとき
      }
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  buildFeatures {
    buildConfig = true
    viewBinding = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
}

dependencies {
  implementation(files("libs/jdbm-1.0.jar"))
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.preference)
}
