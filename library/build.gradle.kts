import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "com.github.wirye"
version = "1.0.0"

kotlin {
    jvm()
    android {
        namespace = "com.github.wirye.anilibriakt"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava()
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain.dependencies {}

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

//    signAllPublications()

    coordinates(group.toString(), "anilibria-kt", version.toString())

    pom {
        name = "anilibria-kt"
        description = "Kotlin Multiplatform wrapper for AniLibria API"
        inceptionYear = "2026"
        url = "https://github.com/Wirye/anilibria-kt"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "Wirye"
                name = "Wirye"
                url = "https://github.com/Wirye"
            }
        }
        scm {
            url = "https://github.com/Wirye/anilibria-kt"
            connection = "scm:git:git://github.com/Wirye/anilibria-kt.git"
            developerConnection = "scm:git:ssh://github.com/Wirye/anilibria-kt.git  "
        }
    }
}
