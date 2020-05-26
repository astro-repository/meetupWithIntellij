package model.administrators;

import model.users.User;

import javax.persistence.*;
import java.util.*;

@Entity
public class Administrator extends User {

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<MeetingGroupProposal> meetingGroupProposals = new HashSet<MeetingGroupProposal>();

    public Administrator(){}

    public Administrator(String firstName, String lastName, int number) {
        super(firstName, lastName, number);
    }

    public Set<MeetingGroupProposal> getMeetingGroupProposals() {
        return meetingGroupProposals;
    }

    public void setMeetingGroupProposals(MeetingGroupProposal meetingGroupProposals) {
        this.meetingGroupProposals.add(meetingGroupProposals);
    }

    public void setMeetingGroupProposals(Set<MeetingGroupProposal> meetingGroupProposals) {
        this.meetingGroupProposals = meetingGroupProposals;
    }

    public void addMeetingGroupProposals(MeetingGroupProposal meetingGroupProposals) {
        this.meetingGroupProposals.add(meetingGroupProposals);
    }

    public void addMeetingGroupProposals(MeetingGroupProposal... meetingGroupProposals) {
        this.meetingGroupProposals.addAll(Arrays.asList(meetingGroupProposals));
    }

    public static void showProposals() {
        String jpql = "select mgp from MeetingGroupProposal mgp where mgp.status = 'InVerification'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(jpql);
        List meetingGroupProposals = query.getResultList();
        System.out.println( query.getResultList() );
    }

    public void validateMeetingProposal(MeetingGroupProposal meetingGroupProposal) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
        EntityManager em = emf.createEntityManager();
        meetingGroupProposal.setStatus(MeetingGroupProposalStatus.Accepted);
        meetingGroupProposal.setDecision(MeetingGroupProposalDecision.Accept);
        em.persist(meetingGroupProposal);
    }

    public void validateAllMeetingProposals(MeetingGroupProposal... meetingGroupProposals) {
        Arrays.stream(meetingGroupProposals).forEach(mgp-> {
            mgp.setStatus(MeetingGroupProposalStatus.Accepted);
            mgp.setDecision(MeetingGroupProposalDecision.Accept);
        });
    }

    public void rejectedMeetingProposal(MeetingGroupProposal meetingGroupProposal) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
        EntityManager em = emf.createEntityManager();
        meetingGroupProposal.setStatus(MeetingGroupProposalStatus.Rejected);
        meetingGroupProposal.setDecision(MeetingGroupProposalDecision.Reject);
        em.persist(meetingGroupProposal);
    }

    public void rejectedAllMeetingProposals(MeetingGroupProposal... meetingGroupProposals) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
        EntityManager em = emf.createEntityManager();
        Arrays.stream(meetingGroupProposals).forEach(mgp-> {
            mgp.setStatus(MeetingGroupProposalStatus.Rejected);
            mgp.setDecision(MeetingGroupProposalDecision.Reject);
            em.persist(mgp);
        });
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "meetingGroupProposals=" + meetingGroupProposals +
                '}';
    }
}
