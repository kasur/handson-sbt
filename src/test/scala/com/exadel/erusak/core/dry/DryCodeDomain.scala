package com.exadel.erusak.core.dry

/**
 * @author erusak.
 */
object DryCodeDomain {
  case class Email(
    subject: String,
    text: String,
    sender: String,
    recipient: String
  )

  type EmailFilter = Email => Boolean
  type User = String

  def newMailsForUser(userMails: Seq[Email], withFilter: EmailFilter) = userMails filter withFilter

  //here comes filters
  def sendBy: String => EmailFilter = sender => email => email.sender == sender

}
