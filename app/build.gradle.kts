plugins {
//    id("com.android.application")
    id("com.google.gms.google-services")
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.firstapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firstapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
        // Import the BoM for the Firebase platform


        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

//    implementation("com.google.android.material:material:1.11.0-alpha01")

    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.google.firebase:firebase-database:19.6.0")

    implementation("com.google.firebase:firebase-bom:32.8.1")

    // Declare the dependencies for the desired Firebase products without specifying versions
    // For example, declare the dependencies for Firebase Authentication and Cloud Firestore

    implementation ("com.google.firebase:firebase-firestore")
    implementation ("androidx.cardview:cardview:1.0.0")

//    implementation ("com.firebaseui:firebase-ui-database:6.2.1")
    implementation ("com.google.android.material:material:1.1.0")

    implementation ("com.google.firebase:firebase-database:19.3.1")

    implementation("com.google.ai.client.generativeai:generativeai:0.3.0")

    // Required for one-shot operations (to use `ListenableFuture` from Guava Android)
    implementation("com.google.guava:guava:31.0.1-android")

    // Required for streaming operations (to use `Publisher` from Reactive Streams)
    implementation("org.reactivestreams:reactive-streams:1.0.4")


}