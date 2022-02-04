# Alpaca Muscle Maintenance

[![Android CI](https://github.com/alpaca0984/AlpacaMuscleMaintenance/workflows/Android%20CI/badge.svg)](https://github.com/alpaca0984/AlpacaMuscleMaintenance/actions?query=workflow%3A%22Android+CI%22)

## Overview

An Android app using Kotlin which enables you to record the numbers of push-up (will also support squat someday).
This shows posts calling [Qiita API](https://qiita.com/api/v2/docs). It's a website where people post technical articles, but there is `筋トレ` (muscle maintenance) tag for some reasons.

Technology stack:
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Kotlin coroutines](https://developer.android.com/kotlin/coroutines)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Navigation component](https://developer.android.com/guide/navigation)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

## Architecture

This app follows Multi-Module Clean Architecture. It separates layers into presentation, data and domain.

<img height="680px" src="./docs/images/architecture.jpeg" />

These posts helped me to understand what the clean architecture is and why it is good:
- [Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)
- [Clean Architecture in Android — A Beginner Approach](https://medium.com/swlh/clean-architecture-in-android-a-beginner-approach-be0ce00d806b)
- [Why you need Use Cases/Interactors](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)

## Views

### Home

It shows wisdom words to encourage you to do exercise. It shows several words randomly and appears slowly using [AnimationUtils.loadAnimation](https://developer.android.com/reference/android/view/animation/AnimationUtils.html#loadAnimation(android.content.Context,%20int)).

<img height="320px" src="./docs/images/home.png" />

### Exercise

You can record the number of push-ups. The idea is that... you push the gray big button with your chin 😂 When you push `+` button at bottom right, it saves record data using [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room). Squat tab will come someday (how can I record it?)

<img height="320px" src="./docs/images/exercise.png" />

### Record

It shows the number of push-ups in time series. Uses [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) to make the graph.

<img height="320px" src="./docs/images/records.png" />

### Feed

It shows posts which are related to muscle maintenance. Uses [Retrofit](https://github.com/square/retrofit) to access Web API and [Glide](https://github.com/bumptech/glide) to load images as thumbnails.
When we tap a rows, it opens the page in WebView.

<img height="320px" src="./docs/images/feed_with_detail.png" />

### Repository page

Shows this Github repository to see any updates.

<img height="320px" src="./docs/images/bug_report.png" />

## Author

Masataka Hirano - [alpaca0984](https://github.com/alpaca0984)
