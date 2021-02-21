package mobile.hearthstoneviewer.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search_card.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel


class SearchByNameFragment : Fragment() {


    private lateinit var drinkListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: CardsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        drinkListAdapter = CardListAdapter(CardsViewModel.cardsList){
            CardsViewModel.selectedCard = it
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_cardsDetailsFragment)
        }

        CardsViewModel.cardsList.observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE
            recyclerViewCardListSearch.visibility = View.VISIBLE
            drinkListAdapter.notifyDataSetChanged()
        })

        viewModel.listOfCards.observe(viewLifecycleOwner, {
            drinkListAdapter.notifyDataSetChanged()
        })


        return inflater.inflate(R.layout.fragment_search_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCardListSearch.apply{
            adapter = drinkListAdapter
            layoutManager=viewManager
        }

//        recyclerViewDrinkListSearch.setIndexBarColor(R.color.kivi)
//        recyclerViewDrinkListSearch.setIndexBarCornerRadius(10)
//        recyclerViewDrinkListSearch.setIndexBarTransparentValue(0.4F)
//        recyclerViewDrinkListSearch.setIndexBarVisibility(false)
//
//        val typeface = ResourcesCompat.getFont(requireContext(), R.font.nunito_light);
//        recyclerViewDrinkListSearch.setTypeface(typeface)
//
//        recyclerViewDrinkListSearch.addOnScrollListener(object: RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
//                    recyclerViewDrinkListSearch.setIndexBarVisibility(true)
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//            } TODO: Make it work properly
//        })


        searchViewDrink.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewDrink.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                {
                    if(newText!="")
                    {
                        viewModel.getCardsByName(newText)
                        drinkListAdapter.cards = viewModel.listOfCards
                        drinkListAdapter.notifyDataSetChanged()
                    }
                    else{
                        drinkListAdapter.cards = CardsViewModel.cardsList
                        drinkListAdapter.notifyDataSetChanged()
//                        viewModel.listOfDrinks.value!!.clear()
                    }
                    return true
                } else return false
            }
        })

    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchByNameFragment()
    }


}