package com.iyanuadelekan.kanary.app.adapter.service.mail

/**
 * Interface to be extended by email service providers
 * to facilitate the sending of mails in Kanary applications via their service.
 */
interface MailerServiceAdapter {

    /**
     * @property clientId - Unique identifier of client. Often generated
     * upon registration with an email service provider. Kindly contact your
     * service provider for help retrieving this.
     */
    val clientId: String

    /**
     * @property clientSecret - Client secret of service user. This is generally
     * generated upon registration with an email service provider. Please contact
     * your service provider to request for one.
     */
    val clientSecret: String

    /**
     * This method upon implementation will be invoked to send
     * emails to receivers via a compatible mailer service.
     *
     * @param title - The email title.
     * @param subject - The email subject.
     * @param body - The body of the email.
     * @param sender - The sender of the email.
     * @param receiver - The email receiver.
     * @return [Any] - Service provider response.
     */
    fun sendMail(
            title: String,
            subject: String,
            body: String,
            sender: String,
            receiver: String): Any
}