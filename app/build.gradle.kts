plugins {
    id(BuildPlugins.application)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hilt)
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = DependingOn.AndroidTest.androidJUnitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
    packagingOptions {
        resources {
//            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
}

dependencies {
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
    //Test
    testImplementation(DependingOn.Test.jUnit)
    androidTestImplementation(DependingOn.Test.extJunit)
    androidTestImplementation(DependingOn.AndroidTest.espresso)
    androidTestImplementation(DependingOn.AndroidTest.compose)
}
