package thesis.Graduation.thesis.entity.enums;

public enum TaxNumberType {
    PESEL("PESEL"),
    NIP("NIP"),
    VAT("VAT"),
    OTHER("Inne");

    private final String displayName;

    TaxNumberType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
