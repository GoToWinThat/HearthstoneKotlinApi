package mobile.hearthstoneviewer.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cards.*
import kotlinx.android.synthetic.main.fragment_search_result.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class SearchResult : Fragment()
{
    private lateinit var cardListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())

        cardListAdapter = CardListAdapter(CardsViewModel.searchCardsList){
            CardsViewModel.selectedCard = it
            view?.findNavController()?.navigate(R.id.cardsDetailsFragment)
        }
        cardsViewModel.getCardsByParams()
        CardsViewModel.searchCardsList.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCardListSearchResult.apply{
            adapter = cardListAdapter
            layoutManager= viewManager
        }
    }
    companion object
    {
        @JvmStatic
        fun newInstance() = SearchResult ()
    }
}