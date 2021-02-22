package mobile.hearthstoneviewer.ui.hsdecks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cards.*
import kotlinx.android.synthetic.main.fragment_decks.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.favourite.FavouriteViewModel
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsFragment
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class DecksFragment : Fragment() {

    private lateinit var deckListAdapter: DeckListAdapter

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var decksViewModel: DecksViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {


        decksViewModel = ViewModelProvider(this).get(DecksViewModel::class.java)
        decksViewModel.getCards()


        viewManager = LinearLayoutManager(requireContext())
        deckListAdapter = DeckListAdapter(decksViewModel.listOfDecks){
            DecksViewModel.selectedDeck = it
            view?.findNavController()?.navigate(R.id.action_navigation_decks_to_deckListCardsFragment)
        }

        decksViewModel.listOfDecks.observe(viewLifecycleOwner, {
            deckListAdapter.notifyDataSetChanged()
        })



        return inflater.inflate(R.layout.fragment_decks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewDecksList.apply{
            adapter = deckListAdapter
            layoutManager= viewManager
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = DecksFragment ()
    }
}