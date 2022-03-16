plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hilt)
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = DependingOn.AndroidTest.androidJUnitRunner
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    //Retrofit
    implementation(DependingOn.Retrofit.retrofit)
    implementation(DependingOn.Retrofit.moshi)
    implementation(DependingOn.Retrofit.interceptor)
    //Hilt
    implementation(DependingOn.Hilt.hiltAndroid)
    implementation(DependingOn.Hilt.hiltNavigation)
    kapt(DependingOn.Hilt.hiltCompiler)
}
