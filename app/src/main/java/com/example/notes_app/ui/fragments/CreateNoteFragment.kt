package com.example.notes_app.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes_app.Model.Notes
import com.example.notes_app.R
import com.example.notes_app.ViewModel.NotesViewModel
import com.example.notes_app.databinding.FragmentCreateNoteBinding
import java.util.*

class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        //by-default priority must be green
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        //making notes priority clickable
        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener{
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener{
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }
        return binding.root
    }
    private fun createNotes(it: View?){
        val title= binding.editTitle.text.toString()
        val subtitle= binding.editSubtitle.text.toString()
        val notes= binding.editNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data= Notes(
            null,
            title= title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Notes Created Successfully!", Toast.LENGTH_SHORT).show()


    }

}