plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hilt)
    id(BuildPlugins.ktor)
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
    //Ktor
    implementation(DependingOn.Ktor.ktor)
    implementation(DependingOn.Ktor.logging)
    implementation(DependingOn.Ktor.serialisation)
    implementation(DependingOn.Ktor.jsonSerialisation)
    //Hilt
    implementation(DependingOn.Hilt.hiltAndroid)
    implementation(DependingOn.Hilt.hiltNavigation)
    kapt(DependingOn.Hilt.hiltCompiler)
    //Test
    testImplementation(DependingOn.Test.jUnit)
}
