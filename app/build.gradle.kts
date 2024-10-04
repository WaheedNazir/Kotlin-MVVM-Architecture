import java.util.Properties
import java.io.FileInputStream
import java.io.FileNotFoundException

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.kotlin.mvvm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kotlin.mvvm.architecture"
        minSdk = 21
        targetSdk = 34
        compileSdk = 34
        versionCode = 5
        versionName = "1.0.5"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    // Load properties from the credentials.properties file
    val credentialsFile = rootProject.file("credentials.properties")
    val properties = Properties()

    if (credentialsFile.exists()) {
        properties.load(FileInputStream(credentialsFile))
    } else {
        throw FileNotFoundException("Missing credentials.properties file!")
    }


    buildTypes {
        getByName("debug") {
            buildConfigField("String", "NEWS_API_KEY", properties.getProperty("NEWS_API_KEY"))
            buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
            isMinifyEnabled = false // Enable ProGuard for release builds if needed
            isDebuggable = true
        }
        getByName("release") {
            buildConfigField("String", "NEWS_API_KEY", properties.getProperty("NEWS_API_KEY"))
            buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
            isMinifyEnabled = false // Enable ProGuard for release builds if needed
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    //KTX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.8.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Dagger, Hilt
    val hilt = "2.51.1"
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")
    // optional - Test helpers
    testImplementation("androidx.room:room-testing:2.6.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")
}
