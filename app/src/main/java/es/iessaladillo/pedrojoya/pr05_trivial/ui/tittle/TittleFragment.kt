package es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager

import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModelFactory
import es.iessaladillo.pedrojoya.pr05_trivial.ui.rules.RulesFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_title.*


class TittleFragment : Fragment(R.layout.fragment_title) {

//    private val viewModel: MainActivityViewModel by lazy {
//        ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
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

    //preguntar por que ocurre
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = requireActivity().run {
            ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
            .get(MainActivityViewModel::class.java)        }
        setupViews()
    }


    private fun setupViews() {
        buttonPlay.setOnClickListener { play() }
    }

    private fun play() {
        viewModel.resetGame(numeroDePreguntas)
        navigateToGame()
    }

    private fun navigateToGame() {
        val gameFragment = GameFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, gameFragment, "TAG_GAME_FRAGMENT")
                .addToBackStack("TAG_GAME_FRAGMENT")
        }
    }

    companion object {
        fun newInstance(): TittleFragment =
            TittleFragment()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tittlemenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mnuSettings -> {
                navigateToSettings()
                true
            }
            R.id.mnuAbout -> {
                navigateToAbout()
                true
            }
            R.id.mnuRules -> {
                navigateToRules()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    private fun navigateToRules() {
        val rulesFragment = RulesFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, rulesFragment, "TAG_RULES_FRAGMENT")
                .addToBackStack("TAG_RULES_FRAGMENT")
        }
    }

    private fun navigateToAbout() {
        val aboutFragment = AboutFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, aboutFragment, "TAG_ABOUT_FRAGMENT")
                .addToBackStack("TAG_ABOUT_FRAGMENT")
        }
    }

    fun navigateToSettings() {
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, SettingsFragment())
            addToBackStack(null)
        }
    }

    override fun onResume() {
        super.onResume()
        setupAppBar()
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.app_name)
        }
    }

}
