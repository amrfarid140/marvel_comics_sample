plugins {
    `eventx-android-library`
    id("androidx.navigation.safeargs.kotlin")
}

android {

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.navigationUI)
    implementation(Dependencies.navigationFragment)
    implementation(project(":comics-list-ui"))
    implementation(project(":comic-detail-ui"))
}