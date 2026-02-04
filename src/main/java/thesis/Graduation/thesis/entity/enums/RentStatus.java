package thesis.Graduation.thesis.entity.enums;

public enum RentStatus {
    ACTIVE("Aktywny"),
    COMPLETED("Zakończony"),
    CANCELLED("Anulowany"),
    LATE_RETURN("Spóźniony zwrot");

    private final String displayName;

    RentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
