package com.lepltd.util

object Enum {

  object ResponseStatus extends Enumeration {

    val Found    = Value(302)
    val NotFound = Value(106)

    val DuplicateRequest = Value(205)
    val Success          = Value(200)

    val Created = Value(201)

    val Failed = Value(400)

  }

}
