package model.users;

import util.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 40)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(length = 8, nullable = false, unique = true)
    private int number;

    @Column(nullable = false, unique = true)
    private String pseudo;

    // -----------------------------
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles = new ArrayList<UserRole>();

    @Embedded
    private UserRegistration userRegistration;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Message> messages = new ArrayList<Message>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModificationDate;

    // -----------------------------

    public User(){}

    public User(String firstName, String lastName, int number){
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.userRegistration = new UserRegistration(this);
        setPseudo(String.valueOf(number));
    }

    public void setPseudo(String pseudo) {
        Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        this.pseudo = "@" + pattern.matcher(pseudo).replaceAll("");
    }

    public String confirmedUserRegistration(int confirmationSMS) {
        return this.userRegistration.confirmedRegistration(confirmationSMS);
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

    public List<Message> getMessages() {
        return new ArrayList<Message>(messages);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void sendMessage(User to, String message) {
        to.addMessage(
                new Message(this, message)
        );
    }

    public Object readLastUserMessage(User user) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meetup-validate");
        EntityManager em = emf.createEntityManager();
        String jpql = "select msg from Message msg where msg.id = " +
                "(select max(msgs.id) from Message msgs where msgs.author = :user)";
        Query query = em.createQuery(jpql);
        query.setParameter("user", user);

        return query.getSingleResult();

    }

    public List<Message> readAllUserMessages(User user) {
        List<Message> msgs = new ArrayList<Message>();
        for (Message msg : this.messages) {
            if (msg.getAuthor() == user) {
                msgs.add(msg);
            }
        }
        return msgs;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPseudo() {
        return pseudo;
    }

    public List<UserRole> getUserRole() {
        return new ArrayList<UserRole>(this.userRoles);
    }

    public void addUserRole(UserRole userRole){
        this.userRoles.add(userRole);
    }

    public void setUserRole(ArrayList<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserRole> getUserRoles() {
        return new ArrayList<UserRole>(userRoles);
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public UserRegistration getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", number=" + number +
                ", pseudo='" + pseudo + '\'' +
                ", userRoles=" + userRoles +
                ", userRegistrations=" + userRegistration +
                '}';
    }
}
