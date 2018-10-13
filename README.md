# Android - Compass to destination
This repository contains a sample compass app that uses MVVM as its presentation layer pattern. **The app aims to centralize data flow and at the same time be extremely flexible to creating variants for automated and manual testing**. Also, the project implements and follows the guidelines presented in Google Sample [MVVM+RXJAVA-android](https://github.com/googlesamples/android-architecture/tree/dev-todo-mvvm-rxjava/).

Project is mainly written in Kotlin with the following essential dependencies: Dagger2 with Dagger-android, RxJava2 with RxAndroid and RxKotlin, Arch LifeCycle, Fused Location, Retrofit and Espresso. Other noteworthy dependencies would be Mockito, Chrome CustomTabs and Guava.
## App Demo
The starting point of the project was a a standard compass that pinpointed the mangnetic poles. The core of the app is now the extra feature that allows the user to enter a pair of coordinates. This causes the compass to pintpoint to a specific direction that leads to the entered destination. The compass pinpoints to the direction of the destination without ever needing an active internet connection, relying totally on internal sensors and GPS.
Also, in order to maintain a correct orientation towards the destination, an internal feature has been added which assures that the user's current location is updated on specific displacements.

![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/1f.gif)
![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/2f.gif)
![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/3f.gif)
