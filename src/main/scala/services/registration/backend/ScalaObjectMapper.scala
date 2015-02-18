package com.belongo.services.registration.backend

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * Created by Simon on 18.02.2015.
 */
class ScalaObjectMapper extends ObjectMapper{
    registerModule(DefaultScalaModule)
}
