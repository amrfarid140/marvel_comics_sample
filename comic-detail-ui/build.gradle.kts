plugins {
    `eventx-android-library`
}

android {
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
}
