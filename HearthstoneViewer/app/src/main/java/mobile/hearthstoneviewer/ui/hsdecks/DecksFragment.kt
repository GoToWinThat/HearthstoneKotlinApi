package mobile.hearthstoneviewer.ui.hsdecks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.ui.favourite.FavouriteViewModel

class DecksFragment : Fragment() {

    private lateinit var decksViewModel: DecksViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        decksViewModel = ViewModelProvider(this).get(DecksViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_decks, container, false)

        val textView: TextView = view.findViewById(R.id.text_decks)

        decksViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })

        return view
    }
}