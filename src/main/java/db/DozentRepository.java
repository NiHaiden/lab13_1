package db;

import model.Dozent;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class DozentRepository implements AutoCloseable {
    private static final DozentRepository INSTANCE = new DozentRepository();

    private DozentRepository() {
    }

    protected static DozentRepository getInstance() {
        return INSTANCE;
    }


    public Optional<Dozent> getDozentById(Integer id) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();
        try {
            return Optional.ofNullable(em.find(Dozent.class, id));
        } finally {
            em.close();
        }
    }

    public List<Dozent> findAll() {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        try {
            return em.createQuery("select d from Dozent d").getResultList();
        } finally {
            em.close();
        }
    }

    public boolean persistDozent(Dozent dozent) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(dozent);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteDozent(Dozent dozent) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            dozent = em.find(Dozent.class, dozent.getDozId());
            em.remove(dozent);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Dozent updateDozent(Dozent dozent) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (em.find(Dozent.class, dozent.getDozId()) != null) {
                dozent = em.merge(dozent);
            } else {
                dozent = null;
            }
            tx.commit();
            return dozent;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void close() throws Exception {
        JPAUtil.close();
    }
}
