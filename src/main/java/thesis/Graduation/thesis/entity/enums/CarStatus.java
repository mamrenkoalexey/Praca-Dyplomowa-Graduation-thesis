package thesis.Graduation.thesis.entity.enums;

public enum CarStatus {
    AVAILABLE("Dostępny"),
    RESERVED("Zarezerwowany"),
    SOLD("Sprzedany"),
    IN_SERVICE("W serwisie"),
    UNAVAILABLE("Niedostępny");

    private final String displayName;

    CarStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}