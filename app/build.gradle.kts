import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.plugin)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.weather.monitoring.app"
    compileSdk = 34
    val localProperties = rootProject.file("local.properties")
    val properties = Properties()
    properties.load(localProperties.inputStream())
    val weatherAPIKey = properties.getProperty("API_KEY")

    defaultConfig {
        applicationId = "com.weather.monitoring.app"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"$weatherAPIKey\"")
            buildConfigField("String", "BASE_API_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_KEY", "\"$weatherAPIKey\"")
            buildConfigField("String", "BASE_API_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)

    // retrofit
    implementation(libs.okHttp)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)

    // gson
    implementation(libs.gson)

    // timber
    implementation(libs.timber)

    // joda
    implementation(libs.joda)

    // navigation component
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    // room db
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // location provider
    implementation(libs.location.provider)
}