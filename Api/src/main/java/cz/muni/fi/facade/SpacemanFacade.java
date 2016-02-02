package cz.muni.fi.facade;

import cz.muni.fi.dto.SpacemanDTO;

import java.util.List;

/**
 * Created by Vaculik on 02/02/2016.
 */
public interface SpacemanFacade {

    SpacemanDTO getById(Long id);

    List<SpacemanDTO> getAll();

    void create(SpacemanDTO spacemanDTO);

    void update(SpacemanDTO spacemanDTO);

    void delete(SpacemanDTO spacemanDTO);
}
