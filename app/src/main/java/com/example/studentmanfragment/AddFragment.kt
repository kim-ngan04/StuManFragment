package com.example.studentmanfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studentman.R

class AddStudentFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.edit_student_name).text.toString()
            val id = view.findViewById<EditText>(R.id.edit_student_id).text.toString()

            if (name.isNotBlank() && id.isNotBlank()) {
                viewModel.addStudent(Student(name, id))
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}