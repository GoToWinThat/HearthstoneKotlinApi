package mobile.hearthstoneviewer

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.repository.CardRepository
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel
import mobile.hearthstoneviewer.ui.hsdecks.DecksViewModel
import retrofit2.awaitResponse


class MainActivity : AppCompatActivity()
{

    private lateinit var cardsViewModel: CardsViewModel
    private lateinit var decksViewModel: DecksViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        cardsViewModel.getCards()
        decksViewModel= ViewModelProvider(this).get(DecksViewModel::class.java)
        decksViewModel.getDecks()
    }
}