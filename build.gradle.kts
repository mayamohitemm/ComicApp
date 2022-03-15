buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(DependingOn.GradlePlugin.hilt)
    }


}
plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}