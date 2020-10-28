plugins {
    `eventx-android-library`
}

android {

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":lib-domain"))
    implementation(Dependencies.viewModel)
}
