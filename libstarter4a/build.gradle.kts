plugins {
    id("com.android.library") version "8.7.2"
    id("maven-publish")
}

android {
    namespace = "com.bitwormhole.starter4a"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    api("com.bitwormhole.starter4j:starter4j-starter:0.0.2-r6-snapshot")
    api("com.bitwormhole.starter4j:starter4j-vlog:0.0.2-r6-snapshot")
    api("com.bitwormhole.starter4j:starter4j-vlog-android:0.0.2-r6-snapshot")

    implementation("com.google.code.gson:gson:2.8.2")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}