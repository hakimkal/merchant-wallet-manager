package com.lepltd.util

object Enum {

  object TransactionStatus extends Enumeration {
    val Daily    = Value("daily")
    val Weekly   = Value("weekly")
    val Monthly  = Value("montly")
    val Quaterly = Value("quaterly")
    val Yearly   = Value("yearly")
  }

  object ResponseStatus extends Enumeration {
    //  val Queued              = Value(101)
    //  val PendingConfirmation = Value(102)
    // val PendingValidation    = Value(103)
    //  val Validated           = Value(104)
    //  val Booked              = Value(105)
    val Found            = Value(302)
    val NotFound         = Value(106)
    //  val InValidAmount       = Value(107)
    //  val NotSupported        = Value(201)
    //  val InsufficientFunds   = Value(202)
    //  val ApplicationError    = Value(203)
    //  val NotAllowed          = Value(204)
    val DuplicateRequest = Value(205)
    val Success          = Value(200)

    val Created = Value(201)

    val Failed = Value(400)
    //   val Throttled           = Value(401)
    //   val Expired             = Value(402)
    //   val Reversed            = Value(500)

  }

}
