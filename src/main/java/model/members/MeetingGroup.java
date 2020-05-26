package model.members;

import model.administrators.MeetingGroupProposal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class MeetingGroup implements Serializable {
    //ManyToMany
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Meeting> meetings = new HashSet<Meeting>();

    //OneToOne
    @ManyToOne
    private MeetingGroupProposal meetingGroupProposal;

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

    public MeetingGroup() {}

    public MeetingGroup(MeetingGroupProposal meetingGroupProposal) {
        this.meetingGroupProposal = meetingGroupProposal;
    }

    public Set<Meeting> getMeetings() {
        return new HashSet<Meeting>(meetings);
    }

    public void addMeetings(Meeting meeting) {
        this.meetings.add(meeting);
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MeetingGroupProposal getMeetingGroupProposal() {
        return meetingGroupProposal;
    }

    public void setMeetingGroupProposal(MeetingGroupProposal meetingGroupProposal) {
        this.meetingGroupProposal = meetingGroupProposal;
    }

    public long getId() {
        return id;
    }

}
