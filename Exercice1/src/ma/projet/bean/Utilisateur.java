package ma.projet.bean;

public class Utilisateur implements Identifiable {
    private static int compteur = 0;

    private final int id;
    private String login;
    private String password;
    private Profile profile;

    public Utilisateur(String login, String password, Profile profile) {
        this.login = nettoyerLogin(login);
        this.password = verifierPassword(password);
        this.profile = verifierProfile(profile);
        this.id = ++compteur;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = nettoyerLogin(login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = verifierPassword(password);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = verifierProfile(profile);
    }

    private static String nettoyerLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Le login est obligatoire");
        }
        return login.trim().toLowerCase();
    }

    private static String verifierPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 4 caracteres");
        }
        return password;
    }

    private static Profile verifierProfile(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Le profil est obligatoire");
        }
        return profile;
    }

    @Override
    public String toString() {
        return String.format(
                "Utilisateur{id=%d, login='%s', profil='%s'}",
                id,
                login,
                profile.getCode()
        );
    }
}
