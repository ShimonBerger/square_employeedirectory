## Build tools & versions used
Android Gradle Plugin v7.4.0
Kotlin v1.8.0
CompileSdk 33

## Steps to run the app
Clone or import into Android Studio and build normally.

## What areas of the app did you focus on?
Separation of powers between different layers of the app.
UI

## What was the reason for your focus? What problems were you trying to solve?
Not to have one layer too dependent on another layer. Abstract away implementations to improve complexity, testability, and allow for easier code changes in the future.
Wanted a pleasant UI that was easily understood.

## How long did you spend on this project?
5-6 hours broken up over a number of days.

## Did you make any trade-offs for this project? What would you have done differently with more time?
Slightly better data layer abstraction with more dependency injection. More unit tests. Better UI with more controls (filter, sort, etc.)

## What do you think is the weakest part of your project?
Unit testing.

## Did you copy any code or dependencies? Please make sure to attribute them here!
RecyclerViewWithEmpty was adapted from a gist as mentioned in the class.
MainCoroutineDispatcherRule is from Google documentation as mentioned in the class.

## Is there any other information you’d like us to know?
