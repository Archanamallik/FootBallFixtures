plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.footballfixtures"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.footballfixtures"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    //HILT

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    //retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)


    //paging
    implementation(libs.androidx.paging.runtime)
    implementation (libs.androidx.paging.compose)

    implementation (libs.glide)
    // COIL
    implementation (libs.coil.compose)

    testImplementation (libs.mockito.core)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    implementation("net.bytebuddy:byte-buddy:1.14.2")
    testImplementation (libs.mockwebserver3)
   // testImplementation "android.arch.core:core-testing:1.0.0"
}