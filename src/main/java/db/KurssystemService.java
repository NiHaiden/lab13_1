package db;

import exception.KursDBException;
import model.Dozent;
import model.Kunde;
import model.Kurs;
import model.Kurstyp;

import java.util.List;
import java.util.stream.Collectors;

public class KurssystemService implements IKurssystemService {

    private static KurssystemService INSTANCE = new KurssystemService();

    private KurssystemService() {}

    public static KurssystemService getINSTANCE() {
        return INSTANCE;
    }

    private KursRepository kursRepository = KursRepository.getInstance();
    private KundeRepository kundeRepository = KundeRepository.getInstance();
    private DozentRepository dozentRepository = DozentRepository.getInstance();
    private KurstypRepository kurstypRepository = KurstypRepository.getInstance();

    @Override
    public List<Kunde> getKunden() throws KursDBException {
        return kundeRepository.findAll();
    }

    @Override
    public void insertKunde(Kunde k) throws KursDBException {
        boolean returnValue = kundeRepository.persistKunde(k);

        if (!returnValue) {
            throw new KursDBException("Something went wrong while saving a Kunde!");
        }
    }

    @Override
    public void deleteKunde(Kunde k) throws KursDBException {
        if (!kundeRepository.deleteKunde(k)) {
            throw new KursDBException("Something went wrong while deleting a Kunde!");
        }
    }

    @Override
    public List<Dozent> getDozenten() throws KursDBException {
        return dozentRepository.findAll();
    }

    @Override
    public List<Kurstyp> getKurstypen() throws KursDBException {
        return kurstypRepository.findAll();
    }

    @Override
    public List<Kurs> getKurse() throws KursDBException {
        return kursRepository.findAll();
    }

    @Override
    public void insertKurstyp(Kurstyp kt) throws KursDBException {
        if(!kurstypRepository.persistKurstyp(kt)) {
            throw new KursDBException("Something went wrong whilst trying to persist a Kurstyp!");
        }
    }

    @Override
    public void deleteKurstyp(Kurstyp kt) throws KursDBException {
        if(!kurstypRepository.deleteKurstyp(kt)) {
            throw new KursDBException("Something went wrong whilst trying to delete a Kurstyp!");
        }
    }

    @Override
    public void insertKurs(Kurs kurs) throws KursDBException {
        if (!kursRepository.persistKurs(kurs)) {
            throw new KursDBException("Something went wrong whilst trying to persist a Kurs!");
        }
    }

    @Override
    public List<Kunde> getKundenFromKurs(Kurs kurs) throws KursDBException {
        List<Kunde> kunden = kundeRepository.findAll();

        return kunden.stream().
                filter(kunde -> kunde.getKurse().contains(kurs))
                .collect(Collectors.toList());
    }

    @Override
    public void bucheKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        kunde.getKurse().add(kurs);
        kurs.getKunden().add(kunde);

        if(kundeRepository.updateKunde(kunde) == null) {
            throw new KursDBException("Something went wrong whilst updating the booking for Kunde " + kunde);
        }

        if(kursRepository.updateKurs(kurs) == null) {
            throw new KursDBException("Something went wrong whilst updating the booking for Kurs " + kurs);
        }
    }

    @Override
    public void storniereKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        kunde.getKurse().remove(kurs);
        kurs.getKunden().remove(kunde);

        if(kundeRepository.updateKunde(kunde) == null) {
            throw new KursDBException("Something went wrong whilst updating the booking for Kunde " + kunde);
        }

        if(kursRepository.updateKurs(kurs) == null) {
            throw new KursDBException("Something went wrong whilst updating the booking for Kurs " + kurs);
        }
    }
}
