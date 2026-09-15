package ma.projet;

import ma.projet.bean.Profile;
import ma.projet.bean.Utilisateur;
import ma.projet.service.ProfileService;
import ma.projet.service.UserService;

import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        ProfileService serviceProfils = new ProfileService();
        UserService serviceUtilisateurs = new UserService();

        Profile developpement = serviceProfils.create("DEV", "Equipe de developpement");
        Profile qualite = serviceProfils.create("QA", "Controle et qualite");
        Profile gestion = serviceProfils.create("GES", "Gestion des projets");

        serviceUtilisateurs.create("imane", "Imane#2026", developpement);
        Utilisateur mehdi = serviceUtilisateurs.create("mehdi", "Mehdi#2026", qualite);
        serviceUtilisateurs.create("sara", "Sara#2026", developpement);
        serviceUtilisateurs.create("amine", "Amine#2026", gestion);

        System.out.println("=== Profils enregistres ===");
        afficherListe(serviceProfils.findAll());
        System.out.println("\n=== Utilisateurs enregistres ===");
        afficherListe(serviceUtilisateurs.findAll());

        developpement.setDescription("Developpement Java et tests techniques");
        serviceProfils.update(developpement);
        mehdi.setPassword("Nouveau#2026");
        serviceUtilisateurs.update(mehdi);

        System.out.println("\nProfil recherche par ID : " + serviceProfils.findById(developpement.getId()));
        System.out.println("Utilisateur recherche par ID : " + serviceUtilisateurs.findById(mehdi.getId()));

        boolean utilisateurSupprime = serviceUtilisateurs.delete(mehdi.getId());
        boolean profilSupprime = serviceProfils.delete(qualite.getId());
        System.out.println("\nUtilisateur supprime : " + utilisateurSupprime);
        System.out.println("Profil QA supprime : " + profilSupprime);

        System.out.println("\n=== Utilisateurs du profil DEV ===");
        afficherListe(serviceUtilisateurs.findByProfile(developpement));

        System.out.println("\nTotal profils : " + serviceProfils.findAll().size());
        System.out.println("Total utilisateurs : " + serviceUtilisateurs.findAll().size());
    }

    private static void afficherListe(List<?> elements) {
        if (elements.isEmpty()) {
            System.out.println("Aucun resultat");
            return;
        }
        elements.forEach(element -> System.out.println("- " + element));
    }
}
