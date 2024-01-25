package com.example.dependencyInjection

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class
])
interface smartphoneFunction {
   fun inject(mainActivity: MainActivity)
}
/**
In Dagger, an interface annotated with @Component is known as a Dagger component interface. Dagger uses this interface to generate a Dagger component implementation at compile time. Dagger components play a crucial role in the dependency injection process. Let's break down why you need this interface when working with Dagger:

Dependency Injection Configuration:

The @Component annotation tells Dagger to generate a component for the specified interface.
It serves as a configuration interface where you define the relationships between the client (code that needs dependencies) and the provider (code that provides dependencies).
Dependency Provider Methods:

Inside the interface, you declare abstract methods that represent the dependencies you want Dagger to provide.
In your example, fun getSmartPhone(): SmartPhone is a method that Dagger will implement to provide instances of the SmartPhone class.
Code Generation:

Dagger generates code during the compilation process based on the information provided in the component interface.
This generated code includes the actual implementation of the methods that provide dependencies, as well as the necessary wiring to satisfy the dependencies.
Dependency Resolution:

When a Dagger component is created, it resolves dependencies and ensures that the necessary objects are created and provided to the dependent classes.
In your case, the getSmartPhone() method indicates that the component can provide an instance of the SmartPhone class.
Here's a brief example to illustrate:

// Component interface
@Component
interface SmartphoneFunction {
    fun getSmartPhone(): SmartPhone
}

// Usage in client code
class MyActivity {
    // Dagger creates an implementation of SmartphoneFunction and provides the SmartPhone instance.
    val smartphone: SmartPhone = DaggerSmartphoneFunction.create().getSmartPhone()

    // Now you can use 'smartphone' in your activity.
}
In summary, the Dagger component interface is a crucial part of Dagger's annotation processing mechanism, allowing you to declare dependencies and letting Dagger generate the necessary code for dependency injection.
 */






