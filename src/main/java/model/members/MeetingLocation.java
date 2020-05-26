package model.members;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
public class MeetingLocation {

    @Column
    private String localisation;

    public MeetingLocation() {}

    public MeetingLocation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "MeetingLocation{" +
                "localisation='" + localisation + '\'' +
                '}';
    }
}
