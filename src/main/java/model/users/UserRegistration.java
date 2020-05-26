package model.users;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Un registre dépend de l'utilisateur qui le créer
 */
@Embeddable
public class UserRegistration {

    @ManyToOne
    private User user;

    @Column
    private int confirmationKey;

    private Date expiredTime ;

    @Enumerated(value = EnumType.STRING)
    private UserRegistrationStatus status = UserRegistrationStatus.WaitingForConfirmation;

    public UserRegistration(){}

    public UserRegistration(User user) {
        this.user = user;
        this.confirmationKey = generateConfirmationKey();
        this.expiredTime = generateExpiredTime();
    }

    public int generateConfirmationKey() {
        return  Integer.parseInt(String.valueOf( Math.random()*100000 ).split( "[.]" )[0]);
    }

    private Date generateExpiredTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);
        return calendar.getTime();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getConfirmationKey() {
        return confirmationKey;
    }

    public UserRegistrationStatus getStatus() {
        return status;
    }

    public String confirmedRegistration(int confirmedRegistration) {

        if ( this.expiredTime.compareTo( Calendar.getInstance().getTime() ) > 0)
            if ( this.confirmationKey == confirmedRegistration )
                this.status = UserRegistrationStatus.Confirmed;
        else {
            this.status = UserRegistrationStatus.Expired;
            this.expiredTime = generateExpiredTime();
            return "Code incorrect ! Nouveau code générer, veuillez consulter vos messages.";
        }

        return "Code valide !";
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "confirmationKey=" + confirmationKey +
                ", expiredTime=" + expiredTime +
                ", status=" + status +
                '}';
    }
}
