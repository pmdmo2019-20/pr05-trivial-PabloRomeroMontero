package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_game_won.*


class GameWon : Fragment(R.layout.fragment_game_won), IObackPress {

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
        requireActivity()

    }

    private fun setupViews() {
        setupAppBar()
        viewModel = requireActivity().run {
            ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
                .get(MainActivityViewModel::class.java)        }
        buttonWon.setOnClickListener { navigateToGame() }
    }


    private fun navigateToGame() {
        viewModel.resetGame()
        val gameFragment = GameFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameFragment, "TAG_GAME_FRAGMENT")
                .addToBackStack("TAG_GAME_FRAGMENT")
        }
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_won_imgYouWin)
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.comenzarJuego(numeroDePreguntas)
        val tittleFragment = TittleFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, tittleFragment, "TAG_TITTLE_FRAGMENT")
        }
        return true
    }
}
