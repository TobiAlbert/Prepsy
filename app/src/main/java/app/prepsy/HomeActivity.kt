package app.prepsy

import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val years = listOf(
            2010, 2011, 2012, 2013, 2014, 2015,
            2016, 2017, 2018, 2019, 2020
        )
        val yearsAdapter = ArrayAdapter(this, R.layout.list_item_dropdown, years)
        findViewById<AutoCompleteTextView>(R.id.selectYearAT).setAdapter(yearsAdapter)
        findViewById<AutoCompleteTextView>(R.id.selectYearAT).setText(years.first().toString(), false)


        val subjects = listOf(
            "Agriculture",
            "Arabic",
            "Art",
            "Biology",
            "Chemistry",
            "CRS",
            "Commerce",
            "Economics",
            "French",
            "Geography",
            "Government",
            "Hausa",
            "History",
            "Home Economics",
            "Igbo",
            "Islamic Studies",
            "Literature in English",
            "Mathematics",
            "Music",
            "Physics",
            "Principles of Accounts",
            "Use of English",
            "Yoruba"
        )

        val subjectsAdapter = ArrayAdapter(this, R.layout.list_item_dropdown, subjects)
        findViewById<AutoCompleteTextView>(R.id.selectSubjectAT).setAdapter(subjectsAdapter)
        findViewById<AutoCompleteTextView>(R.id.selectSubjectAT).setText(subjects.first(), false)
    }
}