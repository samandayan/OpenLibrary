package com.albert.openlibrary

import android.app.Application
import com.squareup.otto.Bus

class OpenLibraryApplication : Application() {

    /**
     * This is the bus used to share the
     * retrieved data from the downloading
     * file with the fragment that displays
     * data to the user.
     * The bus sends and receives objects
     * that are shared by classes.
     */
    companion object {
        var bus = Bus()
        fun getDefaultBus() : Bus {
            return  bus
        }
    }
}