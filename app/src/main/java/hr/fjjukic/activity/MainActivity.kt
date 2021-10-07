package hr.fjjukic.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import hr.fjjukic.R
import hr.fjjukic.common.contracts.GetNavController

class MainActivity : AppCompatActivity(), GetNavController {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        supportActionBar?.let { actionBar ->
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.setCustomView(R.layout.custom_action_bar)
        }
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun getNavController(): NavController {
        return findViewById<FragmentContainerView>(R.id.nav_host_fragment)
            .findNavController()
    }
}