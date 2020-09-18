## Kotlin MVVM Architecture

A sample app that display list of Google news. The purpose of this project to illustrate the usage of MVVM architecture design pattern that follow the best practices of Object Oriented Design Patterns using the following technology stack.

 1. Architecture Design Pattern
 2. MVVM
 2. Dagger2 (Dependency Injection)
 3. Live Data, MediatorLiveData
 4. Room Database
 5. Retrofit
 6. Unit Testing (Espresso), Mockito (Coming soon)
 7. Repository Pattern
 8. AndroidX
 9. Glide
 10. NetworkBoundResource, NetworkAndDBBoundResource
 11. Google News API
 12. JetPack Libraries

### Demo
<img height="400px" src="https://github.com/WaheedNazir/Kotlin-MVVM-Architecture/blob/master/screens/sample_demo.gif" />

## Architecture
<img alt="MVVM Architecture" height="700px" src="https://github.com/WaheedNazir/Kotlin-MVVM-Architecture/blob/master/screens/Architecture_design_new.jpg" />


## NetworkBoundResource
<img alt="MVVM Architecture" height="500px" src="https://github.com/WaheedNazir/Kotlin-MVVM-Architecture/blob/master/screens/network-bound-resource.png" />


# Mediator LiveData
<img alt="MVVM Architecture" height="350px" src="https://github.com/WaheedNazir/Kotlin-MVVM-Architecture/blob/master/screens/Mediator_LiveData_Combined_These_Data_Sounrces.png" />


### Dependencies used

      // Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    // Support Libraries
    implementation 'com.android.support:design:28.0.0'
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation "com.android.support.constraint:constraint-layout:$constraintLayoutVersion"

    // Retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    implementation "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    testImplementation "com.squareup.okhttp3:mockwebserver:$okHttpVersion"

    // Lifecycle (ViewModel + LiveData)
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    kapt "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    implementation "android.arch.core:core-testing:$lifecycleVersion"

    // Room
    implementation "android.arch.persistence.room:runtime:$roomVersion"
    kapt "android.arch.persistence.room:compiler:$roomVersion"
    androidTestImplementation "android.arch.persistence.room:testing:$roomVersion"

    // Glide
    implementation "com.github.bumptech.glide:glide:$glideVersion"

    // Dagger core
    implementation "com.google.dagger:dagger:$dagger_version"
    kapt "com.google.dagger:dagger-compiler:$dagger_version"

    //Dagger Android
    implementation "com.google.dagger:dagger-android:$dagger_version"
    implementation "com.google.dagger:dagger-android-support:$dagger_version"
    kapt "com.google.dagger:dagger-android-processor:$dagger_version"

    //RxJava
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation "io.reactivex.rxjava2:rxjava:2.2.7"
    implementation 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'

    // Testing
    testImplementation "junit:junit:$jUnitVersion"
    androidTestImplementation("com.android.support.test.espresso:espresso-core:$espressoVersion", {
        exclude group: 'com.android.support', module: 'support-annotations'
    })


### Other samples of MVVM using Kotlin

* [View-ViewModel-Interaction] - Sample shows View and ViewModel Interaction/Communication.
* [Communication-ActivityFragment-ViewModel] - Sample shows communication between Activity and Fragment using ViewModel
* [ViewModel-ViewModel-Communication] - Sample shows communication between ViewModels
* [LocationUpdates-LiveData-ViewModel] - Sample shows, to get location updates as LiveData in Kotlin.
* [Kotlin-MVVM-Architecture] - Sample of complete MVVM Architecture using Dagger2, Kotlin, MediatorLiveData, Retrofit etc.
* [Presentation Slides] - Presentation slides on this architecture sample.

   [Kotlin-MVVM-Architecture]: <https://github.com/WaheedNazir/Kotlin-MVVM-Architecture>
   [View-ViewModel-Interaction]: <https://github.com/WaheedNazir/View-ViewModel-Interaction>
   [ViewModel-ViewModel-Communication]: <https://github.com/WaheedNazir/ViewModel-ViewModel-Communication>
   [Communication-ActivityFragment-ViewModel]: <https://github.com/WaheedNazir/Communication-ActivityFragment-ViewModel>
   [LocationUpdates-LiveData-ViewModel]: <https://github.com/WaheedNazir/LocationUpdates-LiveData-ViewModel>
   [Presentation Slides]: <https://github.com/WaheedNazir/Kotlin-MVVM-Architecture/blob/master/Presentation_WaheedNazir_FINAL.pdf>

   



## Author
[Waheed Nazir](https://github.com/WaheedNazir "Waheed Nazir (WaveTechStudio)")


## Sources
 1. [Google's official doc](https://developer.android.com/jetpack/docs/guide)
 2. [Google's Codelab](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/#0)
 2. [Architecture Components Samples](https://github.com/android/architecture-components-samples/tree/88747993139224a4bb6dbe985adf652d557de621)
 3. [Jose Alcérreca's post at Medium](https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7)
