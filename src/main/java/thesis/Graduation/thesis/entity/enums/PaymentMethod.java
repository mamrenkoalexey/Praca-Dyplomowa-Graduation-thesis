package thesis.Graduation.thesis.entity.enums;

public enum PaymentMethod {
    CASH("Gotówka"),
    TRANSFER("Przelew");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
