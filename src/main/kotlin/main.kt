import biweekly.Biweekly
import biweekly.ICalendar
import biweekly.component.VEvent
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.time.LocalDate
import java.time.Month

fun main(args: Array<String>) {
  val server = embeddedServer(Netty, 8080) {
    routing {
      get("/") {
        calendar {
          val event = VEvent()
          event.setSummary("Christmas")

          event.date = 25 December 2017

          addEvent(event)
        }
      }
    }
  }
  server.start(wait = true)
}

suspend fun PipelineContext<Unit, ApplicationCall>.calendar(builder: ICalendar.() -> Unit) = call.respondText(Biweekly.write(ICalendar().apply(builder)).go(), ContentType.Text.Plain)

infix fun Int.December(year: Int) = LocalDate.of(year, Month.DECEMBER, this)!!

var VEvent.date: LocalDate
  set(value) {
    val date = java.sql.Date.valueOf(value)
    setDateStart(date)
    setDateEnd(date)
  }
  get() = TODO()