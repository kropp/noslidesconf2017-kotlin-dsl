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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.time.temporal.TemporalAdjusters
import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>) {
  val server = embeddedServer(Netty, 8080) {
    routing {
      get("/") {
        calendar {
          val Christmas = 25 December 2017
          event {
            title = "Christmas"
            date = Christmas
          }

          val `4th Advent` = Sunday before Christmas
          (`4th Advent` - 3 * week .. `4th Advent` every week).forEachIndexed { i, Advent ->
            event {
              title = "$i Advent"
              date = Advent
            }
          }
        }
      }
    }
  }
  server.start(wait = true)
}



suspend fun PipelineContext<Unit, ApplicationCall>.calendar(builder:  ICalendar.() -> Unit) = call.respondText(Biweekly.write(ICalendar().apply(builder)).go(), ContentType.Text.Plain)

infix fun Int.December(year: Int) = LocalDate.of(year, Month.DECEMBER, this)!!

var VEvent.date: LocalDate
  set(value) {
    val date = java.sql.Date.valueOf(value)
    setDateStart(date)
    setDateEnd(date)
  }
  get() = TODO()

fun ICalendar.event(builder: VEvent.() -> Unit) = addEvent(VEvent().apply(builder))
var VEvent.title: String
  set(value) {
    setSummary(value)
  }
  get() = TODO()

val Sunday = DayOfWeek.SUNDAY
infix fun DayOfWeek.before(date: LocalDate) = date.with(TemporalAdjusters.previous(this))!!

val week = Period.ofDays(7)!!
operator fun Int.times(p: Period) = p.multipliedBy(this)!!

infix fun ClosedRange<LocalDate>.every(period: Period) = buildSequence {
  var current = start
  do {
    yield(current)
    current += period
  } while (current <= endInclusive)
}
