package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager

import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.ui.IObackPress
import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over.Game_overFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won.GameWon
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivity
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModelFactory
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(R.layout.fragment_game),IObackPress {
//    private val viewModel: MainActivityViewModel by lazy {
//        ViewModelProvider(viewLifecycleOwner, MainActivityViewModelFactory(Database.newInstance()))
//            .get(MainActivityViewModel::class.java)
//    }

    private lateinit var viewModel : MainActivityViewModel

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }
    private val numeroDePreguntas: Int by lazy {
        settings.getInt(
            getString(R.string.question_key_preference),
            5
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = requireActivity().run {
            ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
                .get(MainActivityViewModel::class.java)        }
        setupViews()
    }

    private fun setupViews() {
        setupAppBar()
        textViewQuestion.text = viewModel.getQuestion().q
        a1.text = viewModel.getQuestion().a1
        a2.text = viewModel.getQuestion().a2
        a3.text = viewModel.getQuestion().a3
        a4.text = viewModel.getQuestion().a4

        buttonSubmit.setOnClickListener { comprobar()
        }
    }

    private fun comprobar() {
        var numero = 0
        when (radioGroup.checkedRadioButtonId) {
            R.id.a1 -> numero=1
            R.id.a2 -> numero=2
            R.id.a3 -> numero=3
            R.id.a4 -> numero=4
        }
        if(numero != 0 && viewModel.comprobar(numero) && viewModel.numeroPreguntasHechas+1==viewModel.listaPreguntas.size) {
            hasGanado()
        }else if(numero != 0 && viewModel.comprobar(numero)){
            nuevaPregunta()
        }else{
            viewModel.resetGame(numeroDePreguntas)
            navegarGameOver()
        }
    }

    private fun navegarGameOver() {
        val gameOverFragment = Game_overFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameOverFragment, "TAG_GAME_OVER_FRAGMENT")
                .addToBackStack("TAG_GAME_OVER_FRAGMENT")
        }
    }

    private fun hasGanado() {
        val gameWonFragment = GameWon()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameWonFragment, "TAG_GAMEWON_FRAGMENT")
                .addToBackStack("TAG_GAMEWON_FRAGMENT")
        }
    }


    private fun nuevaPregunta(){
        val gameFragment = GameFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameFragment, "TAG_GAME_FRAGMENT")
                .addToBackStack("TAG_GAME_FRAGMENT")
        }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.game_question_title,viewModel.numeroPreguntasHechas+1,numeroDePreguntas)
        }
    }


    override fun onBackPressed(): Boolean {
        val tittleFragment = TittleFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, tittleFragment, "TAG_TITTLE_FRAGMENT")
        }
        return true
    }

}
