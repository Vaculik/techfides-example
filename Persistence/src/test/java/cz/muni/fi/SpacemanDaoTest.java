package cz.muni.fi;

import cz.muni.fi.config.SpringApplicationContext;
import cz.muni.fi.dao.SpacemanDao;
import cz.muni.fi.entity.Spaceman;
import cz.muni.fi.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Vaculik on 01/02/2016.
 */
@ContextConfiguration(classes = SpringApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SpacemanDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SpacemanDao spacemanDao;

    private SpacemanBuilder spacemanBuilder;

    @BeforeMethod
    public void init() {
        spacemanBuilder = new SpacemanBuilder();
    }

    @Test
    public void getById() {
        Spaceman spaceman = spacemanBuilder.build();
        spacemanDao.create(spaceman);
        Spaceman result = spacemanDao.getById(spaceman.getId());

        Assert.assertEquals(result, spaceman);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void getByIdNullId() {
        spacemanDao.getById(null);
    }

    @Test
    public void getAll() {
        Spaceman spacemanJohn = spacemanBuilder.build();
        spacemanBuilder.setFirstName("Charlie");
        Spaceman spacemanCharlie = spacemanBuilder.build();

        spacemanDao.create(spacemanJohn);
        spacemanDao.create(spacemanCharlie);
        List<Spaceman> results = spacemanDao.getAll();

        Assert.assertEquals(results.size(), 2);
        Assert.assertTrue(results.contains(spacemanJohn));
        Assert.assertTrue(results.contains(spacemanCharlie));
    }

    @Test
    public void getAllNoResult() {
        Assert.assertEquals(spacemanDao.getAll().size(), 0);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createNull() {
        spacemanDao.create(null);
    }

    @Test
    public void update() {
        Spaceman spaceman = spacemanBuilder.build();
        spacemanDao.create(spaceman);
        spaceman.setFirstName("Charlie");
        spacemanDao.update(spaceman);
        Spaceman result = spacemanDao.getById(spaceman.getId());

        Assert.assertEquals(result, spaceman);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateNull() {
        spacemanDao.update(null);
    }

    @Test
    public void remove() {
        Spaceman spacemanJohn = spacemanBuilder.build();
        spacemanBuilder.setFirstName("Charlie");
        Spaceman spacemanCharlie = spacemanBuilder.build();

        spacemanDao.create(spacemanJohn);
        spacemanDao.create(spacemanCharlie);
        spacemanDao.delete(spacemanJohn);
        List<Spaceman> result = spacemanDao.getAll();

        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(spacemanCharlie));
        Assert.assertFalse(result.contains(spacemanJohn));
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void removeNullTest() {
        spacemanDao.delete(null);
    }

    private class SpacemanBuilder {
        String firstName;
        String lastName;
        Date dateOfBirth;
        Specialization specialization;

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
    }
}
