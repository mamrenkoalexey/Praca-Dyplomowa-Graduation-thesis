package thesis.Graduation.thesis.entity.enums;

public enum FuelType {
    PETROL("Benzyna"),
    DIESEL("Diesel"),
    ELECTRIC("Elektryczny"),
    HYBRID("Hybryda"),
    LPG("LPG");

    private final String displayName;

    FuelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
