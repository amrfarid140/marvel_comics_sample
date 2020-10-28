plugins {
    `eventx-kotlin-library`
}

dependencies {
    implementation(Dependencies.coroutinesCore)
    implementation(project(mapOf("path" to ":lib-domain")))
}