package ad251.bezshyiko.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CalculationInput {

    private final double amount;
    private final double rate;
    private final double term;
    private final double extraValue;
    private final List<Double> series;

    private CalculationInput(Builder builder) {
        this.amount = builder.amount;
        this.rate = builder.rate;
        this.term = builder.term;
        this.extraValue = builder.extraValue;
        this.series = Collections.unmodifiableList(new ArrayList<>(builder.series));
    }

    public static Builder builder() {
        return new Builder();
    }

    public double getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public double getTerm() {
        return term;
    }

    public double getExtraValue() {
        return extraValue;
    }

    public List<Double> getSeries() {
        return series;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CalculationInput other = (CalculationInput) obj;
        return Double.compare(amount, other.amount) == 0
                && Double.compare(rate, other.rate) == 0
                && Double.compare(term, other.term) == 0
                && Double.compare(extraValue, other.extraValue) == 0
                && series.equals(other.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, rate, term, extraValue, series);
    }

    @Override
    public String toString() {
        return "CalculationInput{amount=" + amount
                + ", rate=" + rate
                + ", term=" + term
                + ", extraValue=" + extraValue
                + ", series=" + series + '}';
    }

    public static class Builder {

        private double amount;
        private double rate;
        private double term;
        private double extraValue;
        private List<Double> series = Collections.emptyList();

        private Builder() {
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder rate(double rate) {
            this.rate = rate;
            return this;
        }

        public Builder term(double term) {
            this.term = term;
            return this;
        }

        public Builder extraValue(double extraValue) {
            this.extraValue = extraValue;
            return this;
        }

        public Builder series(List<Double> series) {
            this.series = Objects.requireNonNull(series, "series");
            return this;
        }

        public CalculationInput build() {
            return new CalculationInput(this);
        }
    }
}
