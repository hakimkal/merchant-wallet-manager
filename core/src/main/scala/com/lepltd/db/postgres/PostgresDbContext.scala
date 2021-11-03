package com.lepltd.db.postgres

import cats.effect.IO
import com.lepltd.util.Config

import java.time.{ LocalDate, LocalDateTime, LocalTime, ZoneId }
import java.util.{ Date, UUID }
import doobie.quill.DoobieContext
import doobie.util.ExecutionContexts
import doobie.util.meta.Meta
import doobie.util.transactor.Transactor
import io.getquill.{ idiom => _, mirrorContextWithQueryProbing, MappedEncoding, SnakeCase }

trait PostgresDbContext {

  val user = Config.postgresDbUser
  val pass = Config.postgresDbPassword
  val db   = Config.postgresDbName

  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",
    url    = s"jdbc:postgresql:$db",
    user   = user,
    pass   = pass
  )

  val quillContext = new DoobieContext.Postgres(SnakeCase) with EncoderDecoder

}

trait EncoderDecoder {
  implicit val uuidMeta: Meta[UUID] = Meta[String].timap(UUID.fromString)(_.toString)

  implicit val jodaLocalDateTimeDecoder =
    mirrorContextWithQueryProbing.MappedEncoding[Date, LocalDateTime](
      fromDateToLocalDateTime
    )

  implicit val jodaLocalDateTimeEncoder =
    MappedEncoding[LocalDateTime, Date](fromLocalDateTimeToDateTime)

  def fromDateToLocalDateTime(date: Date): LocalDateTime = {
    val local: LocalDate =
      date.toInstant
        .atZone(ZoneId.systemDefault())
        .toLocalDate;

    LocalDateTime.of(local, LocalTime.now)
  }

  def fromLocalDateTimeToDateTime(dateTime: LocalDateTime): Date =
    java.sql.Timestamp.valueOf(dateTime);

}
