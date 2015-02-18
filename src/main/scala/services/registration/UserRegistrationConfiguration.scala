package com.belongo.services.registration


import com.belongo.services.registration.backend.ScalaObjectMapper
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, Primary}

/**
 * Created by Simon on 18.02.2015.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
class UserRegistrationConfiguration {
  import com.belongo.services.registration.MessagingNames._

  @Bean
  @Primary
  def scalaObjectMapper() = new ScalaObjectMapper()

  @Bean
  def rabbitTemplate(connectionFactory: ConnectionFactory) = {
    val template = new RabbitTemplate(connectionFactory)
    val jsonConverter = new Jackson2JsonMessageConverter
    jsonConverter.setJsonObjectMapper(scalaObjectMapper())
    template.setMessageConverter(jsonConverter)
    template
  }

  @Bean
  def userRegistrationsExchange() = new TopicExchange(exchangeName)
}

object MessagingNames {
  val queueName = "user-registration"
  val routingKey = queueName
  val exchangeName = "user-registrations"
}