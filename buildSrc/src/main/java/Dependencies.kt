package com.yvkalume.buildsrc

object App {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
}

object Plugin {
    const val gradle = "com.android.tools.build:gradle:4.0.2"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
}

object Version {
    const val kotlin = "1.4.10"
}

object Module {

}

object Deps {

    const val viewBinding = "com.github.yogacp:android-viewbinding:1.0.1"
    const val roundedImageview = "com.makeramen:roundedimageview:2.3.0"
    const val dotIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
    const val material = "com.google.android.material:material:1.2.0"
    const val pinview = "com.github.mukeshsolanki:android-otpview-pinview:2.1.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val formValidator = "com.github.ShabanKamell.FormValidator:core:2.1.0"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    }

    object Groupie {
        private const val version = "2.8.0"
        const val core = "com.xwray:groupie:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }

    object AndroidX {
        private const val nav_version = "2.3.1"

        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val core = "androidx.core:core-ktx:1.3.2"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.2"
        const val viewpager = "androidx.viewpager2:viewpager2:1.0.0"

        object Navigation {
            private const val version = "2.3.0"

            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
        }

        object Test {
            const val junit = "junit:junit:4.12"
            const val ext = "androidx.test.ext:junit:1.1.2"
            object Espresso {
                private const val version = "3.3.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
            }
        }
    }
}

