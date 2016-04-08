package com.excilys.android.formation.chatlite.model;

import java.util.UUID;

/**
 * Created by Aurélien LEJEAU on 08/04/16.
 */
public class ComplexMessage extends SimpleMessage {
    String attachment = null;

    ComplexMessage() {
        super();
    }

    /**
     * Creates a message containing an randomly generated UUID,
     * an login, a message and an attachment.
     *
     * @param m          a SimpleMessage
     * @param attachment a BASE64 encoded PNG or JPG String
     */
    ComplexMessage(SimpleMessage m, String attachment) {
        this.uuid = m.getUuid();
        this.login = m.getLogin();
        this.message = m.getMessage();
        this.attachment = attachment;
    }

    /**
     * Creates a message containing an randomly generated UUID,
     * an login, a message and an attachment.
     *
     * @param author     the message's login
     * @param content    the message's message
     * @param attachment a BASE64 encoded PNG or JPG String
     */
    public ComplexMessage(String author, String content, String attachment) {
        super(author, content);
        this.attachment = attachment;
    }

    /**
     * Creates a message containing an UUID, an login, a message and an attachment.
     *
     * @param uuid       the message's UUID
     * @param author     the message's login
     * @param content    the message's message
     * @param attachment a BASE64 encoded PNG or JPG String
     */
    public ComplexMessage(UUID uuid, String author, String content, String attachment) {
        super(uuid, author, content);
        this.attachment = attachment;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComplexMessage that = (ComplexMessage) o;

        return attachment != null ? attachment.equals(that.attachment) : that.attachment == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComplexMessage{" +
                "attachment='" + attachment + '\'' +
                '}';
    }
}
