plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "me.amryousef.marvel"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".dev"
            minifyEnabled(false)
            isShrinkResources = false
            debuggable(true)
        }
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    androidExtensions {
        features = setOf("parcelize")
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.10")
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.materialComponents)
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUI)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodegen)
    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerAndroidSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerAndroidProcessor)

    testImplementation(TestDependencies.junit)
    androidTestImplementation(AndroidTestDependencies.androidJunitExt)
    androidTestImplementation(AndroidTestDependencies.espressoCore)
}

afterEvaluate {
    tasks.getByName("preBuild").dependsOn(
            tasks.getByName("detekt")
    )
}