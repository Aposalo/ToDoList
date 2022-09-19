package com.example.android.todolist.other

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.android.todolist.other.TaskAdapter.TaskViewHolder
import com.example.android.todolist.database.TaskEntry
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.android.todolist.R
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private val mContext: Context,
    private val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<TaskViewHolder>() {

    private var mTaskEntries: List<TaskEntry?>? = null
    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    companion object {
        private const val DATE_FORMAT = "dd/MM/yyy"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskEntry = mTaskEntries?.get(position)
        val description = taskEntry?.description
        val priority = taskEntry?.priority
        val updatedAt = taskEntry?.updatedAt?.let { dateFormat.format(it) }
        holder.taskDescriptionView.text = description
        holder.updatedAtView.text = updatedAt
        val priorityString = "" + priority
        holder.priorityView.text = priorityString
        val priorityCircle = holder.priorityView.background as GradientDrawable
        val priorityColor = getPriorityColor(priority)
        priorityCircle.setColor(priorityColor)
    }

    private fun getPriorityColor(priority: Int?): Int {
        var priorityColor = 0
        when (priority) {
            1 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialRed)
            2 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange)
            3 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow)
            else -> {}
        }
        return priorityColor
    }

    override fun getItemCount(): Int {
        return if (mTaskEntries == null) 0
               else mTaskEntries!!.size
    }

    var tasks: List<TaskEntry?>?
        get() = mTaskEntries
        set(taskEntries) {
            mTaskEntries = taskEntries
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onItemClickListener(itemId: Int)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var taskDescriptionView: TextView
        var updatedAtView: TextView
        var priorityView: TextView

        init {
            taskDescriptionView = itemView.findViewById(R.id.taskDescription)
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt)
            priorityView = itemView.findViewById(R.id.priorityTextView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            var elementId = mTaskEntries?.get(adapterPosition)?.id
            if (elementId == null) elementId = -1
            mItemClickListener.onItemClickListener(elementId)
        }
    }
}