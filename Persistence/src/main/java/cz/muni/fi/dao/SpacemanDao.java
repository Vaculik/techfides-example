package cz.muni.fi.dao;

import cz.muni.fi.entity.Spaceman;

import java.util.List;

/**
 * Created by Vaculik on 01/02/2016.
 */
public interface SpacemanDao {

    Spaceman getById(Long id);

    List<Spaceman> getAll();

    void create(Spaceman spaceman);

    void update(Spaceman spaceman);

    void delete(Spaceman spaceman);
}
