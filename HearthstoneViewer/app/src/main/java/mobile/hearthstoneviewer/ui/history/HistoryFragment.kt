package mobile.hearthstoneviewer.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_history_fragment.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class HistoryFragment : Fragment()
{
    private lateinit var cardListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())

        cardListAdapter = CardListAdapter(cardsViewModel.listOfCards) {
            CardsViewModel.selectedCard = it
            view?.findNavController()?.navigate(R.id.action_historyFragment_to_cardsDetailsFragment)
        }
        cardsViewModel.getRecentCards()
        cardsViewModel.listOfCards.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.card_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewRecentCards.apply {
            adapter = cardListAdapter
            layoutManager = viewManager
        }
    }

    companion object
    {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}