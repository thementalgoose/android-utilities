package tmg.utilities.utils

import tmg.utilities.extensions.dayOfMonth
import tmg.utilities.extensions.month
import tmg.utilities.extensions.year
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        /**
         * Initialise a new date
         *
         * @param year The year
         * @param month The month (1 - 12)
         * @param day The day
         * @param hour (Optional)
         * @param minutes (Optional)
         * @param seconds (Optional)
         */
        fun date(year: Int, month: Int, day: Int, hour: Int = 0, minutes: Int = 0, seconds: Int = 0): Date {
            val cal: Calendar = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, day)
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minutes)
            cal.set(Calendar.SECOND, seconds)
            cal.set(Calendar.MILLISECOND, 0)
            return cal.time
        }

        /**
         * Initialise a new date
         *
         * @param hour The hour
         * @param minutes The hour
         * @param seconds (Optional)
         */
        fun time(hour: Int, minutes: Int, seconds: Int = 0): Date {
            val date: Date = Date()
            return date(date.year(), date.month() - 1, date.dayOfMonth(), hour, minutes, seconds)
        }

    }
}