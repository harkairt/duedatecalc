import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

fun LocalDate.isFriday(): Boolean = this.dayOfWeek == DayOfWeek.FRIDAY

fun LocalTime.isWithinInclusive(start: LocalTime, end: LocalTime): Boolean
        = (this == start || isAfter(start)) && (this == end || isBefore(end))

fun LocalTime.isWorkingHour(): Boolean = isWithinInclusive(startOfWorkingHourLocalTime, endOfWorkingHourLocalTime)