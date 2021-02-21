package mobile.hearthstoneviewer.ui.hsdecks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SectionIndexer
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.model.entities.Card
import mobile.hearthstoneviewer.model.entities.Deck
import mobile.hearthstoneviewer.ui.hscards.CardListAdapter

class DeckListAdapter (var decks: LiveData<List<Deck>>, var clickCallback: ((d: Deck) -> Unit)) : RecyclerView.Adapter<DeckListAdapter.Holder>(), SectionIndexer
{
    class Holder(view: View): RecyclerView.ViewHolder(view)

    private lateinit var mSectionPositions: ArrayList<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_decks,parent,false) as View
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int)
    {
        val textViewTitle = holder.itemView.findViewById<TextView>(R.id.textDeckName)
        val rowDeck = holder.itemView.findViewById<LinearLayout>(R.id.row_deck)

        if(decks.value?.get(position)?.heroClass?.name != "")
        {
            textViewTitle.text =  decks.value?.get(position)?.heroClass?.name
            rowDeck.setOnClickListener { clickCallback(decks.value?.get(position)!!)
            }
        }
        else
        {
            textViewTitle.text = "empty"
        }

    }

    override fun getItemCount(): Int
    {
        return decks.value?.size?:0
    }

    override fun getSections(): Array<String> {

        var sections: MutableList<String> = ArrayList()
        mSectionPositions = ArrayList()

        return sections.toTypedArray()
    }

    override fun getPositionForSection(sectionIndex: Int): Int
    {
        return mSectionPositions[sectionIndex];
    }

    override fun getSectionForPosition(position: Int): Int
    {
        return 0;
    }

}