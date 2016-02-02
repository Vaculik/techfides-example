package cz.muni.fi;

import cz.muni.fi.dao.SpacemanDao;
import cz.muni.fi.entity.Spaceman;
import cz.muni.fi.entity.Specialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vaculik on 02/02/2016.
 */
@Component
public class InitialDataImpl implements InitialData {

    private static final Logger logger = LoggerFactory.getLogger(InitialDataImpl.class);

    private SpacemanLoader spacemanLoader = new SpacemanLoader();

    @Autowired
    private SpacemanDao spacemanDao;


    @Override
    public void loadData() {
        logger.debug("Loading initial data.");

        spacemanLoader
                .setFirstName("Matt")
                .setLastName("Damon")
                .setDateOfBirth(1970, Calendar.OCTOBER, 8)
                .setSpecialization(Specialization.BIOLOGY)
                .load();
        spacemanLoader
                .setFirstName("Matthew")
                .setLastName("McConaughey")
                .setDateOfBirth(1969, Calendar.NOVEMBER, 4)
                .setSpecialization(Specialization.PHYSICS)
                .load();
        spacemanLoader
                .setFirstName("Sandra")
                .setLastName("Bullock")
                .setDateOfBirth(1964, Calendar.JULY, 26)
                .setSpecialization(Specialization.MECHANICS)
                .load();
    }



    private class SpacemanLoader {
        String firstName;
        String lastName;
        Date dateOfBrith;
        Specialization specialization;

        Calendar calendar = Calendar.getInstance();

        public SpacemanLoader setFirstName(String name) {
            this.firstName = name;
            return this;
        }

        public SpacemanLoader setLastName(String name) {
            this.lastName = name;
            return this;
        }

        public SpacemanLoader setDateOfBirth(int year, int month, int day) {
            calendar.set(year, month, day);
            this.dateOfBrith = calendar.getTime();
            return this;
        }

        public SpacemanLoader setSpecialization(Specialization specialization) {
            this.specialization = specialization;
            return this;
        }

        public void load() {
            Spaceman spaceman = new Spaceman();
            spaceman.setFirstName(firstName);
            spaceman.setLastName(lastName);
            spaceman.setDateOfBirth(dateOfBrith);
            spaceman.setSpecialization(specialization);
            spacemanDao.create(spaceman);
        }
    }
}
