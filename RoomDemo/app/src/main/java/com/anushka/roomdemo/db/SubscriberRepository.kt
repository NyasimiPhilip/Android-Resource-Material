package com.anushka.roomdemo.db

// A repository class typically acts as an abstraction layer between different data sources (like a Room database) and the rest of the app.
class SubscriberRepository(private val dao: SubscriberDAO) {

    // LiveData is commonly used for observing changes in data. In this case, it represents a list of all subscribers.
    val subscribers = dao.getAllSubscribers()

    // Function to insert a new subscriber into the database. Marked with 'suspend' as it is a coroutine function.
    suspend fun insert(subscriber: Subscriber) {
        dao.insertSubscriber(subscriber)
    }

    // Function to update an existing subscriber in the database. Marked with 'suspend' for coroutine usage.
    suspend fun update(subscriber: Subscriber) {
        dao.updateSubscriber(subscriber)
    }

    // Function to delete a subscriber from the database. Marked with 'suspend' for coroutine usage.
    suspend fun delete(subscriber: Subscriber) {
        dao.deleteSubscriber(subscriber)
    }

    // Function to delete all subscribers from the database. Marked with 'suspend' for coroutine usage.
    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
