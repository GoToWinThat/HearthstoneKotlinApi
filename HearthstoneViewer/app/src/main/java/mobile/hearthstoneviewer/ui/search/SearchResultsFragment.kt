package mobile.hearthstoneviewer.ui.search

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
import kotlinx.android.synthetic.main.fragment_search_results.*
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

class SearchResultsFragment : Fragment() {

    private lateinit var viewModel: CardsViewModel
    private lateinit var drinkListAdapter: CardListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        drinkListAdapter = CardListAdapter(viewModel.listOfCards) { it ->

            viewModel.getCardsById(it.id) { d ->
                CardsViewModel.selectedCard = d

                view?.findNavController()
                        ?.navigate(R.id.action_searchResultsFragment_to_cardsDetailsFragment)
            }

        }
       // viewModel.getDrinksByIngrediends(IngredientViewModel.selectedIngredients)


        viewModel.listOfCards.observe(viewLifecycleOwner, {
            drinkListAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewResults.apply {
            adapter = drinkListAdapter
            layoutManager = viewManager
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchResultsFragment()
    }
}