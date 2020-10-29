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
    implementation(project(":comics-list-domain"))
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.moshi)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.javaxInject)

    implementation(TestDependencies.junit)
    implementation(TestDependencies.kotlinTest)
    implementation(TestDependencies.mockk)
    implementation(TestDependencies.coroutinesTest)
}
