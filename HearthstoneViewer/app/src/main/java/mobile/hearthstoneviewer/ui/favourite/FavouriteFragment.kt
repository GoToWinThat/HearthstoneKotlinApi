package mobile.hearthstoneviewer.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import mobile.hearthstoneviewer.R
import androidx.lifecycle.ViewModelProvider

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val textView: TextView = view.findViewById(R.id.text_favourite)

        favouriteViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return view
    }
}