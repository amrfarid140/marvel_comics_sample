plugins {
    `eventx-android-library`
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.javaxInject)
}
