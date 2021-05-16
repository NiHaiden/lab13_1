package db;

import model.Kurstyp;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class KurstypRepository implements AutoCloseable {
    private static final KurstypRepository INSTANCE = new KurstypRepository();

    private KurstypRepository() {
    }

    protected static KurstypRepository getInstance() {
        return INSTANCE;
    }


    public Optional<Kurstyp> getKurstypById(Character id) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();
        try {
            return Optional.ofNullable(em.find(Kurstyp.class, id));
        } finally {
            em.close();
        }
    }

    public List<Kurstyp> findAll() {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        try {
            return em.createQuery("select k from Kurstyp k").getResultList();
        } finally {
            em.close();
        }
    }

    public boolean persistKurstyp(Kurstyp kurstyp) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(kurstyp);
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

    public boolean deleteKurstyp(Kurstyp kurstyp) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            kurstyp = em.find(Kurstyp.class, kurstyp.getTypId());
            em.remove(kurstyp);
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

    public Kurstyp updateKurstyp(Kurstyp kurstyp) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (em.find(Kurstyp.class, kurstyp.getTypId()) != null) {
                kurstyp = em.merge(kurstyp);
            } else {
                kurstyp = null;
            }
            tx.commit();
            return kurstyp;
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
