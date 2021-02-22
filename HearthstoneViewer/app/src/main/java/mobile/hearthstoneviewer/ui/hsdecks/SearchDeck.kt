package mobile.hearthstoneviewer.ui.hsdecks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_decks.*
import kotlinx.android.synthetic.main.fragment_decks.recyclerViewDecksList
import kotlinx.android.synthetic.main.fragment_search_card.*
import kotlinx.android.synthetic.main.fragment_search_deck.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel


class SearchDeck : Fragment()
{
    private lateinit var deckListAdapter: DeckListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var decksViewModel: DecksViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        decksViewModel = ViewModelProvider(this).get(DecksViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())

        //poprawa
        deckListAdapter = DeckListAdapter(DecksViewModel.searchedDeck){
            DecksViewModel.selectedDeck = it
            view?.findNavController()?.navigate(R.id.action_searchDeck_to_deckListCardsFragment)
        }

        DecksViewModel.searchedDeck.observe(viewLifecycleOwner, {
            deckListAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_search_deck, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewDeckListSearch.apply{
            adapter = deckListAdapter
            layoutManager= viewManager
        }


        searchViewDeck.setOnQueryTextListener(

            object : SearchView.OnQueryTextListener
            {
                override fun onQueryTextSubmit(query: String?): Boolean
                {
                    searchViewDeck.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean
                {
                    if (newText != null)
                    {
                        if(newText!="")
                        {
                            decksViewModel.getDeck(newText)
                            deckListAdapter.notifyDataSetChanged()
                        }
                        else
                        {
                        }
                        return true
                    }
                    else
                    {
                        return false
                    }

                }
            })
    }
    companion object {

        @JvmStatic
        fun newInstance() = SearchDeck ()
    }
}