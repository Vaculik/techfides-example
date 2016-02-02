package cz.muni.fi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cz.muni.fi.dto.SpacemanDTO;
import cz.muni.fi.entity.Specialization;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;

/**
 * Created by Vaculik on 02/02/2016.
 */

@Relation(value = "spaceman", collectionRelation = "spacemen")
@JsonPropertyOrder(value = {"id", "firstName", "lastName", "dateOfBirth", "specialization"})
public class SpacemanResource extends ResourceSupport {

    @JsonProperty("id")
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Specialization specialization;

    public SpacemanResource(SpacemanDTO spacemanDTO) {
        this.id = spacemanDTO.getId();
        this.firstName = spacemanDTO.getFirstName();
        this.lastName = spacemanDTO.getLastName();
        this.dateOfBirth = spacemanDTO.getDateOfBirth();
        this.specialization = spacemanDTO.getSpecialization();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
