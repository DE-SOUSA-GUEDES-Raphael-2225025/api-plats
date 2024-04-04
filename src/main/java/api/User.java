package api;
/**
 * Représente un utilisateur dans le système. Cette classe stocke les informations de base
 * d'un utilisateur, y compris son identifiant de connexion, son nom, son prénom et son mot de passe.
 */
public class User {
    /**
     * L'identifiant de connexion de l'utilisateur.
     */

    private int id;

    private String login;

    /**
     * Le nom de l'utilisateur.
     */
    private String name;

    /**
     * Le prénom de l'utilisateur.
     */
    private String surname;

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String password;

    /**
     * Construit une nouvelle instance de User avec les informations spécifiées.
     *
     * @param login L'identifiant de connexion de l'utilisateur.
     * @param name Le nom de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @param surname Le prénom de l'utilisateur.
     */
    public User(String login, String name, String password, String surname) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }

    /**
     * Retourne l'identifiant de connexion de l'utilisateur.
     *
     * @return L'identifiant de connexion de l'utilisateur.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit l'identifiant de connexion de l'utilisateur.
     *
     * @param login Le nouvel identifiant de connexion.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param name Le nouveau nom de l'utilisateur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param surname Le nouveau prénom de l'utilisateur.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password Le nouveau mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
