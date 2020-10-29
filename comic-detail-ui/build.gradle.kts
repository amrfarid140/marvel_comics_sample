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

    androidTestImplementation(AndroidTestDependencies.coreTesting)
    androidTestImplementation(AndroidTestDependencies.fragmentTesting)
    androidTestImplementation(AndroidTestDependencies.androidJunitExt)
    androidTestImplementation(AndroidTestDependencies.espressoCore)
    androidTestImplementation(AndroidTestDependencies.testCore)
    androidTestImplementation(AndroidTestDependencies.testRules)
    androidTestImplementation(AndroidTestDependencies.testRunner)
    androidTestImplementation(AndroidTestDependencies.mockkAndroid)
    androidTestImplementation(project(":lib-ui-test"))
}
