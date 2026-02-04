package thesis.Graduation.thesis.entity.enums;

public enum PaymentStatus {
    PENDING("Oczekująca"),
    COMPLETED("Zakończona"),
    CANCELLED("Anulowana"),
    FAILED("Nieudana");

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
