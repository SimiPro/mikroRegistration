package com.belongo.services.registration.backend

import org.springframework.data.mongodb.core.index.Indexed
import scala.annotation.meta.field



case class RegisteredUser(id: String, @Indexed(unique=true)email: String, password:String)