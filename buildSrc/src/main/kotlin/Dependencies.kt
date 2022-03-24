import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.provideDelegate

object BuildPlugins {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val android by lazy { "android" }
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android" }
    val kotlinKapt by lazy { "kotlin-kapt" }
    val hilt by lazy { "dagger.hilt.android.plugin" }
    val androidLibrary = "com.android.library"
}

object DependingOn {

    object AppPlugins {
        val plugins: List<Triple<String, String, Boolean>> = listOf(
            Triple("com.android.application", "7.1.2", false),
            Triple("com.android.library", "7.1.2", false),
            Triple("org.jetbrains.kotlin.android", "1.5.21", false),
        )
    }

    object Accompanist {
        val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
        val pager by lazy { "com.google.accompanist:accompanist-pager:${Versions.pager}" }
        val insets by lazy { "com.google.accompanist:accompanist-insets:${Versions.insets}" }
        val systemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:0.15.0" }
    }

    object Retrofit {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
        val moshi by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshi}" }
        val interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}" }
    }

    object Hilt {
        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
        val hiltNavigation by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}" }
    }

    object Compose {
        val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
        val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
        val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
        val composePreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
        val composeUiTool by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    }

    object Lifecycle {
        val runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}" }
    }

    object AndroidX {
        val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    }

    object Test {
        val jUnit by lazy { "junit:junit:${Versions.junit}" }
        val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }
    }

    object AndroidTest {
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
        val compose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
        val androidJUnitRunner by lazy { "androidx.test.runner.AndroidJUnitRunner" }
    }

    object GradlePlugin {
        val kotlinGradlePlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
        val androidGradlePlugin by lazy { "com.android.tools.build:gradle:${Versions.androidGradlePlugin}" }
        val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    }
}
