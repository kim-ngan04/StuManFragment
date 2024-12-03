package com.example.studentmanfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _students = MutableLiveData<MutableList<Student>>()
    val students: LiveData<MutableList<Student>> = _students

    init {
        _students.value = mutableListOf(
            Student("Nguyễn Văn An", "SV001"),
            Student("Trần Thị Bảo", "SV002"),
            Student("Lê Hoàng Cường", "SV003"),
            Student("Phạm Thị Dung", "SV004"),
            Student("Đỗ Minh Đức", "SV005"),
            Student("Vũ Thị Hoa", "SV006"),
            Student("Hoàng Văn Hải", "SV007"),
            Student("Bùi Thị Hạnh", "SV008"),
            Student("Đinh Văn Hùng", "SV009"),
            Student("Nguyễn Thị Linh", "SV010"),
            Student("Phạm Văn Long", "SV011"),
            Student("Trần Thị Mai", "SV012"),
            Student("Lê Thị Ngọc", "SV013"),
            Student("Vũ Văn Nam", "SV014"),
            Student("Hoàng Thị Phương", "SV015"),
            Student("Đỗ Văn Quân", "SV016"),
            Student("Nguyễn Thị Thu", "SV017"),
            Student("Trần Văn Tài", "SV018"),
            Student("Phạm Thị Tuyết", "SV019"),
            Student("Lê Văn Vũ", "SV020")
        )
    }

    fun addStudent(student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList.add(student)
        _students.value = currentList
    }

    fun updateStudent(position: Int, student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList[position] = student
        _students.value = currentList
    }

    fun removeStudent(position: Int): Student {
        val currentList = _students.value ?: mutableListOf()
        val removedStudent = currentList.removeAt(position)
        _students.value = currentList
        return removedStudent
    }

    fun undoRemove(position: Int, student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList.add(position, student)
        _students.value = currentList
    }
}