package model.administrators;

import model.members.MeetingGroup;
import model.members.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MeetingGroupProposal implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private MeetingGroup meetingGroup;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Member member;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Administrator administrator;

    @Enumerated(value = EnumType.STRING)
    private MeetingGroupProposalStatus status = MeetingGroupProposalStatus.InVerification;

    @Enumerated(value = EnumType.STRING)
    private MeetingGroupProposalDecision decision;

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

    public MeetingGroupProposal() {}

    public MeetingGroupProposal(Member member) {
        this.member = member;
    }

    public MeetingGroup getMeetingGroup() {
        return meetingGroup;
    }

    public void setMeetingGroup(MeetingGroup meetingGroup) {
        this.meetingGroup = meetingGroup;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MeetingGroupProposalStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingGroupProposalStatus status) {
        this.status = status;
    }

    public MeetingGroupProposalDecision getDecision() {
        return decision;
    }

    public void setDecision(MeetingGroupProposalDecision decision) {
        this.decision = decision;
    }

    public long getId() {
        return id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    @Override
    public String toString() {
        return "MeetingGroupProposal{" +
                "status=" + status +
                ", decision=" + decision +
                ", id=" + id +
                '}';
    }
}
