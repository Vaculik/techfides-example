package cz.muni.fi.facade;

import cz.muni.fi.dao.SpacemanDao;
import cz.muni.fi.dto.SpacemanDTO;
import cz.muni.fi.entity.Spaceman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vaculik on 02/02/2016.
 */
@Service
@Transactional
public class SpacemanFacadeImpl implements SpacemanFacade {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SpacemanDao spacemanDao;


    @Override
    public SpacemanDTO getById(Long id) {
        return beanMapper.map(spacemanDao.getById(id), SpacemanDTO.class);
    }

    @Override
    public List<SpacemanDTO> getAll() {
        return beanMapper.map(spacemanDao.getAll(), SpacemanDTO.class);
    }

    @Override
    public void create(SpacemanDTO spacemanDTO) {
        spacemanDao.create(beanMapper.map(spacemanDTO, Spaceman.class));
    }

    @Override
    public void update(SpacemanDTO spacemanDTO) {
        spacemanDao.update(beanMapper.map(spacemanDTO, Spaceman.class));
    }

    @Override
    public void delete(SpacemanDTO spacemanDTO) {
        spacemanDao.delete(beanMapper.map(spacemanDTO, Spaceman.class));
    }
}
