package thesis.Graduation.thesis.entity.enums;

public enum LeaseStatus {
    ACTIVE("Aktywny"),
    PENDING("Oczekujący"),
    COMPLETED("Zakończony"),
    TERMINATED("Rozwiązany");

    private final String displayName;

    LeaseStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
