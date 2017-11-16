import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
  val server = embeddedServer(Netty, 8080) {
    routing {
      get("/") {
        call.respondText("Hello, NoSlidesConf!", ContentType.Text.Html)
      }
    }
  }
  server.start(wait = true)
}