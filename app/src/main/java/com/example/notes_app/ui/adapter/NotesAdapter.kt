package com.example.notes_app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app.Model.Notes
import com.example.notes_app.R
import com.example.notes_app.databinding.ItemNotesBinding
import com.example.notes_app.ui.fragments.HomeFragmentDirections

class NotesAdapter(requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList= newFilteredList
        notifyDataSetChanged()
    }
    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val getPosition = notesList[position]
        holder.binding.notesTitle.text = getPosition.title
        holder.binding.notesSubtitle.text = getPosition.subTitle
        holder.binding.notesDate.text = getPosition.date

        when(getPosition.priority){
            "1" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(getPosition)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size
}