package cz.muni.fi.dao;

import cz.muni.fi.entity.Spaceman;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Vaculik on 01/02/2016.
 */
@Repository
@Transactional
public class SpacemanDaoImpl implements SpacemanDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Spaceman getById(Long id) {
        return em.find(Spaceman.class, id);
    }

    @Override
    public List<Spaceman> getAll() {
        TypedQuery<Spaceman> query = em.createQuery("SELECT sp FROM Spaceman AS sp", Spaceman.class);
        return query.getResultList();
    }

    @Override
    public void create(Spaceman spaceman) {
        em.persist(spaceman);
    }

    @Override
    public void update(Spaceman spaceman) {
        em.merge(spaceman);
    }

    @Override
    public void delete(Spaceman spaceman) {
        em.remove(em.contains(spaceman) ? spaceman : em.merge(spaceman));
    }
}
