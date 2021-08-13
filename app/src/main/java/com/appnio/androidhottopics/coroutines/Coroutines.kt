package com.appnio.androidhottopics.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.Continuation














// What exactly is a coroutine?
























// Put simply a coroutine is a concurrency design pattern where a function or block of code can can
// be suspended, ceding back to the caller and can later be resumed


































// While Jetbrains are certainly getting a lot of buzz around this concept, it's actually been
// around a long time.  The term was in 1958 by a guy name Melvin Conway.











































// There are a few languages that have supported coroutines for some time including python, C#, and
// C++ in the form of async/await












































// The problem with the aforementioned implementations however is that you have to write async/await
// code in a visually very different way than regular synchronous code













































// This brings us to the motivation behind Jetbrains implementation.







































// 1. Callback hell
// 2. The limitation and inefficiency of the Thread model
// 3. Making concurrency code more idiomatic









































// In kotlin, we specify this suspendable code with the suspend access modifier.
suspend fun getTemperature(): Temperature {
    delay(100) // <- suspension point simulated network call
    return Temperature()
}






























// Sounds like black magic and it most certainly looks like black magic in practice but actually
// under the hood it's not all that complex as we'll see

// let's take a peak behind the curtain



























/**
 * When the compiler sees the suspend modifier it replaces the suspend modifier with a function
 * parameter for us of Type [Continuation].
 */
//      fun getWeather(completion: Continuation<Any?>)
suspend fun getWeather(): Weather {
    val temp = getTemperature()
    val humidity = getHumidity()
    val windFactor = getWindFactor()
    return Weather(temp, humidity, windFactor)
}

































// Let's recap






// The suspend modifier lets the compiler know that the function can suspend and can only be called from other coroutines






// The compiler takes that suspend modifier and removes the return type as well as adds a parameter of type Continuation.
// Which acts as a callback, state machine, and stack frame
// The callstack is moved to the heap as a pointer to the continuation object































// Now that we know how coroutines suspends and the inner working of them, how do we control the
// flow of execution and on which thread we execute?









































/**
 * Every coroutine is always executed within some context. This context is a composition of several
 * Elements like a Dispatcher, Job, or a Name. This context is defined in the scope of the coroutine.
 *
 * When we want to start a new coroutine we use one of a few built in Builders, which are just extensions
 * of CoroutineScope.  This newly created coroutine will propagate the context to every element
 * within the scope.
  */



























val job = Job()
val mainScope = MainScope()
val coroutineScope = CoroutineScope(Dispatchers.Default + job + CoroutineName("SomeJob"))
fun loadData() {
    val weatherJob = mainScope.launch {
        val weather = getWeather()
    }
    coroutineScope.launch {
        val weather = getWeather()
    }
}