
import java.util.regex.Pattern.compile

plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.posting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.posting"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/androidx.cardview_cardview.version")
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.database)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-auth")

    val nav_version = "2.7.7"

    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation("com.google.android.material:material:1.9.0")// Ensure this is correct and up-to-date



    implementation("com.github.rey5137:material:1.3.1")
        implementation("androidx.cardview:cardview:1.0.0")

    implementation("com.firebaseui:firebase-ui-database:8.0.1")

    implementation("com.firebaseui:firebase-ui-database:8.0.2")

    // FirebaseUI for Cloud Firestore
    implementation("com.firebaseui:firebase-ui-firestore:8.0.2")

    // FirebaseUI for Firebase Auth
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")

    // FirebaseUI for Cloud Storage
    implementation("com.firebaseui:firebase-ui-storage:8.0.2")
    implementation("com.squareup.picasso:picasso:2.71828")

        implementation("androidx.recyclerview:recyclerview:1.3.2")
        // For control over item selection of both touch and mouse driven selection
        implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    implementation("com.android.support:cardview-v7:28.0.0")
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        // For control over item selection of both touch and mouse driven selection
        implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    implementation("io.paperdb:paperdb:2.7.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")







}