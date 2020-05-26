package model.members;

import model.administrators.Administrator;
import model.administrators.MeetingGroupProposalDecision;
import model.administrators.MeetingGroupProposal;
import model.users.User;
import util.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Member extends User implements Serializable {
    // ManyToMany (1,n)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private final Set<MeetingAttendee> listAttendee = new HashSet<MeetingAttendee>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    private final Set<MeetingNotAttendee> listNotAttendee = new HashSet<MeetingNotAttendee>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    private final Set<MeetingWaitlistMember> listWaiting = new HashSet<MeetingWaitlistMember>();

    @OneToMany(mappedBy = "member")
    private final Set<MeetingGroupProposal> proposals = new HashSet<MeetingGroupProposal>();

    public Member(){}

    public Member(String firstName, String lastName, int number) {
        super(firstName, lastName, number);
    }

    public Set<MeetingAttendee> getListAttendee() {
        return new HashSet<MeetingAttendee>(listAttendee);
    }

    public void addListAttendee(MeetingAttendee listAttendee) {
        this.listAttendee.add(listAttendee);
    }

    public Set<MeetingNotAttendee> getListNotAttendee() {
        return new HashSet<MeetingNotAttendee>(listNotAttendee);
    }

    public void addListNotAttendee(MeetingNotAttendee listNotAttendee) {
        this.listNotAttendee.add(listNotAttendee);
    }

    public Set<MeetingWaitlistMember> getListWaiting() {
        return new HashSet<MeetingWaitlistMember>(listWaiting);
    }

    public void addListWaiting(MeetingWaitlistMember listWaiting) {
        this.listWaiting.add(listWaiting);
    }

    public Set<MeetingGroupProposal> getProposals() {
        return new HashSet<MeetingGroupProposal>(proposals);
    }

    public void addProposals(MeetingGroupProposal proposal) {
        this.proposals.add(proposal);
    }

//    public void proposeMeetingGroup(
//            Administrator administrator,
//            MeetingGroupProposal meetingGroupProposal) {
//
//        meetingGroupProposal.setMember(this);
//        meetingGroupProposal.setAdministrator(administrator);
//        administrator.addMeetingGroupProposals(meetingGroupProposal);
//
//    }

    public void proposeMeetingGroup(
            Administrator administrator,
            MeetingGroupProposal meetingGroupProposal,
            MeetingGroup meetingGroup,
            Meeting meeting,
            MeetingAttendee meetingAttendee,
            MeetingLocation meetingLocation,
            Member... members) {

        meetingGroupProposal.setMember(this);
        meetingGroupProposal.setAdministrator(administrator);
        administrator.addMeetingGroupProposals(meetingGroupProposal);

        meetingAttendee.setMeeting(meeting);
        meetingAttendee.addMember(members);
        meeting.setLocation(meetingLocation);
        meeting.addMeetingAttendeeList(meetingAttendee);
        meetingGroup.setMeetingGroupProposal(meetingGroupProposal);
        meetingGroupProposal.setMeetingGroup(meetingGroup);
        meetingGroup.addMeetings(meeting);
        meeting.setMeetingGroup(meetingGroup);

    }

    public void sendMessageMeeting(Meeting meeting, String message) {

        for (MeetingAttendee meetingAttendee : meeting.getMeetingAttendeeList()) {
            if ((meetingAttendee.getMembers().contains(this) ||
                    meeting.getMeetingGroup().getMeetingGroupProposal().getMember() == this) &&
                        meeting.getMeetingGroup().getMeetingGroupProposal().getDecision() ==
                                MeetingGroupProposalDecision.Accept) {

                meeting.addMessage(
                        new Message(this, message)
                );
                break;

            }
        }

    }

    public List<Message> ReadMessageMeeting(Meeting meeting) {

        for (MeetingAttendee meetingAttendee : meeting.getMeetingAttendeeList()) {
            if ((meetingAttendee.getMembers().contains(this) ||
                    meeting.getMeetingGroup().getMeetingGroupProposal().getMember() == this) &&
                    meeting.getMeetingGroup().getMeetingGroupProposal().getDecision() ==
                            MeetingGroupProposalDecision.Accept) {

                 return meeting.getMessages();

            }
        }

        return new ArrayList<Message>();

    }

}
