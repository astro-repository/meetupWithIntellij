import model.administrators.Administrator;
import model.administrators.MeetingGroupProposal;
import model.members.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-create");
        EntityManager em = emf.createEntityManager();
////
        Member cedrick = new Member("Touré", "cedrick", 5908917);
        Administrator amani = new Administrator("Amani", "christian", 52575131);
        Member christian = new Member("Touré", "christian", 4620007);
        Member frederic = new Member("Boye", "frederic", 4620407);
        Member test = new Member("dfgdfg", "frederic", 564564);

//        MeetingLocation meetingLocation = new MeetingLocation("Abidjan");
//        MeetingAttendee meetingAttendee = new MeetingAttendee();
//        Meeting meeting = new Meeting();
//        MeetingGroup meetingGroup = new MeetingGroup();
//        MeetingGroupProposal meetingGroupProposal = new MeetingGroupProposal();
//        System.out.println(meetingAttendee);
//        System.out.println(meeting);
//        System.out.println(meetingGroup);
//        System.out.println(meetingGroupProposal);

//        meetingAttendee.setMeeting(meeting);
//        meetingAttendee.addMember(christian, frederic);
//        meeting.setLocation(meetingLocation);
//        meeting.addMeetingAttendeeList(meetingAttendee);
//        meetingGroup.setMeetingGroupProposal(meetingGroupProposal);
//        meetingGroupProposal.setMeetingGroup(meetingGroup);
//        meetingGroup.addMeetings(meeting);
//        meeting.setMeetingGroup(meetingGroup);

//        cedrick.proposeMeetingGroup(amani, meetingGroupProposal);

        cedrick.proposeMeetingGroup(
                amani,
                new MeetingGroupProposal(),
                new MeetingGroup(),
                new Meeting(),
                new MeetingAttendee(),
                new MeetingLocation("Abidjan"),
                christian, frederic
        );

//        System.out.println(cedrick);

        em.getTransaction().begin();
//////
        em.persist(cedrick);
        em.persist(amani);
        em.persist(christian);
        em.persist(frederic);
        em.persist(test);

//        Member frederic = em.find(Member.class, 8L);
//        Member cedrick = em.find(Member.class, 1L);
//        Member test = em.find(Member.class, 17L);
//        Administrator amani = em.find(Administrator.class, 2L);

        cedrick.sendMessage(frederic, "Message utilisateur 1");
        cedrick.sendMessage(frederic, "Message utilisateur 2");
        cedrick.sendMessage(frederic, "Message utilisateur 3");
        cedrick.sendMessage(frederic, "Message utilisateur 4");
//
        frederic.sendMessage(cedrick, "reponse utilisateur 1");
        frederic.sendMessage(cedrick, "reponse utilisateur 2");
        frederic.sendMessage(cedrick, "reponse utilisateur 3");
        frederic.sendMessage(cedrick, "reponse utilisateur 4");
//        System.out.println(em.find(Meeting.class, 5L));

        amani.validateAllMeetingProposals(em.find(MeetingGroupProposal.class, 3L));

        cedrick.sendMessageMeeting(em.find(Meeting.class, 5L), "Message to meeting cedrick");
        test.sendMessageMeeting(em.find(Meeting.class, 5L), "Message to meeting cedrick");

//        em.persist(meetingAttendee);
//        em.persist(meeting);
//        em.persist(meetingGroup);
//        em.persist(meetingGroupProposal);

//        em.persist(cedrick);
//        em.persist(frederic);

        em.getTransaction().commit();

//        System.out.println(cedrick);
//        System.out.println(amani);
//        System.out.println(christian);
//        System.out.println(frederic);
//        System.out.println(meetingAttendee);
//        System.out.println(meeting);
//        System.out.println(meetingGroup);
//        System.out.println(meetingGroupProposal);
//        System.out.println(meetingLocation);

        Administrator.showProposals();
        System.out.println(cedrick.readAllUserMessages(frederic));
        System.out.println(cedrick.readLastUserMessage(frederic));

//        em.getTransaction().begin();
//        em.persist(amani);
//        em.persist(cedrick);
//        em.persist(christian);
//        em.getTransaction().commit();
//        cedrick.test();
//        System.out.println(amani);
//        System.out.println(christian);
        em.close();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
//        EntityManager em = emf.createEntityManager();
//
//        Member toure = em.find(Member.class, (long)1);
//        toure.setPseudo("@astroTest541@kjh");
//        System.out.println(toure);
    }

}
