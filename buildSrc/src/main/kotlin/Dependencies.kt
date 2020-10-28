object Versions {
    const val kotlinVersion = "1.4.0"
    const val androidGradlePlugin = "4.1.0"
    const val detektVersion = "1.11.0-RC2"
    const val coroutinesVersion = "1.4.0"
    const val coreKtx = "1.3.1"
    const val appCompat = "1.2.0"
    const val materialComponents = "1.2.0"
    const val fragmentKtx = "1.2.5"
    const val navigationVersion = "2.3.0"
    const val constraintLayout = "2.0.0-rc1"
    const val recyclerView = "1.2.0-alpha05"
    const val lifecycleVersion = "2.2.0"
    const val daggerVersion = "2.28.3"
    const val moshiVersion = "1.9.3"
    const val picassoVersion = "2.71828"
    const val retrofitVersion = "2.9.0"

}

object ClassPath {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektVersion}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
}

object Dependencies {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val viewModel  = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.11.0"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"

    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val javaxInject = "javax.inject:javax.inject:1"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitLogger = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
}

object TestDependencies {
    const val junit = "junit:junit:4.13"
}

object AndroidTestDependencies {
    const val androidJunitExt = "androidx.test.ext:junit:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
}