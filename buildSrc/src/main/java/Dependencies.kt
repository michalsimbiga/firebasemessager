@file:Suppress("MayBeConstant")

object ApplicationID {
    val id = "com.application.firebasemessager"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.31"

    val gradle = "3.5.1"
    val minSDK = 23
    val targetSDK = 29
    val compileSDK = 29

    val googleServices = "4.3.3"
    val googleMaps = "17.0.0"
    val googleLocations = "17.0.0"
    val googlePlaces = "2.1.0"

    val firebaseCore = "17.2.1"
    val firebaseAnalytics = "17.2.1"
    val firebaseMessaging = "20.1.0"
    val firebaseAuth = "19.2.0"
    val firebaseFirestore = "21.4.0"

    val appCompat = "1.0.2"
    val core_ktx = "1.0.1"
    val constraint = "2.0.0-beta3"
    val material = "1.1.0-alpha09"
    val coroutines = "1.3.2"
    val recyclerview = "1.0.0"
    val cardview = "1.0.0"
    val swipeRefresh = "1.0.0"
    val viewPager2 = "1.0.0-beta05"
    val expendableLayout = "2.9.2"

    val dagger = "2.21"
    val assistedInject = "0.3.3"

    val retrofit = "2.6.0"
    val okhttp = "3.12.0"
    val loggineInterceptor = "3.12.0"
    val gson = "2.6.0"

    val navigation = "2.2.0-alpha01"
    val lifecycle = "2.2.0-alpha01"
    val savedStateViewModel = "1.0.0-rc01"

    val permissionDispatcher = "4.6.0"
    val permissionAnnotation = "4.5.0"

    val testRunner = "1.1.1"
    val junit = "4.12"
    val ext_junit = "1.1.1"
    val espresso = "3.1.1"
    val mockito = "2.13.0"
    val coroutinesTest = "1.3.1"
    val coreTesting = "2.0.1"

    val glide = "4.10.0"

    val leakCanary = "2.0"

    val timber = "4.5.1"

    val ktlint = "0.29.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    val googleMaps = "com.google.android.gms:play-services-maps:${Versions.googleMaps}"
    val googleLocation = "com.google.android.gms:play-services-location:${Versions.googleLocations}"
    val googlePlaces = "com.google.android.libraries.places:places:${Versions.googlePlaces}"

    val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
    val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val firebaseFirestore = "com.google.firebase:firebase-firestore:${Versions.firebaseFirestore}"


    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    val assistedInject =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assistedInject}"
    val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assistedInject}"

    val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val lifecycleKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val savedStateViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.savedStateViewModel}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggineInterceptor}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
}

object SupportLibraries {
    val material = "com.google.android.material:material:${Versions.material}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
    val expendableLayout = "net.cachapa.expandablelayout:expandablelayout:${Versions.expendableLayout}"
}

object TestLibraries {
    val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val junit = "junit:junit:${Versions.junit}"
    val extJunit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesTest}"
    val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
}
