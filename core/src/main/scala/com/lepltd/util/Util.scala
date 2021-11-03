package com.lepltd.util

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.util.Date

object Util {
  val DATE_FORMAT1 = "yyyy-MM-dd HH:mm:ss.SSS"
  val DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS"
  val DATE_FORMAT3 = "yyyy-MM-dd"
  val DATE_FORMAT4 = "yyyy-MM-dd'T'HH:mm:ss.SS"
  val DATE_FORMAT5 = "yyyy-MM-dd HH:mm:ss"

  def getCurrentDateTime(dateFormat: String = DATE_FORMAT1): String = {
    new SimpleDateFormat(dateFormat).format(new Date())
  }

  def stringToDate(date: String, format: String = DATE_FORMAT5): LocalDateTime = {
    LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format))
  }

}
