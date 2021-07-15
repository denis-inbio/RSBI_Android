package ro.rsbideveloper.rsbi

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import ro.rsbideveloper.rsbi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainActivity_bottom_nav.setupWithNavController(findNavController(R.id.MainActivity_fragment_host))

//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(
//            findNavController(R.id.MainActivity_fragment_host)
//        ) || super.onOptionsItemSelected(item)
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return item.onNavDestinationSelected(
                findNavController(R.id.MainActivity_fragment_host)
            ) || super.onOptionsItemSelected(item)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//

    // <*?> OptionsItem vs ContextItem -> what kind of items are the BottomNavBar's items ?
        // does the bottom nav bar need to be associated with the activity in Kotlin code, or ..?
        // or is there some default inflater being called that fails to make an association ?
        // why doesn't the NavController automatically match the item id's with the destination's id ?
        // or, how does onNavDestinationSelected() know which item is a selection and which isn't ?
        // apparently the bottom navigation bar is not properly set up ?

    // <TODO> remove the app bar, the floating action button (could be used
        //  later for something ? maybe for adding a post, creating a webinar entry,
        //  remove menu items)
    // <TODO> "are you a member ?" (members can add webinars)
    // <TODO> webinar registration survey -> send email ? (RSBI server does this, I don't have access)
    // <TODO> implement the bottom navigation bar such that the nav graph can be simpler [as it stands,
        // it needs to be a bidirectional K4 graph + that (Splashscreen -> Homepage)]
    // <TODO>
}