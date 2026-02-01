plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Necesario para que Room procese las anotaciones @Entity y @Dao
    id("kotlin-kapt") 
}

android {
    namespace = "com.tu.paquete.controlfinanzas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tu.paquete.controlfinanzas"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    // Jetpack Compose (Interfaz de usuario)
    val compose_version = "1.6.0"
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Room (Base de Datos Local)
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // ViewModel y Navegación
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
}