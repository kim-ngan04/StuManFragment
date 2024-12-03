package com.example.studentmanfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.studentman.R

class StudentAdapter(
    context: Context,
    private val students: List<Student>
) : ArrayAdapter<Student>(context, R.layout.layout_student_item, students) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.layout_student_item, parent, false)

        val student = students[position]
        view.findViewById<TextView>(R.id.text_student_name).text = student.studentName
        view.findViewById<TextView>(R.id.text_student_id).text = student.studentId

        view.findViewById<View>(R.id.image_edit).visibility = View.GONE
        view.findViewById<View>(R.id.image_remove).visibility = View.GONE

        return view
    }
}