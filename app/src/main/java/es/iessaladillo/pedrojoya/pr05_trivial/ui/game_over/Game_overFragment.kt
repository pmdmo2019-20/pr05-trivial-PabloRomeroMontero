package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.IObackPress
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_game_won.*

class Game_overFragment : Fragment(R.layout.fragment_game_over),IObackPress {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()

    }

    private fun setupViews() {
        setupAppBar()
        buttonTryAgain.setOnClickListener { navigateToGame() }
    }


    private fun navigateToGame() {
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
        val tittleFragment = TittleFragment()
        activity?.supportFragmentManager?.commit {
            replace(R.id.fcDetail, tittleFragment, "TAG_TITTLE_FRAGMENT")
        }
        return true
    }
}
