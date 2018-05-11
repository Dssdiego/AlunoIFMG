package com.obsidianstudios.alunoifmg

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.obsidianstudios.alunoifmg.models.Assignment
import com.obsidianstudios.alunoifmg.models.Note
import kotlinx.android.synthetic.main.assignment_item.view.*
import kotlinx.android.synthetic.main.class_item.view.*

class AssignmentListAdapter(private val assignments: List<Assignment>,
                            private val context: Context) : RecyclerView.Adapter<AssignmentListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.assignment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignment = assignments[position]
        holder?.let {
            it.bindView(assignment)
        }
    }

    override fun getItemCount(): Int {
        return assignments.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(assignment: Assignment) {
            val title = itemView.assignment_item_title
            val date = itemView.assignment_item_date
            val className = itemView.assignment_item_class
            val daysRemaining = itemView.assignment_item_days

            when {
                assignment.daysRemaining < 7 -> itemView.assignment_item_flag.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_flag_red))
                assignment.daysRemaining in 8..14 -> itemView.assignment_item_flag.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_flag_yellow))
                else -> itemView.assignment_item_flag.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_flag_green))
            }

            date.text = assignment.dueDate
            title.text = assignment.title
            daysRemaining.text = assignment.daysRemaining.toString() + " d"
            className.text = assignment.name
            className.setTextColor(assignment.color)
        }

    }

}