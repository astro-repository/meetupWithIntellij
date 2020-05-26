package model.members;

import util.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Meeting implements Serializable {

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<MeetingAttendee> meetingAttendeeList = new HashSet<MeetingAttendee>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<MeetingNotAttendee> meetingNotAttendeeList = new HashSet<MeetingNotAttendee>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<MeetingWaitlistMember> meetingWaitlistList = new HashSet<MeetingWaitlistMember>();

    //OneTonOne
    @ManyToOne
    private MeetingGroup meetingGroup;

    //Embedded
    @Embedded
    private MeetingLocation location;

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private final List<Message> messages = new ArrayList<Message>();

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

    public Meeting() {}

    public Meeting(Set<MeetingAttendee> meetingAttendees)  {
        this.meetingAttendeeList = meetingAttendees;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public Set<MeetingAttendee> getMeetingAttendeeList() {
        return new HashSet<MeetingAttendee>(meetingAttendeeList);
    }

    public void setMeetingAttendeeList(Set<MeetingAttendee> meetingAttendeeList) {
        this.meetingAttendeeList = meetingAttendeeList;
    }

    public void addMeetingAttendeeList(MeetingAttendee meetingAttendee) {
        this.meetingAttendeeList.add(meetingAttendee);
    }

    public Set<MeetingNotAttendee> getMeetingNotAttendeeList() {
        return meetingNotAttendeeList;
    }

    public void setMeetingNotAttendeeList(Set<MeetingNotAttendee> meetingNotAttendeeList) {
        this.meetingNotAttendeeList = meetingNotAttendeeList;
    }

    public Set<MeetingWaitlistMember> getMeetingWaitlistList() {
        return meetingWaitlistList;
    }

    public void setMeetingWaitlistList(Set<MeetingWaitlistMember> meetingWaitlistList) {
        this.meetingWaitlistList = meetingWaitlistList;
    }

    public MeetingGroup getMeetingGroup() {
        return meetingGroup;
    }

    public void setMeetingGroup(MeetingGroup meetingGroup) {
        this.meetingGroup = meetingGroup;
    }

    public MeetingLocation getLocation() {
        return location;
    }

    public void setLocation(MeetingLocation location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingAttendeeList=" + meetingAttendeeList +
                ", meetingNotAttendeeList=" + meetingNotAttendeeList +
                ", meetingWaitlistList=" + meetingWaitlistList +
                ", location=" + location +
                ", id=" + id +
                ", messages=" + messages +
                ", createdDate=" + createdDate +
                ", lastModificationDate=" + lastModificationDate +
                '}';
    }
}
