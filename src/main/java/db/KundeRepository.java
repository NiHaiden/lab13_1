package db;

import model.Kunde;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class KundeRepository implements AutoCloseable {
    private static final KundeRepository INSTANCE = new KundeRepository();

    private KundeRepository() {
    }

    protected static KundeRepository getInstance() {
        return INSTANCE;
    }


    public Optional<Kunde> getKundeById(Integer id) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();
        try {
            return Optional.ofNullable(em.find(Kunde.class, id));
        } finally {
            em.close();
        }
    }

    public List<Kunde> findAll() {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        try {
            return em.createQuery("select k from Kunde k").getResultList();
        } finally {
            em.close();
        }
    }

    public boolean persistKunde(Kunde kunde) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(kunde);
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

    public boolean deleteKunde(Kunde kunde) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            kunde = em.find(Kunde.class, kunde.getKundeId());
            em.remove(kunde);
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

    public Kunde updateKunde(Kunde kunde) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (em.find(Kunde.class, kunde.getKundeId()) != null) {
                kunde = em.merge(kunde);
            } else {
                kunde = null;
            }
            tx.commit();
            return kunde;
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
