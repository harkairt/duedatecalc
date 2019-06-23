import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime

class LocalTimeExtensionTest
{
    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-24T09:00:00",
            "2019-06-24T10:00:00",
            "2019-06-24T11:00:00",
            "2019-06-24T12:00:00",
            "2019-06-24T13:00:00",
            "2019-06-24T14:00:00",
            "2019-06-24T15:00:00",
            "2019-06-24T16:00:00",
            "2019-06-24T16:59:59"
        ]
    )
    fun shouldBeWithinWorkingHourTimeFrame(inputDate: String) {
        assert(LocalDateTime.parse(inputDate).toLocalTime().isWorkingHour())
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "2019-06-24T00:00:00",
            "2019-06-24T01:00:00",
            "2019-06-24T02:00:00",
            "2019-06-24T03:00:00",
            "2019-06-24T04:00:00",
            "2019-06-24T05:00:00",
            "2019-06-24T06:00:00",
            "2019-06-24T07:00:00",
            "2019-06-24T08:00:00",
            "2019-06-24T08:59:59",
            "2019-06-24T17:00:00",
            "2019-06-24T17:00:00",
            "2019-06-24T18:00:00",
            "2019-06-24T19:00:00",
            "2019-06-24T20:00:00",
            "2019-06-24T21:00:00",
            "2019-06-24T22:00:00",
            "2019-06-24T23:00:00",
            "2019-06-24T23:59:59"
        ]
    )
    fun shouldNotBeWithinWorkingHourTimeFrame(inputDate: String) {
        assert(!LocalDateTime.parse(inputDate).toLocalTime().isWorkingHour())
    }
}