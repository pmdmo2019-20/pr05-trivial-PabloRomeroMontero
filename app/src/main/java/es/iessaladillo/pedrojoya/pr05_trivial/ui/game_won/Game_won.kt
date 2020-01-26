package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.IObackPress
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment
import kotlinx.android.synthetic.main.fragment_game_won.*


class GameWon : Fragment(R.layout.fragment_game_won), IObackPress {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        requireActivity()

    }

    private fun setupViews() {
        setupAppBar()
        buttonWon.setOnClickListener { navigateToGame() }
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
            setTitle(R.string.game_won_imgYouWin)
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
