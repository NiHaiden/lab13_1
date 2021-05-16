package db;


import model.Kurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class KursRepository implements AutoCloseable {
    private static final KursRepository INSTANCE = new KursRepository();

    private KursRepository() {
    }

    protected static KursRepository getInstance() {
        return INSTANCE;
    }


    public Optional<Kurs> getKursById(Integer id) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();
        try {
            return Optional.ofNullable(em.find(Kurs.class, id));
        } finally {
            em.close();
        }
    }

    public List<Kurs> findAll() {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        try {
            return em.createQuery("select k from Kurs k").getResultList();
        } finally {
            em.close();
        }
    }

    public boolean persistKurs(Kurs kurs) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(kurs);
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

    public boolean deleteKurs(Kurs kurs) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            kurs = em.find(Kurs.class, kurs.getKursId());
            em.remove(kurs);
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

    public Kurs updateKurs(Kurs kurs) {
        EntityManager em = JPAUtil.getEMF("jpa-test-unit1").createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (em.find(Kurs.class, kurs.getKursId()) != null) {
                kurs = em.merge(kurs);
            } else {
                kurs = null;
            }
            tx.commit();
            return kurs;
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
