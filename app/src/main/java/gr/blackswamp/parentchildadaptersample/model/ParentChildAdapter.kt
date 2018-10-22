package gr.blackswamp.parentchildadaptersample.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import gr.blackswamp.parentchildadaptersample.R


class ParentChildAdapter(internal var context: Context, parents: List<Parent>) : RecyclerView.Adapter<ParentChildAdapter.BaseViewHolder>() {
    val items = mutableListOf<Any>()

    init {
        parents //parents should be passed as a constructor argument
                .forEach {
                    items.add(it)
                    items.addAll(it.children)
                }
    }

    override fun getItemCount(): Int = items.size


    fun getItem(position: Int): Any = items[position]

    override fun getItemViewType(position: Int): Int = if (getItem(position) is Parent) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var view: View?
        if (viewType == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_parent, parent, false)
            return ParentViewHolder(view!!)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_child, parent, false)
            return ChildViewHolder(view!!)
        }
    }

    private fun itemClicked(_position: Int) {
        Toast.makeText(context, "item at position $_position clicked, the item is a ${if (getItemViewType(_position) == 0) "Parent" else "Child"}", Toast.LENGTH_LONG).show()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bindData(position, getItem(position))

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindData(position: Int, item: Any)

    }

    inner class ParentViewHolder(view: View) : BaseViewHolder(view) {
        private val parentId: TextView = view.findViewById(R.id.parent_id) as TextView
        private val parentDept: TextView = view.findViewById(R.id.parent_department) as TextView
        private var _position = 0

        init {
            view.setOnClickListener { this@ParentChildAdapter.itemClicked(_position) }
        }

        override fun bindData(position: Int, item: Any) {
            val parent = item as? Parent ?: return
            _position = position
            parentId.text = parent.idD.toString()
            parentDept.text = parent.dept
        }
    }


    inner class ChildViewHolder(view: View) : BaseViewHolder(view) {
        private val childId: TextView = view.findViewById(R.id.child_id) as TextView
        private val childItem: TextView = view.findViewById(R.id.child_item) as TextView
        private var _position = 0

        init {
            view.setOnClickListener { this@ParentChildAdapter.itemClicked(_position) }
        }

        override fun bindData(position: Int, item: Any) {
            val child = item as? Child ?: return
            _position = position
            childItem.text = child.item
            childId.text = child.idI.toString()
        }
    }

}