package mobile.hearthstoneviewer.ui.hscards

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card_details.*
import kotlinx.coroutines.launch
import mobile.hearthstoneviewer.R

class CardsDetailsFragment : Fragment() {

    private lateinit var viewModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        lifecycleScope.launch {
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var classname = "Druid";
        when(CardsViewModel.selectedCard.classId)
        {
            2 -> classname = "Druid"
            3 -> classname = "Łowca"
            4 -> classname = "Mag"
            5 -> classname = "Paladyn"
            6 -> classname = "Kapłan"
            7 -> classname = "Łotr"
            8 -> classname = "Szaman"
            9 -> classname = "Czarnoksiężnik"
            10 -> classname = "Wojownik"
            14 -> classname = "Łowca Demonów"

        }
        var cardRarity = "Rzadka"
        when (CardsViewModel.selectedCard.rarityId)
        {
            1 -> cardRarity = "Pospolita"
            2 -> cardRarity = "Darmowa"
            3 -> cardRarity = "Rzadka"
            4 -> cardRarity = "Epicka"
            5 -> cardRarity = "Legendarna"
        }
        var cardType = "Broń"
        when(CardsViewModel.selectedCard.cardTypeId)
        {
            3 -> cardType = "Postać"
            4 -> cardType = "Stronnik"
            5 -> cardType = "Czar"
        }

        textViewCategory.text = CardsViewModel.selectedCard.name
        textViewClassIdName.text = "Klasa postaci: " + classname +
                "\nTyp karty: "  +cardType +
                "\nRzadkość karty: "  +cardRarity +
                "\nŻycie: " + CardsViewModel.selectedCard.health +
                "\nAtak: " + CardsViewModel.selectedCard.attack +
                "\nKoszt many: " + CardsViewModel.selectedCard.manaCost


        Picasso.get().load(CardsViewModel.selectedCard.image).resize(600, 700)
            .into(imageView)


        lifecycleScope.launch {
            if (viewModel.checkIfDrinkIsFavourite(CardsViewModel.selectedCard)) {
                buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_24_kivi)
                CardsViewModel.selectedCard.favorite = true
                textViewAddToFavorites.text = "Usuń z ulubionych"
            } else {
                buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24_kici)
                CardsViewModel.selectedCard.favorite = false
                textViewAddToFavorites.text = "Dodaj do ulubionych"
            }
        }


        buttonAddToFavorites.setOnClickListener {
            lifecycleScope.launch {
                if (viewModel.checkIfDrinkIsFavourite(CardsViewModel.selectedCard)) {
                    buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24_kici)
                    CardsViewModel.selectedCard.favorite = false
                    textViewAddToFavorites.text = "Dodaj do ulubionych"
                    viewModel.deleteCardFromFavourites(CardsViewModel.selectedCard)

                } else if (!viewModel.checkIfDrinkIsFavourite(CardsViewModel.selectedCard))
                {
                    buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_24_kivi)
                    CardsViewModel.selectedCard.favorite = true
                    textViewAddToFavorites.text = "Usuń z ulubionych"
                    viewModel.addCardToFavourite(CardsViewModel.selectedCard)
                }
            }
        }

        buttonBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardsDetailsFragment_to_navigation_cards)
        }
    }

    companion object
    {
        @JvmStatic
        fun newInstance() = CardsDetailsFragment()
    }
}