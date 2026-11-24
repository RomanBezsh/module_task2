package ad251.bezshyiko.model;

import java.util.Objects;

public class ResultRecord implements Comparable<ResultRecord> {

    private final int number;
    private final CalculationType type;
    private final String amount;
    private final String rate;
    private final String term;
    private final String extra;
    private final double resultValue;
    private final String resultText;

    private ResultRecord(Builder builder) {
        this.number = builder.number;
        this.type = Objects.requireNonNull(builder.type, "type");
        this.amount = Objects.requireNonNull(builder.amount, "amount");
        this.rate = Objects.requireNonNull(builder.rate, "rate");
        this.term = Objects.requireNonNull(builder.term, "term");
        this.extra = Objects.requireNonNull(builder.extra, "extra");
        this.resultValue = builder.resultValue;
        this.resultText = Objects.requireNonNull(builder.resultText, "resultText");
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getNumber() {
        return number;
    }

    public CalculationType getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getRate() {
        return rate;
    }

    public String getTerm() {
        return term;
    }

    public String getExtra() {
        return extra;
    }

    public double getResultValue() {
        return resultValue;
    }

    public String getResultText() {
        return resultText;
    }

    @Override
    public int compareTo(ResultRecord other) {
        return Integer.compare(number, other.number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResultRecord other = (ResultRecord) obj;
        return number == other.number
                && Double.compare(resultValue, other.resultValue) == 0
                && type == other.type
                && amount.equals(other.amount)
                && rate.equals(other.rate)
                && term.equals(other.term)
                && extra.equals(other.extra)
                && resultText.equals(other.resultText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, amount, rate, term, extra, resultValue, resultText);
    }

    @Override
    public String toString() {
        return "ResultRecord{number=" + number
                + ", type=" + type.name()
                + ", resultValue=" + resultValue
                + ", resultText='" + resultText + "'}";
    }

    public static class Builder {

        private int number;
        private CalculationType type;
        private String amount;
        private String rate;
        private String term;
        private String extra;
        private double resultValue;
        private String resultText;

        private Builder() {
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder type(CalculationType type) {
            this.type = type;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder rate(String rate) {
            this.rate = rate;
            return this;
        }

        public Builder term(String term) {
            this.term = term;
            return this;
        }

        public Builder extra(String extra) {
            this.extra = extra;
            return this;
        }

        public Builder resultValue(double resultValue) {
            this.resultValue = resultValue;
            return this;
        }

        public Builder resultText(String resultText) {
            this.resultText = resultText;
            return this;
        }

        public ResultRecord build() {
            return new ResultRecord(this);
        }
    }
}
