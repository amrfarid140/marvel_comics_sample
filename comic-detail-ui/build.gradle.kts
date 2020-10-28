plugins {
    `eventx-android-library`
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":lib-ui"))
    implementation(project(":lib-presentation"))
    implementation(project(":lib-navigation"))
    implementation(project(":comic-detail-presentation"))
    implementation(Dependencies.materialComponents)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.daggerAndroidSupport)
    implementation(Dependencies.liveData)
    implementation(Dependencies.viewModel)
    implementation(Dependencies.picasso)
    implementation(Dependencies.navigationFragment)
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.fragment:fragment-testing:1.2.5")
    androidTestImplementation(AndroidTestDependencies.androidJunitExt)
    androidTestImplementation(AndroidTestDependencies.espressoCore)
    androidTestImplementation("androidx.test:core:1.0.0")
    androidTestImplementation("io.mockk:mockk-android:1.10.2")


    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test:rules:1.1.0")
}
