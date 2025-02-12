plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.electiva_libre"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.electiva_libre"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true

            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "Electiva_Libre dev")
            buildConfigField("String", "HOST_BASE", "${rootProject.ext.properties["HOST_BASE"]}")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "app_name", "Electiva_Libre")
            buildConfigField("String", "HOST_BASE", "${rootProject.ext.properties["HOST_BASE"]}")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}
var ktor_version = "3.0.3"

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //keytor
    implementation( libs.ktor.client.core)
    implementation( libs.ktor.client.android)
    implementation( libs.ktor.client.content.negotiation)
    implementation( libs.ktor.serialization.kotlinx.json)
    implementation( libs.ktor.client.logging)
    //hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //nav_compose

    val nav_version = "2.8.6"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    //viemodel
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")

    // ViewModel y Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7") // ViewModel + Corrutinas
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7") // LiveData
    implementation(libs.androidx.lifecycle.runtime.ktx.v262) // LifecycleScope

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0") // Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0") // Android

    // Lottie
    implementation (libs.lottie.compose)
    //ROOM
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Icons
    implementation(libs.icons)


}