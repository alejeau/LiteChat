package com.excilys.android.formation.chatlite.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.util.UUID;

public class SimpleMessage implements JsonSerializable {
    protected UUID uuid = null;
    protected String login = null;
    protected String message = null;

    public SimpleMessage() {
    }

    /**
     * Creates a message containing an randomly generated UUID,
     * an login and a message.
     *
     * @param login   the message's login
     * @param message the message's message
     */
    public SimpleMessage(String login, String message) {
        generateUUID();
        this.login = login;
        this.message = message;
    }

    /**
     * Creates a message containing an UUID, an login and a message.
     *
     * @param uuid    the message's UUID
     * @param login   the message's login
     * @param message the message's message
     */
    public SimpleMessage(UUID uuid, String login, String message) {
        this.uuid = uuid;
        this.login = login;
        this.message = message;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMessage that = (SimpleMessage) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "uuid=" + uuid +
                ", login='" + login + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * Serialization method called when no additional type information is
     * to be included in serialization.
     *
     * @param gen
     * @param serializers
     */
    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        startSerialize(gen);
        doSerialize(gen, serializers);
        endSerialize(gen);
    }

    protected void startSerialize(JsonGenerator gen) throws IOException {
        gen.writeStartObject();
    }

    protected void doSerialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStringField("uuid", this.uuid.toString());
        gen.writeStringField("login", this.login);
        gen.writeStringField("message", this.message);
    }

    protected void endSerialize(JsonGenerator gen) throws IOException {
        gen.writeEndObject(); // Closing Object..
    }

    /**
     * Serialization method called when additional type information is
     * expected to be included in serialization, for deserialization to use.
     * <p/>
     * Usually implementation consists of a call to one of methods
     * in {@link TypeSerializer} (such as {@link TypeSerializer#writeTypePrefixForObject(Object, JsonGenerator)})
     * followed by serialization of contents,
     * followed by another call to {@link TypeSerializer}
     * (such as {@link TypeSerializer#writeTypeSuffixForObject(Object, JsonGenerator)}).
     * Exact methods to call in {@link TypeSerializer} depend on shape of JSON Object used
     * (Array, Object or scalar like String/Number/Boolean).
     * <p/>
     * Note that some types (most notably, "natural" types: String, Integer,
     * Double and Boolean) never include type information.
     *
     * @param gen
     * @param serializers
     * @param typeSer
     */
    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {

    }
}
