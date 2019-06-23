import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime

class DueDateTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-24T09:00:00, 1, 2019-06-24T10:00:00",
            "2019-06-24T09:00:00, 2, 2019-06-24T11:00:00",
            "2019-06-24T09:00:00, 3, 2019-06-24T12:00:00",
            "2019-06-24T09:00:00, 4, 2019-06-24T13:00:00",
            "2019-06-24T09:00:00, 5, 2019-06-24T14:00:00",
            "2019-06-24T09:00:00, 6, 2019-06-24T15:00:00",
            "2019-06-24T09:00:00, 7, 2019-06-24T16:00:00"
        ], delimiter = ','
    )
    fun shouldCalculateDueDateToday(input: String, turnover: String, expected: String) {
        assertDueDateMatches(input, turnover, expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-24T09:00:00, 8, 2019-06-25T09:00:00",
            "2019-06-24T10:00:00, 7, 2019-06-25T09:00:00",
            "2019-06-24T11:00:00, 6, 2019-06-25T09:00:00",
            "2019-06-24T12:00:00, 5, 2019-06-25T09:00:00",
            "2019-06-24T13:00:00, 4, 2019-06-25T09:00:00",
            "2019-06-24T14:00:00, 3, 2019-06-25T09:00:00",
            "2019-06-24T15:00:00, 2, 2019-06-25T09:00:00",
            "2019-06-24T16:00:00, 1, 2019-06-25T09:00:00"

        ], delimiter = ','
    )
    fun shouldJumpToBeginningOfTheFollowinWorkingDay(input: String, turnover: String, expected: String) {
        assertDueDateMatches(input, turnover, expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-24T09:00:00, 16, 2019-06-26T09:00:00",
            "2019-06-24T10:00:00, 15, 2019-06-26T09:00:00",
            "2019-06-24T11:00:00, 14, 2019-06-26T09:00:00",
            "2019-06-24T12:00:00, 13, 2019-06-26T09:00:00",
            "2019-06-24T13:00:00, 12, 2019-06-26T09:00:00",
            "2019-06-24T14:00:00, 11, 2019-06-26T09:00:00",
            "2019-06-24T15:00:00, 10, 2019-06-26T09:00:00",
            "2019-06-24T16:00:00, 9, 2019-06-26T09:00:00"

        ], delimiter = ','
    )
    fun shouldJumpToBeginningOfTheDayAfterFollowinWorkingDay(input: String, turnover: String, expected: String) {
        assertDueDateMatches(input, turnover, expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-28T16:50:00, 1, 2019-07-01T09:50:00",
            "2019-06-28T16:50:00, 41, 2019-07-08T09:50:00",
            "2019-06-28T16:50:00, 81, 2019-07-15T09:50:00",
            "2019-06-28T16:50:00, 121, 2019-07-22T09:50:00",
            "2019-06-28T16:50:00, 161, 2019-07-29T09:50:00"
        ], delimiter = ','
    )
    fun shouldSkipWeekend(input: String, turnover: String, expected: String) {
        assertDueDateMatches(input, turnover, expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-25T10:32:00, 20, 2019-06-27T14:32:00",
            "2019-06-25T11:50:00, 27, 2019-06-28T14:50:00",
            "2019-06-26T12:10:44, 16, 2019-06-28T12:10:44",
            "2019-06-25T16:30:00, 30, 2019-07-01T14:30:00"
        ], delimiter = ','
    )
    fun shouldCalculateDueDate(input: String, turnover: String, expected: String) {
        assertDueDateMatches(input, turnover, expected)
    }

    private fun assertDueDateMatches(input: String, turnover: String, expected: String) {
        val input = DueDateCalculationInput(LocalDateTime.parse(input), turnover.trim().toInt())
        assertEquals(LocalDateTime.parse(expected), input.getDueDate())
    }
}