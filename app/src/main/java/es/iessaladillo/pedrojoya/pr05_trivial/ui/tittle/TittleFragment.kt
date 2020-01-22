package es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import es.iessaladillo.pedrojoya.pr05_trivial.R


class TittleFragment : Fragment(R.layout.fragment_title) {

    //preguntar por que ocurre
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    // requireView solo se utilizara si el fragmento requiere algo de alguna vista del padre
    private fun setupViews(requireView: View) {

    }

    companion object {
        fun newInstance(): TittleFragment =
            TittleFragment()
    }
}
