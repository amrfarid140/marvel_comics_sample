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
    implementation(Dependencies.materialComponents)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.daggerAndroidSupport)
    implementation(Dependencies.liveData)
    implementation(Dependencies.viewModel)
    implementation(Dependencies.picasso)
    implementation(Dependencies.navigationFragment)
    implementation(AndroidTestDependencies.coreTesting)
    implementation(AndroidTestDependencies.fragmentTesting)
    implementation(AndroidTestDependencies.androidJunitExt)
    implementation(AndroidTestDependencies.espressoCore)
    implementation(AndroidTestDependencies.testCore)
    implementation(TestDependencies.mockk)
    implementation(AndroidTestDependencies.testRunner)
    implementation(AndroidTestDependencies.testRules)
}
