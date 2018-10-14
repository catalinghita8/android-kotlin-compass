# Android - Compass to destination
This repository contains a sample compass app that uses MVVM as its presentation layer pattern. **The app aims to centralize data flow and at the same time be extremely flexible to creating variants for automated and manual testing**. Also, the project implements and follows the guidelines presented in Google Sample [MVVM+RXJAVA-android](https://github.com/googlesamples/android-architecture/tree/dev-todo-mvvm-rxjava/).

Project is mainly written in Kotlin with the following essential dependencies: Dagger2 with Dagger-android, RxJava2 with RxAndroid and RxKotlin, Arch LifeCycle, Fused Location and Espresso. Other noteworthy dependencies would be Mockito, Chrome CustomTabs and Guava.
## App Demo
The [starting point](https://github.com/iutinvg/compass) of the project was a a standard compass that pinpointed the mangnetic poles. The core of the app is now the extra feature that allows the user to enter a pair of coordinates. This causes the compass to pintpoint to a specific direction that leads to the entered destination. The compass pinpoints to the direction of the destination without ever needing an active internet connection, relying totally on internal sensors and GPS.

Also, in order to maintain a correct orientation towards the destination, an internal feature has been added which assures that the user's current location is updated on specific displacements.

![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/1f.gif)
![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/2f.gif)
![](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/3f.gif)

## Presentation Layer
MVVM pattern is integrated to facilitate testing and to allow separating the user interface logic from business logic.

As Views were passive in MVP, here the View layer is much more flexibile as an indefinite number of Views can bind to a `ViewModel` (which actually happens in this project). Also, MVVM enforces a clear separation between Views and their master - ViewModel, as the latter holds no reference to Views. The model layer is completely isolated through the repository pattern.

![Presentation](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/dagger_graph.png)

## Model Layer
The model layer is structured on repository pattern so that the ViewModel has no clue on the origins of the data. 

The repository handles data interactions and transactions from two main data sources - internal sensors and location updates:
- `CompassOrientationSource` which handles events from [internal sensors](https://developer.android.com/guide/topics/sensors/) and propagates relevant data downstream in the reactive flow 
- `CompassLocationSource` which handles location updates using [Fused API](https://developers.google.com/location-context/fused-location-provider/)  and propagates relevant data downstream in the reactive flow 

### Reactive approach
The project tends to have a fully reactive approach, as all events (from sensors or location updates) are handled and propagated downstream all the way to the final consumer, the `Views`.

In order to achieve this, two external dependencies are used:
- [RxLocation](https://github.com/patloew/RxLocation) that transforms location updates to a reactive stream that can be observed/consumed.
- [ReactiveSensors](https://github.com/pwittchen/ReactiveSensors) that transforms sensor updates to a reactive stream that can be observed/consumed. (This lib has been locally enhanced to support multiple sensor events observation)

We are also able to notice RxJava/RxKotlin benefits when data is being retrieved from the repository through different sources and then  channeled through the `ViewModel` and finally consumed in `Views`:
- Data Flow is centralized.
- Threading is much easier, with no need for the dreaded `AsyncTasks`.
- Error handling is straightforward and comfortable.

## Dependency Injection
Dagger2 is used to externalize the creation of dependencies from the classes that use them. Android specific helpers are provided by `Dagger-Android` and the most significant advantage is that they generate a subcomponent for each `Activity` through a new code generator.
Such subcomponent is:
```kotlin
@ActivityScoped
@ContributesAndroidInjector(modules = [CompassModule::class])
internal abstract fun compassActivity(): CompassActivity
```
The below diagram illustrates the most significant relations between components and modules. An important note is the fact that the ViewModel is now `@AppScoped` whereas in MVP the Presenter is `@ActivityScoped` - this is mainly due to the fact that in MVVM the ViewModel is a Android Architecture Component so therefore has a greater scope  than Views. You can also get a quick glance on how annotations help us define custom Scopes in order to properly handle classes instantiation.

![Dependecy](https://github.com/catalinghita8/android-kotlin-compass/blob/master/readme_pics/presentation_layer.png)
_Note: The above diagram might help you understand how Dagger-android works. Also, only essential components/modules/objects are included here, this is suggested by the "…"_

## Testing (In progress..)
The apps' components are extremely easy to test due to DI achieved through Dagger and the project's structure, but as well for the reason that the data flow is centralized with RxJava/RxKotlin which results in highly testable pieces of code. 

Unit tests are conducted with the help of Mockito and Instrumentation tests with the help of Espresso. 
## Strong points
- Decoupling level is high.
- Data Flow is centralized through RxJava/RxKotlin.
- Possess high flexibility to create variants for automated and manual testing.
- Possess lightweight structure due to MVVM presentation pattern.
- Is scalable and easy to expand.
## Weak points
- Possess high code base - simpler approaches might lower code size
- Possess medium complexity - other approaches might lower complexity

# Final notes:
- The app is not a polished ready-to-publish product, it acts as a boilerplate project or as a starting point for android enthusiasts out there.
- Using this project as your starting point and expanding it is also encouraged, as at this point it is very easy to add new modules.
