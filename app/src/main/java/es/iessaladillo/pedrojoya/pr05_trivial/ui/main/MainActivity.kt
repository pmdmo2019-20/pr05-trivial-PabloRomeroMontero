package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.base.Database
import es.iessaladillo.pedrojoya.pr05_trivial.ui.IObackPress
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(Database.newInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            showInitialFragment()
        }
    }

    private fun showInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.fcDetail, TittleFragment.newInstance())
        }
    }


    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.fcDetail)
        if((fragment as? IObackPress)?.onBackPressed()!!){
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
