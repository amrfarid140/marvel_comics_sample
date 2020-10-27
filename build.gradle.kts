buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter {
            content {
                // just allow to include kotlinx projects
                // detekt needs 'kotlinx-html' for the html report
                includeGroup("org.jetbrains.kotlinx")
            }
        }
    }
    dependencies {
        classpath(ClassPath.androidGradlePlugin)
        classpath(ClassPath.kotlinGradlePlugin)
        classpath(ClassPath.detekt)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.11.0-RC2")
}
val ktlint by configurations.creating
dependencies {
    ktlint("com.pinterest:ktlint:0.37.2")
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    val outputDir = "${project.buildDir}/reports/ktlint/"
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

    val ktlintFormat by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }

    val ktlintCheck by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
    }
    ktlintCheck.dependsOn(ktlintFormat)
    tasks.getByName("detekt").dependsOn(ktlintCheck)

    detekt {
        parallel = true                                      // Builds the AST in parallel. Rules are always executed in parallel. Can lead to speedups in larger projects. `false` by default.
        buildUponDefaultConfig = true                        // Interpret config files as updates to the default config. `false` by default.
        reports {
            xml {
                enabled = false
            }
            html {
                enabled = true                                // Enable/Disable HTML report (default: true)
                destination = file("build/reports/detekt.html") // Path where HTML report will be stored (default: `build/reports/detekt/detekt.html`)
            }
            txt {
                enabled = false
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}