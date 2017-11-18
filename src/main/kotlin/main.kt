import biweekly.Biweekly
import biweekly.ICalendar
import biweekly.component.VEvent
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.*

fun main(args: Array<String>) {
  val server = embeddedServer(Netty, 8080) {
    routing {
      get("/") {
        val ical = ICalendar()
        val event = VEvent()
        event.setSummary("Christmas")

        val date = GregorianCalendar(2017, 11, 25).time
        event.setDateStart(date)
        event.setDateEnd(date)

        ical.addEvent(event)

        call.respondText(Biweekly.write(ical).go(), ContentType("text", "calendar"))
      }
    }
  }
  server.start(wait = true)
}