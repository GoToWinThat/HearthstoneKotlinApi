
package mobile.hearthstoneviewer.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import mobile.hearthstoneviewer.R
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_favourite.*
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class FavouriteFragment : Fragment() {
    private lateinit var cardListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {


        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        cardListAdapter = CardListAdapter(cardsViewModel.listOfCards) { it ->
            cardsViewModel.getCardsById(it.id) { d ->
                CardsViewModel.selectedCard = d
                view?.findNavController()
                        ?.navigate(R.id.action_favouriteCardsFragment_to_cardsDetailsFragment)
            }

        }

        cardsViewModel.getFavouriteCards()

        cardsViewModel.listOfCards.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewFavCards.apply {
            adapter = cardListAdapter
            layoutManager = viewManager
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }
}