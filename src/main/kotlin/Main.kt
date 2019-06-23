import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime

const val startOfWorkingHour = 9
const val endOfWorkingHour = 17
const val workingHourLength = endOfWorkingHour - startOfWorkingHour
const val nonWorkingHourLength: Long = startOfWorkingHour + (24 - endOfWorkingHour).toLong()

// Inclusive start, exclusive end, [9:00, 17:00), or [9:00, 16:59:59]
val startOfWorkingHourLocalTime: LocalTime = LocalTime.of(startOfWorkingHour, 0, 0)
val endOfWorkingHourLocalTime: LocalTime = LocalTime.of(endOfWorkingHour, 0, 0).minusSeconds(1)

fun main(args: Array<String>) {
    getInputList().forEach {
        println("$it -> dueDate: ${it.getDueDate()}")
    }
}

private fun getInputList(): List<DueDateCalculationInput> {
    val inputList = mutableListOf<DueDateCalculationInput>()

    readLines("testInput.txt").forEach { lineContent ->
        lineContent.split(',').let {
            inputList.add(DueDateCalculationInput(LocalDateTime.parse(it[0]), it[1].trim().toInt()))
        }
    }

    return inputList
}

fun readLines(fileName: String): List<String> = File(fileName).readLines()