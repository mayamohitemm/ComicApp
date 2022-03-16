plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":commonui"))
    implementation(project(":data"))
    implementation(project(":domain"))
    //Compose
    implementation(DependingOn.AndroidX.coreKtx)
    implementation(DependingOn.Compose.composeUi)
    implementation(DependingOn.Compose.composeMaterial)
    implementation(DependingOn.Compose.composePreview)
    implementation(DependingOn.Lifecycle.runtime)
    implementation(DependingOn.Compose.activityCompose)
    debugImplementation(DependingOn.Compose.composeUiTool)
    //Hilt
    implementation(DependingOn.Hilt.hiltAndroid)
    implementation(DependingOn.Hilt.hiltNavigation)
    kapt(DependingOn.Hilt.hiltCompiler)
    //Retrofit
    implementation(DependingOn.Retrofit.retrofit)
    implementation(DependingOn.Retrofit.moshi)
    implementation(DependingOn.Retrofit.interceptor)
    //Coil
    implementation(DependingOn.Accompanist.coil)
    //Pager
    implementation(DependingOn.Accompanist.pager)
    //Insets
    implementation(DependingOn.Accompanist.insets)
}