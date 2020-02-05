# 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧 
# Heroes
An Android application enabling one to get to know their superheroes better.  

# Introduction
Over the years we have encountered many superheroes and villans from comic books and films. Most of them have become our favourites but  we
have little information about them and how the came to be what they are. [Heroes] is an Android app consuming [Superheros REST API]
(https://superheroapi.com/) to fetch all SuperHeroes and Villians data from all universes.The app is beeing built using MVVM architectural 
pattern and [Android Architecture Components](https://developer.android.com/topic/libraries/architecture).

# Development Environment
* The app is written in Kotlin and uses the Gradle build system.
* Min API level == 21  
* Android Studio >= 3.5.3

# Architecture 

# Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
   * [Live Data](https://developer.android.com/topic/libraries/architecture/livedata) LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components updating app component observers that are in an active lifecycle state.
   * [Navigation](https://developer.android.com/guide/navigation/) Android Jetpack's Navigation component helps you implement effective navigation.
   * [Room](https://developer.android.com/topic/libraries/architecture/room) The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Retrofit](https://square.github.io/retrofit/) Type-safe HTTP client for Android and Java and Kotlin by Square, Inc. 
* [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects:
* [Kotshi](https://github.com/ansman/kotshi) An annotations processor that generates Moshi adapters from immutable Kotlin data classes.
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android
