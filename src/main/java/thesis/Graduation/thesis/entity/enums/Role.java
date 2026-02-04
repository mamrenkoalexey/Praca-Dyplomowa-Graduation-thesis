package thesis.Graduation.thesis.entity.enums;

public enum Role {
    MANAGER("Menedżer"),
    DIRECTOR("Dyrektor"),
    SELLER("Sprzedawca"),
    CLIENT("Klient");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
