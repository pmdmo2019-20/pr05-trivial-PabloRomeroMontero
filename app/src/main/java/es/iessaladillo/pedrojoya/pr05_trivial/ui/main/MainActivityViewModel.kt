package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.base.Question

class MainActivityViewModel(
    database: Database
) : ViewModel() {


    var numeroPreguntasHechas: Int = 0
    var database_ = database
    lateinit var listaPreguntas: MutableList<Question>

    private val _response: MutableLiveData<Boolean> = MutableLiveData()
    val response: LiveData<Boolean>
        get() = _response
    fun reply(value: Boolean) {
        _response.value = value
    }



    fun getQuestions(numeroDePreguntas: Int): MutableList<Question>{
        return database_.getQuestions(numeroDePreguntas)
    }

    fun resetGame() {
        listaPreguntas.clear()
        numeroPreguntasHechas=0
    }

    fun comenzarJuego(numeroDePreguntas: Int){
        listaPreguntas = getQuestions(numeroDePreguntas)
    }

    fun getQuestion() : Question{
        return listaPreguntas[numeroPreguntasHechas]
    }

    fun comprobar(numeroContestado: Int): Boolean{
        if (numeroContestado == listaPreguntas[numeroPreguntasHechas].correctAnswer){
            numeroPreguntasHechas++
            return true
        }else{
            return false
        }
    }
}