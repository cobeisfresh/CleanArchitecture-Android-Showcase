## Purpose of the project
Purpose of this project is to showcase an example of Android application architecture with focus on Clean Architecture. It also provides a useful template that all future projects should implement in order to achieve better consistency among them. 

## Why Clean architecture
There are few well-known arhitectural patterns like MVC, MVP or MVVM that provide elegant solutions to separating application's UI code from the rest of the project. While in some cases it can be enough, we wanted to create a consistent architecture throughout whole application and not only in the presentation layer.

## How is it implemented
![Clean architecture onion diagram](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)
Onion diagram above is Uncle Bob's well-known representation of Clean Architecture and it is the basis for this showcase project. 

### Layers / modules
As you can see on the diagram above application's code is separated into 4 layers. We will represent these 4 layers as 3 modules in our project, merging Entities and Use Cases from the diagram into one Domain layer. Project structure is then divided into:  

 * **app** module  
 * **domain** module  
 * **data** module

#### App module
We have left default name for this one. This module contains all of the code related to the UI/Presentation layer. This includes things like Activities, Fragments and ViewModels.  
We'll use MVVM arhitectural pattern to separate our UI from the rest of the app. Presentation layer is built using Android Architecture Components: ViewModels, Lifecycle and LiveData elements.  
These components are also used throughout the rest of the application, thus helping us in making whole application somewhat reactive.

#### Domain module
Domain module contains application's business logic. This is the core part of every app and it should be clear what project is all about just by looking at these files/classes. This module should also be independent of any framework. That's why it is the innermost layer in Uncle Bob's onion diagram. Following the dependency rule this module shouldn't depend on anything from **app** or **data** module. We will hold our Use Cases and business models in this module. We'll also store repository abstractions in this module. This allows us to communicate with the rest of the application following The Dependency Inversion Principle.

#### Data module
Data module holds all concrete implementations of our repositories and other data sources like databases or network, split into corresponding packages. Every data source holds its own model classes. Before sending data further into the domain layer we are going to map it into business models. This step will help us filter out data that is not valid or may not be used further in the app.
 
