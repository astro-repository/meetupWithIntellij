package model.members;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MeetingNotAttendee implements Serializable {

    @ManyToMany(mappedBy = "listNotAttendee")
    private Set<Member> members  = new HashSet<Member>();

    @ManyToOne
    private Meeting meeting;

    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModificationDate;

    @PrePersist
    public void created() {
        this.createdDate = new Date();
        this.lastModificationDate = new Date();
    }

    @PreUpdate
    public void updated(){
        this.lastModificationDate = new Date();
    }

    public MeetingNotAttendee() {}

    public MeetingNotAttendee(Meeting meeting) {
        this.meeting = meeting;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public long getId() {
        return id;
    }

}
