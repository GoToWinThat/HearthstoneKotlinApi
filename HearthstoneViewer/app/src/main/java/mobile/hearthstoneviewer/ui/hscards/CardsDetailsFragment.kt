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
          //  viewModel.addDrinkToHistory(CardsViewModel.selectedCard)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var classname = "heg";
        when(CardsViewModel.selectedCard.classId)
        {
            2 -> classname = "Druid"
            3 -> classname = "Hunter"
            4 -> classname = "Mage"
            5 -> classname = "Paladin"
            6 -> classname = "Priest"
            7 -> classname = "Rogue"
            8 -> classname = "Shaman"
            9 -> classname = "Warlock"
            10 -> classname = "Warrior"
            14 -> classname = "Demon Hunter"

        }
        textViewDrinkNameD.text = CardsViewModel.selectedCard.name
      textViewClassIdName.text = classname

        Picasso.get().load(CardsViewModel.selectedCard.image).resize(700, 700)
            .centerCrop()
            .into(imageView)


//        lifecycleScope.launch {
//            if (viewModel.checkIfDrinkIsFavourite(CardsViewModel.selectedDrink)) {
//                buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_24_kivi)
//                CardsViewModel.selectedCard.favorite = true
//                textViewAddToFavorites.text = getString(R.string.remove_from_favorite)
//            } else {
//                buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24_kici)
//                CardsViewModel.selectedCard.favorite = false
//                textViewAddToFavorites.text = getString(R.string.add_to_favorite)
//            }
//        }
//
//
//        buttonAddToFavorites.setOnClickListener {
//            lifecycleScope.launch {
//                if (viewModel.checkIfDrinkIsFavourite(DrinkViewModel.selectedDrink)) {
//                    buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_border_24_kici)
//                    DrinkViewModel.selectedDrink.favorite = false
//                    textViewAddToFavorites.text = getString(R.string.add_to_favorite)
//                    viewModel.deleteDrinkFromFavourites(DrinkViewModel.selectedDrink)
//
//                } else if (!viewModel.checkIfDrinkIsFavourite(DrinkViewModel.selectedDrink)) {
//                    buttonAddToFavorites.setImageResource(R.drawable.ic_baseline_favorite_24_kivi)
//                    DrinkViewModel.selectedDrink.favorite = true
//                    textViewAddToFavorites.text = getString(R.string.remove_from_favorite)
//                    viewModel.addDrinkToFavourite(DrinkViewModel.selectedDrink)
//                }
//            }
//        }

//        buttonBack.setOnClickListener {
//            it.findNavController().navigate(R.id.action_drinkDetailsFragment_to_searchFragment)
//        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsDetailsFragment()
    }
}