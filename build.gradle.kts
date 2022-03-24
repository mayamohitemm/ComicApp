buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com")
    }
    dependencies {
        classpath(DependingOn.GradlePlugin.hilt)
    }
}
plugins {
    DependingOn.AppPlugins.plugins.forEach {
        id(it.first) version it.second apply it.third
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
