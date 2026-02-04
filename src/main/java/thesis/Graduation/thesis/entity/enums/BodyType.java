package thesis.Graduation.thesis.entity.enums;

public enum BodyType {
    CROSSOVER("Crossover"),
    SEDAN("Sedan"),
    HATCHBACK("Hatchback"),
    SUV("SUV"),
    COUPE("Coupe"),
    CONVERTIBLE("Kabriolet"),
    PICKUP("Pickup"),
    WAGON("Kombi"),
    VAN("Van");

    private final String displayName;

    BodyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
