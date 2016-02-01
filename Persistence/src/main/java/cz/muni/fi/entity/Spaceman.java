package cz.muni.fi.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Vaculik on 01/02/2016.
 */
@Entity
public class Spaceman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private Specialization specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceman)) return false;

        Spaceman spaceman = (Spaceman) o;

        if (getFirstName() != null ? !getFirstName().equals(spaceman.getFirstName()) : spaceman.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(spaceman.getLastName()) : spaceman.getLastName() != null)
            return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(spaceman.getDateOfBirth()) : spaceman.getDateOfBirth() != null)
            return false;
        return getSpecialization() == spaceman.getSpecialization();

    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getSpecialization() != null ? getSpecialization().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Spaceman{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", specialization=" + specialization +
                '}';
    }
}
