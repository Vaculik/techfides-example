package cz.muni.fi;

import cz.muni.fi.dto.SpacemanDTO;
import cz.muni.fi.entity.Spaceman;
import cz.muni.fi.entity.Specialization;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vaculik on 02/02/2016.
 */
public class SpacemanBuilder {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Specialization specialization;

    public SpacemanBuilder() {
        firstName = "John";
        lastName = "Doe";
        Calendar calendar = Calendar.getInstance();
        calendar.set(1985, Calendar.JANUARY, 1);
        dateOfBirth = calendar.getTime();
        specialization = Specialization.PHYSICS;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date date) {
        dateOfBirth = date;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Spaceman build() {
        Spaceman spaceman = new Spaceman();
        spaceman.setFirstName(firstName);
        spaceman.setLastName(lastName);
        spaceman.setDateOfBirth(dateOfBirth);
        spaceman.setSpecialization(specialization);
        return spaceman;
    }

    public SpacemanDTO buildDTO() {
        SpacemanDTO spaceman = new SpacemanDTO();
        spaceman.setFirstName(firstName);
        spaceman.setLastName(lastName);
        spaceman.setDateOfBirth(dateOfBirth);
        spaceman.setSpecialization(specialization);
        return spaceman;
    }
}
