# Alpaca Muscle Maintenance

## Overview

An Android app using Kotlin which enables you to record the numbers of push-up (will also support squat someday).
And show posts on Qiita which are related to muscle maintenance.

For entire architecture, this app uses:
- [Data Binding Library](https://developer.android.com/topic/libraries/data-binding)
- [Dagger 2](https://github.com/google/dagger)

## Views

### Home

It shows wisdom words to encourage you to do exercise. It shows several words randomly and appears slowly using [AnimationUtils.loadAnimation](https://developer.android.com/reference/android/view/animation/AnimationUtils.html#loadAnimation(android.content.Context,%20int)).

<img height="320px" src="./images/home.png" />

### Exercise

We can record the number of push-ups. The idea is that... we push the gray big button with our chin 😂 It uses [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room) for saving data. And squat tab will come someday (how can I record it?)

<img height="320px" src="./images/exercise.png" />

### Record

It shows the number of push-ups in time series. Uses [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) to make the graph.

<img height="320px" src="./images/records.png" />

### Feed

It shows posts which are related to muscle maintenance. Uses [Retrofit](https://github.com/square/retrofit) to access Web API and [Glide](https://github.com/bumptech/glide) to load images as thumbnails.
When we tap a rows, it opens the page in WebView.

<img height="320px" src="./images/feed_with_detail.png" />

### Repository page

Shows this Github repository to see any updates.

<img height="320px" src="./images/bug_report.png" />

## Author

Masataka Hirano - [alpaca0984](https://github.com/alpaca0984)
