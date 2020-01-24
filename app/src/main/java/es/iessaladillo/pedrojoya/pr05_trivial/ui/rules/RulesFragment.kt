package es.iessaladillo.pedrojoya.pr05_trivial.ui.rules

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import es.iessaladillo.pedrojoya.pr05_trivial.R


class RulesFragment : Fragment(R.layout.fragment_rules) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupAppBar()
    }


    companion object {
        fun newInstance(): RulesFragment =
            RulesFragment()
    }

    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.rules_title)
        }
    }

}
