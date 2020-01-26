package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.ui.main.MainActivityViewModelFactory

class DialogFragment : DialogFragment() {
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = requireActivity().run {
            ViewModelProvider(this, MainActivityViewModelFactory(Database.newInstance()))
                .get(MainActivityViewModel::class.java)
        }
        isCancelable = false
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.message_confirmation))
            .setPositiveButton(getString(R.string.dialog_positive)) { _, _ ->
                viewModel.reply(true)
            }.setNegativeButton(getString(R.string.dialog_negative)) { _, _ ->
                viewModel.reply(false)
            }.create()
}