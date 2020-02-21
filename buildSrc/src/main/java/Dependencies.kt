@file:Suppress("MayBeConstant")

object ApplicationID {
    val id = "com.application.firebasemessager"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlin = "1.3.61"

    val gradle = "3.5.1"
    val minSDK = 23
    val targetSDK = 29
    val compileSDK = 29

    val navigation = "2.3.0-alpha01"
    val appCompat = "1.1.0"
    val material = "1.1.0"
    val core_ktx = "1.2.0"
    val constraint = "1.1.3"

    val junit = "4.12"
    val ext_junit = "1.1.1"
    val espresso = "3.2.0"

    val retrofit = "2.6.0"
    val okhttp = "4.1.0"

    val leakCanary = "2.0"

    val timber = "4.7.1"

    val ktlint = "0.29.0"

    val dagger = "2.21"
    val assistedInject = "0.3.3"

}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

    val junit = "junit:junit:${Versions.junit}"
    val extJunit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val material = "com.google.android.material:material:${Versions.material}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"


    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    val assistedInject =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assistedInject}"
    val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assistedInject}"

    val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.2.2"
    val firebaseAuth = "com.google.firebase:firebase-auth:19.2.0"
    val firebaseFirestore = "com.google.firebase:firebase-firestore:21.4.0"

    val gson = "com.squareup.retrofit2:converter-gson:2.6.0"

}