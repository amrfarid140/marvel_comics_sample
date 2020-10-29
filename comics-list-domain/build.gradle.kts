plugins {
    `eventx-kotlin-library`
}

dependencies {
    implementation(Dependencies.coroutinesCore)
    implementation(project(mapOf("path" to ":lib-domain")))

    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.kotlinTest)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.coroutinesTest)
}