package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationType;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class CalculatorRegistry {

    private final Map<CalculationType, CalculatorFactory> factories = new EnumMap<>(CalculationType.class);

    public CalculatorRegistry() {
        register(CalculationType.SIMPLE_INTEREST, SimpleInterestCalculator::new);
        register(CalculationType.COMPOUND_INTEREST, CompoundInterestCalculator::new);
        register(CalculationType.ANNUITY_PAYMENT, AnnuityCalculator::new);
        register(CalculationType.ROI, RoiCalculator::new);
        register(CalculationType.RISK, RiskCalculator::new);
        register(CalculationType.PAYBACK_PERIOD, PaybackCalculator::new);
    }

    public void register(CalculationType type, CalculatorFactory factory) {
        factories.put(Objects.requireNonNull(type, "type"), Objects.requireNonNull(factory, "factory"));
    }

    public Calculator get(CalculationType type) {
        CalculatorFactory factory = factories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Невідомий тип розрахунку: " + type);
        }
        return factory.create();
    }

    public Collection<CalculatorFactory> getFactories() {
        return Collections.unmodifiableCollection(factories.values());
    }
}
