## Purpose of the project
Purpose of this project is to showcase an example of Android application architecture with focus on Clean Architecture. It also provides a useful template that all future projects should implement in order to achieve better consistency among them. 

### Table of contents: 
1. [Why clean?](#why_clean)
2. [Implementation](#how)
3. [Layers](#layers)
4. [App](#app_module)
5. [Domain](#domain_module)
6. [Data](#data_module)
7. [Libraries](#libraries)

## <a name=why_clean></a> Why Clean architecture
There are few well-known arhitectural patterns like MVC, MVP or MVVM that provide elegant solutions to separating application's UI code from the rest of the project. While in some cases it can be enough, we wanted to create a consistent architecture throughout whole application and not only in the presentation layer.

## <a name=how></a>How is it implemented
![Clean architecture onion diagram](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)
Onion diagram above is Uncle Bob's well-known representation of Clean Architecture and it is the basis for this showcase project. 

### <a name=layers></a>Layers / modules
As you can see on the diagram above application's code is separated into 4 layers. We will represent these 4 layers as 3 modules in our project, merging Entities and Use Cases from the diagram into one Domain layer. Project structure is then divided into:  

 * **app** module  
 * **domain** module  
 * **data** module

To wire it all up and to manage dependencies inside our codebase we are using Koin dependency injection framework. 

#### <a name=app_module></a>App module 
We have left default name for this one. This module contains all of the code related to the UI/Presentation layer. This includes things like Activities, Fragments and ViewModels.  
We'll use MVVM arhitectural pattern to separate our UI from the rest of the app. Presentation layer is built using Android Architecture Components: ViewModels, Lifecycle and LiveData elements.  

#### <a name=domain_module></a>Domain module
Domain module contains application's business logic. This is the core part of every app and it should be clear what project is all about just by looking at these files/classes. This module should also be independent of any framework. That's why it is the innermost layer in Uncle Bob's onion diagram. Following the dependency inversion rule this module shouldn't depend on anything from **app** or **data** module. We will hold our Use Cases and business models in this module. We'll also store repository abstractions in this module. This allows us to communicate with the rest of the application following The Dependency Inversion Principle.

#### <a name=data_module></a>Data module
Data module holds all concrete implementations of our repositories and other data sources like databases or network, split into corresponding packages. Every data source holds its own model classes.  Every model class that is going to be saved into the database or is going to be used in the domain layer should implement DomainMapper and RoomMapper and their respective methods in order to prepare data for the next layer. This step will help us filter out data that is not valid or may not be used further in the app.  
To cache our data locally we are using Google's Room Persistance Library.  
We are also using Retrofit for network requests and Gson for parsing json responses.

## <a name=libraries></a>Third party libraries
- [Retrofit](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)
- [MockitoKotlin](https://github.com/nhaarman/mockito-kotlin)
- [Junit4](https://junit.org/junit4/)
- [OkHttp](https://github.com/square/okhttp)
- [Koin](https://insert-koin.io/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)

## License: MIT 
Created by [COBE](https://www.cobe.tech/) Copyright 2019 COBE

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 
