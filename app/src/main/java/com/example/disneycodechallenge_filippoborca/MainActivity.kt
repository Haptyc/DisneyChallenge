package com.example.disneycodechallenge_filippoborca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var list = ArrayList<Model>()
    var listTwo = ArrayList<Model>()

    val arrayTwo = arrayOf(
        "Mcauley Wilde",
                "Lorenzo Povey",
                "Sid Allison",
                "Aubree Philip",
                "Rubie Bond",
                "Summer Lara",
    )
    val array = arrayOf("Amelia Pratt",
        "Alaina Rivers",
        "Sage Bautista",
        "Savanah Richards",
        "Bailey Hanson",
        "Hillary Raymond",
        "Urijah Pratt",
        "Landin Wilkerson",
        "Harper Dickson",
        "Yoselin Lutz",
        "Fernanda Mills",
        "Maxwell Hall",
        "Trevin Newman",
        "Chris Walter",
        "Naima Poole",
        "Martha Ashley",
        "Essence Benitez",
        "Itzel Lee",
        "Leah Fitzpatrick",
        "Rose Fritz",
        "Marlee Mcknight",
        "Esperanza Rocha",
        "Tabitha Robles",
        "Melanie Anderson",
        "Abbie Holland",
        "Will Trujillo",
        "Shaun Higgins",
        "Moises Holloway",
        "Jane Faulkner",
        "Janiah Beasley",
        "Brenna Downs",
        "Lydia Andersen",
        "Madeline Pugh",
        "Kiara Hudson",
        "Arthur Cooley",
        "Aubrey Hinton",
        "Laila Davila",
        "Miracle Peters",
        "Cassidy Pineda",
        "Cailyn George",
        "Kiera Baldwin",
        "Sylvia Graham",
        "Garrett Owens",
        "Claudia Williamson",
        "Howard Gallegos",
        "Zaiden Bass",
        "Corinne Booker",
        "Wayne Gould",
        "Brady Christensen"
    )

    lateinit var adapter: CustomAdapter
    lateinit var secondCustomAdapter: SecondCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showData()

        adapter = CustomAdapter(list)
        secondCustomAdapter = SecondCustomAdapter(listTwo)


    }
    private fun showData(){
        for (i in 1..47){
            list.add(Model(array[i]))
        }
        for (i in 1..5){
            listTwo.add(Model(arrayTwo[i]))
        }
    }
}