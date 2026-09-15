package ma.projet.service;

import ma.projet.bean.Profile;
import ma.projet.bean.Utilisateur;
import ma.projet.dao.Dao;
import ma.projet.dao.ListDao;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final Dao<Utilisateur> dao;

    public UserService() {
        this(new ListDao<Utilisateur>());
    }

    public UserService(Dao<Utilisateur> dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Le DAO des utilisateurs est obligatoire");
        }
        this.dao = dao;
    }

    public Utilisateur create(String login, String pwd, Profile profile) {
        String loginNettoye = login == null ? "" : login.trim();
        for (Utilisateur utilisateur : dao.findAll()) {
            if (utilisateur.getLogin().equalsIgnoreCase(loginNettoye)) {
                throw new IllegalArgumentException("Ce login existe deja");
            }
        }
        Utilisateur nouvelUtilisateur = new Utilisateur(loginNettoye, pwd, profile);
        dao.create(nouvelUtilisateur);
        return nouvelUtilisateur;
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return dao.update(utilisateur);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public Utilisateur findById(int id) {
        return dao.findById(id);
    }

    public List<Utilisateur> findAll() {
        return dao.findAll();
    }

    public List<Utilisateur> findByProfile(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Le profil est obligatoire");
        }
        return dao.findAll().stream()
                .filter(utilisateur -> utilisateur.getProfile().getId() == profile.getId())
                .collect(Collectors.toList());
    }
}
