package edu.uw.ischool.jho12.quizdroid

class InMemoryTopicRepository : TopicRepository {
    private val topics = listOf(
        Topic(
            "Math",
            "Explore the universe of numbers",
            "Dive deep into the world of mathematics, from basic arithmetic to complex equations.",
            listOf(
                Question("What is 2 + 2?", listOf("1", "2", "3", "4"), "4"),
                Question("What is the square root of 16?", listOf("2", "3", "4", "5"), "4"),
                Question("What is 10 * 10?", listOf("10", "100", "1000", "1"), "100"),
                Question("What is the derivative of x^2?", listOf("2", "2x", "x^2", "None of the above"), "2x")
            )
        ),
        Topic(
            "Physics",
            "Unlock the secrets of the universe",
            "Explore the fundamental nature of the cosmos. Understand forces, energy, and matter, and how they interact.",
            listOf(
                Question("How do you calculate force?", listOf("F=a+b", "F=m*a", "F=m^2*a", "F=c*k"), "F=m*a"),
                Question("What is the speed of light?", listOf("3x10^8 m/s", "2x10^8 m/s", "1x10^8 m/s", "4x10^8 m/s"), "3x10^8 m/s"),
                Question("What unit is electric current measured in?", listOf("Volt", "Watt", "Ampere", "Ohm"), "Ampere"),
                Question("Who discovered the law of gravity?", listOf("Isaac Newton", "Albert Einstein", "Nikola Tesla", "Galileo Galilei"), "Isaac Newton")
            )
        ),
        Topic(
            "Marvel Super Heroes",
            "Heroes, Assemble!",
            "From the streets of New York to the far reaches of the Galaxy, explore the world of Marvel Super Heroes.",
            listOf(
                Question("What is Iron Man's real name?", listOf("Tony Stark", "Tony the Tiger", "Tony Parker", "Tony Kim"), "Tony Stark"),
                Question("Who is the God of Thunder?", listOf("Loki", "Thor", "Odin", "Hela"), "Thor"),
                Question("Which superhero is known as the 'Merc with a Mouth'?", listOf("Spider-Man", "Deadpool", "Wolverine", "Captain America"), "Deadpool"),
                Question("Who is the king of Wakanda?", listOf("T'Challa", "M'Baku", "N'Jadaka", "Nakia"), "T'Challa")
            )
        ),
        Topic(
            "History",
            "Journey through the past",
            "Embark on a time-traveling adventure to discover the events and figures that have shaped our world.",
            listOf(
                Question("What is Benjamin Franklin's last name?", listOf("Franklin", "Kim", "Oh", "Park"), "Franklin"),
                Question("Who was the first president of the United States?", listOf("Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"), "George Washington"),
                Question("In which year did the Titanic sink?", listOf("1912", "1905", "1898", "1923"), "1912"),
                Question("Which empire was known as the Land of the Rising Sun?", listOf("The Roman Empire", "The Ottoman Empire", "The British Empire", "The Japanese Empire"), "The Japanese Empire")
            )
        )
    )

    override fun getTopics(): List<Topic> = topics
}

