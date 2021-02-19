package mobile.hearthstoneviewer.ui.hscards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SectionIndexer
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import mobile.hearthstoneviewer.R
import mobile.hearthstoneviewer.model.entities.Card


class  CardListAdapter(var cards: LiveData<List<Card>>, var clickCallback: ((d: Card) -> Unit)) :RecyclerView.Adapter<CardListAdapter.Holder>(), SectionIndexer
{
    class Holder(view: View): RecyclerView.ViewHolder(view)

    private lateinit var mSectionPositions: ArrayList<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_cards,parent,false) as View
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int)
    {
        val textViewTitle = holder.itemView.findViewById<TextView>(R.id.textCardName)
        val rowCard = holder.itemView.findViewById<LinearLayout>(R.id.row_card)

        if(cards.value?.get(position)?.name != "")
        {
            textViewTitle.text =  cards.value?.get(position)?.name
            rowCard.setOnClickListener { clickCallback(cards.value?.get(position)!!)
            }
        }
        else{
            textViewTitle.text = "empty"
        }

    }

    override fun getItemCount(): Int
    {
        return cards.value?.size?:0
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