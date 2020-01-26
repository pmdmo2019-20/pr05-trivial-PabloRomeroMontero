package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(val database: Database) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(database) as T
        }
        throw IllegalArgumentException("Must provide ScheduleActivityViewModel class")
    }

}