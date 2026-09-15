package ma.projet.bean;

public class Profile implements Identifiable {
    private static int compteur = 0;

    private final int id;
    private String code;
    private String description;

    public Profile(String code, String description) {
        this.code = nettoyerCode(code);
        this.description = nettoyerDescription(description);
        this.id = ++compteur;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = nettoyerCode(code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = nettoyerDescription(description);
    }

    private static String nettoyerCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code du profil est obligatoire");
        }
        return code.trim().toUpperCase();
    }

    private static String nettoyerDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La description est obligatoire");
        }
        return description.trim();
    }

    @Override
    public String toString() {
        return String.format("Profile{id=%d, code='%s', description='%s'}", id, code, description);
    }
}
