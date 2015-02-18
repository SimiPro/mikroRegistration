package com.belongo.services.registration.main

import com.belongo.services.registration.UserRegistrationConfiguration
import org.springframework.boot.SpringApplication


/**
 * Created by Simon on 18.02.2015.
 */
object UserRegistrationMain {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[UserRegistrationConfiguration])
  }

}
