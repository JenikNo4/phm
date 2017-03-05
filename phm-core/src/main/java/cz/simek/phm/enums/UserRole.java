package cz.simek.phm.enums;

/**
 * Created by jenik on 3.3.17.
 */
public enum UserRole {
    REGISTERED,
    AUTHENTICATED, //je proces určující skutečnou identitu uživatele. Jinými slovy je to přihlášení/odhlášení.
    REPORTER,
    ADMIN;
}
