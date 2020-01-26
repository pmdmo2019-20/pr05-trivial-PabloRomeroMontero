package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over

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
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModelFactory
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_won.*

class Game_overFragment : Fragment(R.layout.fragment_game_over),IObackPress {


    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }
    private val numeroDePreguntas: Int by lazy {
        settings.getInt(
            getString(R.string.question_key_preference),
            5
        )
    }
    private lateinit var viewModel : MainActivityViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()

    }

    private fun setupViews() {
        setupAppBar()
        viewModel = requireActivity().run {
            ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
                .get(MainActivityViewModel::class.java)        }

        buttonTryAgain.setOnClickListener { navigateToGame() }
    }


    private fun navigateToGame() {
        viewModel.comenzarJuego(numeroDePreguntas)
        val gameFragment = GameFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameFragment, "TAG_GAME_FRAGMENT")
                .addToBackStack("TAG_GAME_FRAGMENT")
        }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_over_title)
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.resetGame()
        val tittleFragment = TittleFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, tittleFragment, "TAG_TITTLE_FRAGMENT")
        }
        return false
    }
}
