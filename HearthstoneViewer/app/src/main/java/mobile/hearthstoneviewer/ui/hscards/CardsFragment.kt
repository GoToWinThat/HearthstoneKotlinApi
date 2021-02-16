package mobile.hearthstoneviewer.ui.hscards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mobile.hearthstoneviewer.R

class CardsFragment : Fragment() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        cardsViewModel = ViewModelProvider(this).get(CardsViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_cards, container, false)

        val textView: TextView = view.findViewById(R.id.text_cards)

        cardsViewModel.text.observe(viewLifecycleOwner, Observer {textView.text = it})

        return view
    }
}