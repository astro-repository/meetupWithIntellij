package util;

import model.members.Meeting;
import model.members.Member;
import model.users.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Meeting meeting;

    @ManyToOne
    private User author;

    @OneToOne
    private User sender;

    @Column
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModificationDate;

    public Message() {}

    public Message(User author, String message) {
        this.author = author;
        this.message = message;
    }

    @PrePersist
    public void created() {
        this.createdDate = new Date();
        this.lastModificationDate = new Date();
    }

    @PreUpdate
    public void updated(){
        this.lastModificationDate = new Date();
    }

    public long getId() {
        return id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", meeting=" + meeting +
//                ", author=" + author +
                ", message='" + message + '\'' +
                ", createdDate=" + createdDate +
                ", lastModificationDate=" + lastModificationDate +
                '}';
    }
}
