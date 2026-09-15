package ma.projet.service;

import ma.projet.bean.Profile;
import ma.projet.dao.Dao;
import ma.projet.dao.ListDao;

import java.util.List;

public class ProfileService {
    private final Dao<Profile> dao;

    public ProfileService() {
        this(new ListDao<Profile>());
    }

    public ProfileService(Dao<Profile> dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Le DAO des profils est obligatoire");
        }
        this.dao = dao;
    }

    public Profile create(String code, String desc) {
        String codeNettoye = code == null ? "" : code.trim();
        for (Profile profil : dao.findAll()) {
            if (profil.getCode().equalsIgnoreCase(codeNettoye)) {
                throw new IllegalArgumentException("Ce code de profil existe deja");
            }
        }
        Profile nouveauProfil = new Profile(codeNettoye, desc);
        dao.create(nouveauProfil);
        return nouveauProfil;
    }

    public Profile update(Profile profile) {
        return dao.update(profile);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public Profile findById(int id) {
        return dao.findById(id);
    }

    public List<Profile> findAll() {
        return dao.findAll();
    }
}
