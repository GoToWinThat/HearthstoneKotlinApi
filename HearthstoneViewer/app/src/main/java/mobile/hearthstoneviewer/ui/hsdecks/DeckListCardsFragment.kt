package mobile.hearthstoneviewer.ui.hsdecks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_deck_cards.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.model.entities.Card
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class DeckListCardsFragment : Fragment()
    {

        private lateinit var deckListCardsAdapter: DeckListCardsAdapter
        private lateinit var viewManager: RecyclerView.LayoutManager
        private lateinit var decksViewModel: DecksViewModel

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        {


            decksViewModel = ViewModelProvider(this).get(DecksViewModel::class.java)

            var test = MutableLiveData<List<Card>>()
            test.value=DecksViewModel.selectedDeck.cards

            viewManager = LinearLayoutManager(requireContext())
            deckListCardsAdapter = DeckListCardsAdapter(test){
                CardsViewModel.selectedCard = it
                view?.findNavController()?.navigate(R.id.cardsDetailsFragment)
            }

            /*CardsViewModel.cardsList.observe(viewLifecycleOwner, {
                deckListCardsAdapter.notifyDataSetChanged()
            })*/



            return inflater.inflate(R.layout.fragment_deck_cards, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?)
        {
            super.onViewCreated(view, savedInstanceState)

            recyclerViewDeckCardsList.apply{
                adapter = deckListCardsAdapter
                layoutManager= viewManager
            }
        }

        companion object
    {

        @JvmStatic
        fun newInstance() = DeckListCardsFragment ()
    }
}