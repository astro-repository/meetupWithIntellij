package model.members;

import model.administrators.Administrator;
import model.administrators.MeetingGroupProposal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MemberTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
    EntityManager em = emf.createEntityManager();

    Member cedrickToure          = new Member("Touré", "Cédrick", 5908917);
    Administrator christianAmani = new Administrator("Amani", "Christian", 52575131);
    Member christianToure        = new Member("Touré", "Christian", 4620007);
    Member fredericBoye          = new Member("Boye", "frederic", 4620407);
    Member autreMember           = new Member("Member", "Autre", 564564);

    private long idMeeting;

    @Before
    public void setUp() throws Exception {


        cedrickToure.proposeMeetingGroup(
            christianAmani,
            new MeetingGroupProposal(),
            new MeetingGroup(),
            new Meeting(),
            new MeetingAttendee(),
            new MeetingLocation("Abidjan"),
            christianToure, fredericBoye
        );

        cedrickToure.sendMessage(fredericBoye, "Message utilisateur 1");
        cedrickToure.sendMessage(fredericBoye, "Message utilisateur 2");
        cedrickToure.sendMessage(fredericBoye, "Message utilisateur 3");
        cedrickToure.sendMessage(fredericBoye, "Message utilisateur 4");

        fredericBoye.sendMessage(cedrickToure, "reponse utilisateur 1");
        fredericBoye.sendMessage(cedrickToure, "reponse utilisateur 2");
        fredericBoye.sendMessage(cedrickToure, "reponse utilisateur 3");
        fredericBoye.sendMessage(cedrickToure, "reponse utilisateur 4");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getListAttendee() {
        assertTrue(cedrickToure.getListAttendee() instanceof MeetingAttendee);
    }

    @Test
    public void getProposals() {
        assertTrue(cedrickToure.getProposals() instanceof MeetingGroupProposal);
    }

    @Test
    public void readAllUserMessages() {
        assertSame(
                new ArrayList<>(),
                cedrickToure.readAllUserMessages(fredericBoye)

        );
    }
}