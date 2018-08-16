package com.iyanuadelekan.kanary.app.component.mailer

/**
 * @class [BaseMailer] - Base framework mailer class.
 */
abstract class BaseMailer {

    private lateinit var body: String
    private lateinit var title: String
    private lateinit var sender: String
    private lateinit var subject: String
    private lateinit var receiver: String

    /**
     * Invoked to set the body of the email to be set.
     *
     * @param body - The body of the mail.
     * @return [BaseMailer] - Current mailer instance.
     */
    fun setBody(body: String): BaseMailer {
        this.body = body
        return this
    }

    /**
     * Invoked to get the email body.
     *
     * @return [String] - Email body.
     */
    fun getBody(): String = body

    /**
     * Invoked to set the title of the email.
     *
     * @param title - Title of email.
     * @return [BaseMailer] - Current mailer instance.
     */
    fun setTitle(title: String): BaseMailer {
        this.title = title
        return this
    }

    /**
     * Retrieves the title of the email.
     *
     * @return [String] - The email title.
     */
    fun getTitle(): String = title

    /**
     * Sets the sender of the mail.
     *
     * @param sender - A valid sender email address.
     * @return [BaseMailer] - Current mailer instance.
     */
    fun setSender(sender: String): BaseMailer {
        this.sender = sender
        return this
    }

    /**
     * Retrieves the sender of the email.
     *
     * @return [String] - Sender email address.
     */
    fun getSender(): String = sender

    /**
     * Sets the subject of the email.
     *
     * @param subject - Email subject.
     * @return [BaseMailer] - Current mailer instance.
     */
    fun setSubject(subject: String): BaseMailer {
        this.subject = subject
        return this
    }

    /**
     * Retrieves the set email subject.
     *
     * @return [String] - Email subject.
     */
    fun getSubject(): String = subject

    /**
     * Sets the receiver of the mail.
     *
     * @param receiver - Valid receiver email address.
     * @return [BaseMailer] - Current mailer instance.
     */
    fun setReceiver(receiver: String): BaseMailer {
        this.receiver = receiver
        return this
    }

    /**
     * Retrieves the receiver email address.
     *
     * @return [String] - Receiver email address.
     */
    fun getReceiver(): String = receiver

    /**
     * Invoked to send the built email. This method
     * utilizes a pre-configured [MailerServiceAdapter] to send
     * the mail to its specified destination.
     */
    abstract fun send(): Any
}