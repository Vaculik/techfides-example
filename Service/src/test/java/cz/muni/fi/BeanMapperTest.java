package cz.muni.fi;

import cz.muni.fi.config.ServiceApplicationContext;
import cz.muni.fi.dto.SpacemanDTO;
import cz.muni.fi.entity.Spaceman;
import cz.muni.fi.facade.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vaculik on 02/02/2016.
 */
@ContextConfiguration(classes = ServiceApplicationContext.class)
public class BeanMapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMapper beanMapper;

    private SpacemanBuilder spacemanBuilder;

    @BeforeMethod
    public void init() {
        spacemanBuilder = new SpacemanBuilder();
    }

    @Test
    public void singleObjectMap() {
        Spaceman spaceman = spacemanBuilder.build();
        SpacemanDTO spacemanDTO = spacemanBuilder.buildDTO();

        Assert.assertEquals(beanMapper.map(spaceman, SpacemanDTO.class), spacemanDTO);
        Assert.assertEquals(beanMapper.map(spacemanDTO, Spaceman.class), spaceman);
    }

    @Test
    public void collectionMap() {
        List<Spaceman> spacemans = new LinkedList<>();
        List<SpacemanDTO> spacemanDTOs = new LinkedList<>();

        spacemans.add(spacemanBuilder.build());
        spacemanDTOs.add(spacemanBuilder.buildDTO());
        spacemanBuilder.setFirstName("Charlie");
        spacemans.add(spacemanBuilder.build());
        spacemanDTOs.add(spacemanBuilder.buildDTO());

        Assert.assertEquals(beanMapper.map(spacemans, SpacemanDTO.class), spacemanDTOs);
        Assert.assertEquals(beanMapper.map(spacemanDTOs, Spaceman.class), spacemans);
    }
}
