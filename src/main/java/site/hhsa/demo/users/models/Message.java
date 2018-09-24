package site.hhsa.demo.users.models;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {

    //columns

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "VARCHAR(255) default '<No Subject>'")
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(name = "time_sent", nullable = false, columnDefinition = "TIMESTAMP")
    private String timeSent;

    @Column
    private boolean opened = false;

    @Column(name = "receiver_del")
    private boolean receiverDel = false;

    //relationships

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;

    // ===== Constructors ===== \\

    public Message(){}

    public Message(String subject, String body, String timeSent, boolean opened, boolean receiverDel, User sender, User receiver) {
        this.subject = subject;
        this.body = body;
        this.timeSent = timeSent;
        this.opened = opened;
        this.receiverDel = receiverDel;
        this.sender = sender;
        this.receiver = receiver;
    }

    // ===== Getters & Setters ===== \\


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean isReceiverDel() {
        return receiverDel;
    }

    public void setReceiverDel(boolean receiverDel) {
        this.receiverDel = receiverDel;
    }
}
