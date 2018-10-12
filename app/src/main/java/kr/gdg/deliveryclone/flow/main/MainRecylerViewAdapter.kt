package kr.gdg.deliveryclone.flow.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_recyclerview_item.view.*
import kr.gdg.deliveryclone.R
import kr.gdg.deliveryclone.model.Category
import kr.gdg.deliveryclone.utils.listen

class MainRecylerViewAdapter(
        private val items: Array<Category>,
        private val context: Context,
        private val clickListener : (item: Category) -> Unit
) : RecyclerView.Adapter<MainRecylerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecylerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_recyclerview_item, parent, false)
        return ViewHolder(view).listen{ pos, type ->
            clickListener(items[pos])
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainRecylerViewAdapter.ViewHolder, position: Int) {
        holder.source.setImageResource(items[position].resId)
        holder.source.setBackgroundResource(items[position].background)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val source = view.btnIcon
    }

}