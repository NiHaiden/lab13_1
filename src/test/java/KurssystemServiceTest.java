import db.JPAUtil;
import db.KurssystemService;
import exception.KursDBException;
import model.Kunde;
import model.Kurs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KurssystemServiceTest {

    private KurssystemService kurssystemService;

    @BeforeEach
    void setUp() {
        kurssystemService = KurssystemService.getINSTANCE();
    }


    void insertTestData() {}

    @Test
    void insertKunde() throws KursDBException {
        Kunde k = new Kunde("Haiden", "Niklas");

        kurssystemService.insertKunde(k);

        assertEquals(7, kurssystemService.getKunden().size());

        //Cleanup because of Postgres
        kurssystemService.deleteKunde(k);
    }

    @Test
    void deleteKunde() throws KursDBException {
        Kunde k = new Kunde("Haiden", "Niklas");
        kurssystemService.insertKunde(k);
        assertEquals(7, kurssystemService.getKunden().size());

        kurssystemService.deleteKunde(k);
        assertEquals(6, kurssystemService.getKunden().size());
    }

    @Test
    void deleteKundeNotExist() {
        Kunde k = new Kunde("Haiden", "Niklas");
        assertThrows(KursDBException.class, () -> {
           kurssystemService.deleteKunde(k);
        });
    }

    @Test
    void insertKursSuccessful() throws KursDBException {
        Kurs k = new Kurs(kurssystemService.getKurstypen().get(0), "Java für Anfänger", LocalDate.now());
        k.setDozent(kurssystemService.getDozenten().get(0));
        kurssystemService.insertKurs(k);

        assertEquals(7, kurssystemService.getKurse().size());
    }
    @Test
    void insertKursAlreadyExists() {
        assertThrows(KursDBException.class, () -> {
            kurssystemService.insertKurs(kurssystemService.getKurse().get(0));
        });
    }

    @Test
    void insertKundeAlreadyExists() {
        assertThrows(KursDBException.class, () -> {
                kurssystemService.insertKunde(kurssystemService.getKunden().get(0));
        });
    }


    @Test
    void testKursBuchenUndStornieren() throws KursDBException {
        Kunde k = new Kunde("Haiden", "Niklas");
        kurssystemService.insertKunde(k);
        System.out.println(kurssystemService.getKurse());
        Kurs kurs = kurssystemService.getKurse().get(0);
        kurssystemService.bucheKurs(k, kurs);

        assertEquals(1, k.getKurse().size());

        //System.out.println(kurs.getKunden());
        assertEquals(2, kurs.getKunden().size());

        kurssystemService.storniereKurs(k, kurs);


        assertEquals(0, k.getKurse().size());
        assertEquals(1, kurs.getKunden().size());
    }

    @AfterEach
    void tearDown() {
        JPAUtil.close();
    }

}