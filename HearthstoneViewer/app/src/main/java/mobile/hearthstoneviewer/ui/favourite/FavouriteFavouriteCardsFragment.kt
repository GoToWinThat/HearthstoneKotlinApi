package mobile.hearthstoneviewer.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_favourite_favourite_cards.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class FavouriteFavouriteCardsFragment :Fragment() {
    private lateinit var viewModel: CardsViewModel
    private lateinit var cardListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        cardListAdapter = CardListAdapter(CardsViewModel.cardsList) { it ->
            viewModel.getCardsById(it.id) { d ->
                CardsViewModel.selectedCard = d
                view?.findNavController()
                    ?.navigate(R.id.action_favouriteCardsFragment_to_cardsDetailsFragment)
            }

        }

        viewModel.getFavouriteCards()

        viewModel.listOfCards.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_favourite_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewFavFavCards.apply {
            adapter = cardListAdapter
            layoutManager = viewManager
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFavouriteCardsFragment()
    }
}