

presentation {
  title = "Create your own DSL in Kotlin"

  speaker {
    name = "Victor Kropp"
    company = JetBrains

    social(
        twitter = `@kropp`,
        github = "kropp",
        blog = "victor.kropp.name"
    )
  }
}















fun presentation(builder: Presentation.() -> Unit) = Presentation().apply(builder)
class Presentation {
  lateinit var title: String
}

val `@kropp` = ""

fun speaker(builder: Speaker.() -> Unit) = Speaker().apply(builder)

class Speaker {
  lateinit var name: String
  lateinit var company: JetBrains
}

object JetBrains

fun social(twitter: String, github: String, blog: String) = twitter+github+blog