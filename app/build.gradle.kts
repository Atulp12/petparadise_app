plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.petparadise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.petparadise"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.code.gson:gson:2.10")
    implementation ("com.mcxiaoke.volley:library:1.0.19")
    implementation ("com.github.santalu:diagonal-imageview:1.1.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.ncorti:slidetoact:0.11.0")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
//    implementation ("com.effigy.state progressbar:state progressbar:1.0.0")
//    implementation ("com.kofigyan.stateprogressbar:stateprogressbar:1.0.0")
}