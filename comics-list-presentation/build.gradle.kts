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
    implementation(project(":lib-presentation"))
    implementation(project(":lib-domain"))
    implementation(project(":comics-list-domain"))
    implementation(Dependencies.liveData)
    implementation(Dependencies.viewModel)
    implementation(Dependencies.javaxInject)
}