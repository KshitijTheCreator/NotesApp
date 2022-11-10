package com.example.notes_app.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes_app.Model.Notes
import com.example.notes_app.R
import com.example.notes_app.ViewModel.NotesViewModel
import com.example.notes_app.databinding.FragmentHomeBinding
import com.example.notes_app.ui.adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes= arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
        //gets all the notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes= notesList as ArrayList<Notes>
            adapter=NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }



        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item =menu.findItem(R.id.app_bar_search)
        val searchView =item.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            //onQueryTextSubmit does things when the user has pressed submit after
            //writing the text
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            //onQueryTextChange does processing while the user is writing
            //changes before the submission of the text
            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(newText: String?){
        val newFilteredList= arrayListOf<Notes>()
        for(i in oldMyNotes){
            if(i.title.contains(newText!!) || i.subTitle.contains(newText!!))
            {
                //now we will give this(newFilteredList) to the adapter
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}