import org.gradle.kotlin.dsl.provideDelegate

object BuildPlugins {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val android by lazy { "android" }
    val kotlinAndroid by lazy { "kotlin-android" }
    val kotlinKapt by lazy { "kotlin-kapt" }
    val hilt by lazy { "dagger.hilt.android.plugin" }
}

object DependingOn {

    object Accompanist {
        val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
        val pager by lazy { "com.google.accompanist:accompanist-pager:${Versions.pager}" }
        val insets by lazy { "com.google.accompanist:accompanist-insets:${Versions.insets}" }
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
        val composeAndroidTest by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
        val composeUiTool by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    }

    object Lifecycle {
        val runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKtx}" }
    }

    object Test {
        val jUnit by lazy { "junit:junit:${Versions.junit}" }
        val extJunit by lazy { "androidx.test.ext:junit:${Versions.extJunit}" }

    }

    object AndroidTest {
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    }

    object GradlePlugin {
        val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}" }
    }
}
