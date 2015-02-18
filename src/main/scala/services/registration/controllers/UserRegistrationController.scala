package com.belongo.services.registration.controllers

import com.belongo.services.registration.MessagingNames._
import com.belongo.services.registration.backend.{RegisteredUser, RegisteredUserRepository}
import com.mongodb.DuplicateKeyException
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._

@RestController
class UserRegistrationController @Autowired()(registeredUserRepository: RegisteredUserRepository, rabbitTemplate: RabbitTemplate){

  @RequestMapping(value=Array("/user"), method= Array(RequestMethod.POST))
  def registerUser(@RequestBody request: RegistrationRequest) = {
    val registeredUser = new RegisteredUser(null, request.email, request.password)
    registeredUserRepository.save(registeredUser)
    rabbitTemplate.convertAndSend(exchangeName, routingKey,
      NewRegistrationNotification(registeredUser.id, request.email, request.password))
    RegistrationResponse(registeredUser.id, request.email)
  }


  ///// WTF MONGODB DOESNT SET MY INDEX AND THEN SURLY NO EXCEPTION CAN OCCUR!! IM MAD
  @ResponseStatus(value = HttpStatus.CONFLICT, reason  = "duplicate email address")
  @ExceptionHandler(Array(classOf[DuplicateKeyException]))
  def duplicateEmailAddress(): Unit = {}
}

case class RegistrationRequest(email: String, password: String)
case class RegistrationResponse(id: String, email: String)
case class NewRegistrationNotification(id:String, emailAddress:String, password: String)
