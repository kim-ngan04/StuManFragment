package com.example.studentmanfragment

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentman.R
import com.google.android.material.snackbar.Snackbar

class StudentListFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter: StudentAdapter
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.list_view_students)
        adapter = StudentAdapter(requireContext(), mutableListOf())
        listView.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) { students ->
            adapter = StudentAdapter(requireContext(), students)
            listView.adapter = adapter
        }

        registerForContextMenu(listView)
        setHasOptionsMenu(true)  // Enable options menu
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                findNavController().navigate(R.id.action_list_to_add)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val student = viewModel.students.value?.get(info.position) ?: return false

        return when (item.itemId) {
            R.id.action_edit -> {
                val action = StudentListFragmentDirections.actionListToEdit(
                    info.position,
                    student.studentName,
                    student.studentId
                )
                findNavController().navigate(action)
                true
            }
            R.id.action_remove -> {
                val removedStudent = viewModel.removeStudent(info.position)
                showUndoSnackbar(removedStudent, info.position)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun showUndoSnackbar(removedStudent: Student, position: Int) {
        Snackbar.make(listView, "Đã xoá sinh viên", Snackbar.LENGTH_LONG)
            .setAction("Hoàn tác") {
                viewModel.undoRemove(position, removedStudent)
            }
            .show()
    }
}