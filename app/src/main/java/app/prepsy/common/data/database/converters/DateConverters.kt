package app.prepsy.common.data.database.converters

import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimeStamp(value: String): Date {
        // datetime string in the format: 2021-03-18T07:39:26.840Z
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)

        return try {
            inputFormatter.parse(value) ?: Date()
        } catch (e: ParseException) {
            Date()
        }
    }

    @TypeConverter
    fun toTimeStamp(data: Date): String = data.toString();
}