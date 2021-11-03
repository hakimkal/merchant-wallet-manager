package com.lepltd.db.postgres


import java.util.UUID

import akka.http.scaladsl.model.DateTime

object Tables {

  case class MerchantProfile(
    id: UUID,
    name: String,
    email: String,
    secret: String,
    is_active: Boolean = false,
    createdAt: DateTime,
    updatedAt: DateTime)

  case class MerchantWallet(
    id: UUID,
    merchantProfileId: UUID,
    previousBalance: String,
    currentBalance: String,
    createdAt: DateTime)
}
