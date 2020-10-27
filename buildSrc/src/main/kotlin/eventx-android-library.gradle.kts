plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1"
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.0")
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerAndroidSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerAndroidProcessor)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.9")
}

afterEvaluate {
    tasks.getByName("preBuild").dependsOn(
        tasks.getByName("detekt")
    )
}