package es.iessaladillo.pedrojoya.pr05_trivial.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.tittle.TittleFragment

class MainActivity : AppCompatActivity() {

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
}
