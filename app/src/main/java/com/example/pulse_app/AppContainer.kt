package com.example.pulse_app

import com.example.pulse_app.repositories.PulseRepository

val appContainer by lazy {
    AppContainer()
}

class AppContainer {
    val pulseRepository by lazy {
        PulseRepository(
            pulseDao = MainApplication.appDatabase.pulseDao()
        )
    }
}