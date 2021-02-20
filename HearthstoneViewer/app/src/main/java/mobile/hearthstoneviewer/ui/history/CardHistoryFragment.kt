package mobile.hearthstoneviewer.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import mobile.hearthstoneviewer.R
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_history_fragment.*
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel


class DrinkHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = DrinkHistoryFragment()
    }

    private lateinit var viewModel: CardsViewModel
    private lateinit var cardListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        cardListAdapter = CardListAdapter(viewModel.listOfCards) { it ->
            viewModel.getCardsById(it.id) { d ->
                CardsViewModel.selectedCard = d
                view?.findNavController()
                        ?.navigate(R.id.action_drinkHistoryFragment_to_cardsDetailsFragment)
            }

        }

        viewModel.getRecentCards()
        viewModel.listOfCards.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })
        return inflater.inflate(R.layout.card_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewRecentCards.apply {
            adapter = cardListAdapter
            layoutManager = viewManager
        }
    }


}