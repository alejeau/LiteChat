package com.excilys.android.formation.chatlite.model;

import org.json.JSONObject;

import java.util.UUID;

public class Message {
    protected UUID uuid = null;
    protected String author = null;
    protected String content = null;
    protected String attachment = null;

    public Message() {}

    /**
     * Creates a message containing an randomly generated UUID,
     * an author and a content.
     *
     * @param author the message's author
     * @param content the message's content
     */
    public Message(String author, String content) {
        generateUUID();
        this.author = author;
        this.content = content;
    }

    /**
     * Creates a message containing an UUID, an author and a content.
     *
     * @param uuid the message's UUID
     * @param author the message's author
     * @param content the message's content
     */
    public Message(UUID uuid, String author, String content) {
        this.uuid = uuid;
        this.author = author;
        this.content = content;
    }

    /**
     * Creates a message containing an randomly generated UUID,
     * an author, a content and an attachment.
     *
     * @param author the message's author
     * @param content the message's content
     * @param attachment a BASE64 encoded PNG or JPG String
     */
    public Message(String author, String content, String attachment) {
        generateUUID();
        this.author = author;
        this.content = content;
        this.attachment = attachment;
    }

    /**
     * Creates a message containing an UUID, an author, a content and an attachment.
     *
     * @param uuid the message's UUID
     * @param author the message's author
     * @param content the message's content
     * @param attachment a BASE64 encoded PNG or JPG String
     */
    public Message(UUID uuid, String author, String content, String attachment) {
        this.uuid = uuid;
        this.author = author;
        this.content = content;
        this.attachment = attachment;
    }

    /**
     * Generates a random UUID using UUID.randomUUID()
     * and assigns it to the message uuid.
     */
    public void generateUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        Message message = (Message) o;

        if (uuid != null ? !uuid.equals(message.uuid) : message.uuid != null) return false;
        if (author != null ? !author.equals(message.author) : message.author != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null)
            return false;
        return !(attachment != null ? !attachment.equals(message.attachment) : message.attachment != null);

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        return result;
    }
}