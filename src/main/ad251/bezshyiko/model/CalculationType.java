package ad251.bezshyiko.model;

public enum CalculationType {
    SIMPLE_INTEREST("Прості відсотки"),
    COMPOUND_INTEREST("Складні відсотки"),
    ANNUITY_PAYMENT("Ануїтетний платіж"),
    ROI("ROI (рентабельність)"),
    RISK("Ризик (середньоквадратичне відхилення)"),
    PAYBACK_PERIOD("Термін окупності");

    private final String displayName;

    CalculationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
