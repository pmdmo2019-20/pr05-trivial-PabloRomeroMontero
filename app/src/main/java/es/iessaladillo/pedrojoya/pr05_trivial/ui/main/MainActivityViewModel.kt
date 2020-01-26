package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.base.Question

class MainActivityViewModel(
    database: Database
) : ViewModel() {


    var numeroPreguntasHechas: Int = 0
    var database_ = database
    lateinit var listaPreguntas: MutableList<Question>



    fun getQuestions(numeroDePreguntas: Int): MutableList<Question>{
        return database_.getQuestions(numeroDePreguntas)
    }

    fun resetGame(numeroDePreguntas: Int) {
        listaPreguntas = getQuestions(numeroDePreguntas)
        numeroPreguntasHechas=0
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