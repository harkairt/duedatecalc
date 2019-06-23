import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DueDateCalculationInput(private val localDateTime: LocalDateTime, private val turnoverHour: Int) {
    override fun toString(): String {
        return "$localDateTime, $turnoverHour hours."
    }

    fun getDueDate(): LocalDateTime {
        return localDateTime.plusWorkingHours(turnoverHour)
    }
}

private fun LocalDateTime.plusWorkingHours(workingHour: Int): LocalDateTime {
    val workingDays = workingHour.div(workingHourLength)
    val remainingWorkingHours = workingHour.rem(workingHourLength).toLong()

    var newDate = toLocalDate()
    var newTime = toLocalTime()

    if (newTime.wouldOverflowAfterAdding(remainingWorkingHours))
        newDate = newDate.plusWorkingDay(1)

    newDate = newDate.plusWorkingDay(workingDays)
    newTime = newTime.plusWorkingHour(remainingWorkingHours)

    return LocalDateTime.of(newDate, newTime)
}

private fun LocalTime.wouldOverflowAfterAdding(hoursToAdd: Long) =
    !this.plusHours(hoursToAdd).isWorkingHour()

private fun LocalDate.plusWorkingDay(daysToAdd: Int): LocalDate {
    if (daysToAdd == 0)
        return this

    var result: LocalDate = this

    repeat(daysToAdd) {
        if (result.isFriday())
            result = result.plusDays(2)

        result = result.plusDays(1)
    }

    return result
}

private fun LocalTime.plusWorkingHour(hoursToAdd: Long): LocalTime {
    if (hoursToAdd == 0L)
        return this

    var incrementedLocalTime = this.plusHours(hoursToAdd)

    if (!incrementedLocalTime.isWorkingHour()) {
        incrementedLocalTime = shiftIntoWorkingHourFrame(incrementedLocalTime)
    }

    return incrementedLocalTime
}

private fun shiftIntoWorkingHourFrame(result: LocalTime) = result.plusHours(nonWorkingHourLength)