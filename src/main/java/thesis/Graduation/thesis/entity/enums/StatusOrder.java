package thesis.Graduation.thesis.entity.enums;

public enum StatusOrder {
    NEW("Nowe"),
    CONFIRMED("Potwierdzone"),
    CANCEL("Anulowane");

    private final String displayName;

    StatusOrder(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
