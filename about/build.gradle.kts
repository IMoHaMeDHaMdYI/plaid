/*
 * Copyright 2019 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
}

apply(from = "$rootDir/dependencies.gradle.kts")

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("dagger.gradle.incremental" to "true")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding.isEnabled = true
}

dependencies {
    api(platform(project(":depconstraints")))

    implementation(project(":app"))
    implementation(project(":core"))
    implementation(project(":bypass"))

    implementation("androidx.viewpager2:viewpager2")
    implementation("com.android.support:customtabs")
    implementation("com.github.bumptech.glide:glide")

    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
}

kapt {
    useBuildCache = true
}
