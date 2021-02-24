package mobile.hearthstoneviewer.ui.hscards

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
import mobile.hearthstoneviewer.R
import kotlinx.android.synthetic.main.fragment_cards.*
import mobile.hearthstoneviewer.ui.hsdecks.DecksViewModel

class CardsFragment : Fragment()
{

    private lateinit var cardListAdapter: CardListAdapter

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {


        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)


        viewManager = LinearLayoutManager(requireContext())
        cardListAdapter = CardListAdapter(CardsViewModel.cardsList){
            CardsViewModel.selectedCard = it
            view?.findNavController()?.navigate(R.id.cardsDetailsFragment) //DO POPRAWY
        }

        CardsViewModel.cardsList.observe(viewLifecycleOwner, {
            cardListAdapter.notifyDataSetChanged()
        })



        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCardsList.apply{
            adapter = cardListAdapter
            layoutManager= viewManager
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = CardsFragment ()
    }
}