package ma.demo.devfest.polopatique.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import java.util.Date;

/**
 * Takhle vypadá jeden náš "message". Klíč a text.
 *
 */
@Entity
@Unindex
@Cache(expirationSeconds = 120)
public class Message {

    /**
     * Primární klíč a klíč hlášky.
     */
    @Id
    private String key;

    /**
     * Tělo hlášky.
     */
    @Index
    private String message;

    /**
     * Kdy byla vytvořená.
     */
    @Index
    private Date dateCreated;

    /**
     * Kdy byla změněná.
     */
    private Date dateUpdated;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }

    /**
     * Vkusná metoda, která vrátí můj klíč.
     *
     * @return
     */
    public Key<Message> createOfyKey() {
        return createOfyKey(key);
    }

    /**
     * Další vkusná metoda, která vrací obecně "nějaký klíč" hlášky, podle zadaného parametru.
     * @param key
     * @return
     */
    public static Key<Message> createOfyKey(String key) {
        return Key.create(Message.class, key);
    }
}
