package com.yvkalume.buildsrc

object App {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val jvmTarget = "1.8"
}

object Plugin {
    const val gradle = "com.android.tools.build:gradle:4.1.1"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
}

object Version {
    const val kotlin = "1.4.20"
}

object Module {
    const val model = ":model"
    const val util = ":util"
    const val interactor = ":interactors"
}

object Deps {

    const val viewBinding = "com.github.yogacp:android-viewbinding:1.0.1"
    const val roundedImageview = "com.makeramen:roundedimageview:2.3.0"
    const val dotIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
    const val material = "com.google.android.material:material:1.2.0"
    const val pinview = "com.github.mukeshsolanki:android-otpview-pinview:2.1.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val formValidator = "com.github.ShabanKamell.FormValidator:core:2.1.0"
    const val glide = "com.github.bumptech.glide:glide:4.11.0"
    const val blurry = "jp.wasabeef:blurry:4.0.0"
    const val blurBehind = "no.danielzeller.blurbehindlib:blurbehindlib:1.0.0"
    const val imageSlider = "com.github.smarteist:autoimageslider:1.4.0"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    }

    object Groupie {
        private const val version = "2.8.0"
        const val core = "com.xwray:groupie:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }


    object Mavericks {
        const val mvrx = "com.airbnb.android:mvrx:2.0.0-beta1"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    }

    object Koin {
        private const val koin_version = "2.1.6"
        const val android = "org.koin:koin-android:$koin_version"
        const val androidxScope = "org.koin:koin-androidx-scope:$koin_version"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:$koin_version"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.3.9"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:26.1.0"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val storage = "com.google.firebase:firebase-storage"
    }

    object AndroidX {
        private const val nav_version = "2.3.1"

        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val core = "androidx.core:core-ktx:1.3.2"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.2"
        const val viewpager = "androidx.viewpager2:viewpager2:1.0.0"
        const val startup = "androidx.startup:startup-runtime:1.0.0-beta01"

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

