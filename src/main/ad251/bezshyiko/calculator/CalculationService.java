package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.CalculationException;
import ad251.bezshyiko.model.CalculationInput;
import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ResultRecord;
import ad251.bezshyiko.util.FormatUtils;
import ad251.bezshyiko.util.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculationService {

    private final CalculatorRegistry registry;
    private final Repository<ResultRecord> records = new Repository<>();
    private final List<CalculationListener> listeners = new ArrayList<>();
    private int nextNumber = 1;

    public CalculationService() {
        this(new CalculatorRegistry());
    }

    public CalculationService(CalculatorRegistry registry) {
        this.registry = registry;
    }

    public static CalculationService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder {
        private static final CalculationService INSTANCE = new CalculationService();
    }

    public void addListener(CalculationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CalculationListener listener) {
        listeners.remove(listener);
    }

    public Calculator getCalculator(CalculationType type) {
        return registry.get(type);
    }

    public ResultRecord calculateAndAdd(CalculationType type, CalculationInput input, String amountText,
                                        String rateText, String termText, String extraText)
            throws CalculationException {
        Calculator calculator = registry.get(type);
        double result = calculator.calculate(input);
        if (!Double.isFinite(result)) {
            throw new CalculationException("Результат розрахунку не є скінченним числом.");
        }
        ResultRecord record = ResultRecord.builder()
                .number(nextNumber++)
                .type(type)
                .amount(amountText)
                .rate(rateText)
                .term(termText)
                .extra(extraText)
                .resultValue(result)
                .resultText(FormatUtils.format(result) + " " + calculator.getResultUnit())
                .build();
        records.add(record);
        notifyListeners();
        return record;
    }

    public List<ResultRecord> getRecords() {
        return records.getAll();
    }

    public Set<CalculationType> getUsedTypes() {
        Set<CalculationType> types = EnumSet.noneOf(CalculationType.class);
        for (ResultRecord record : records.getAll()) {
            types.add(record.getType());
        }
        return types;
    }

    public Map<CalculationType, List<ResultRecord>> groupByType() {
        Map<CalculationType, List<ResultRecord>> groups = new EnumMap<>(CalculationType.class);
        for (ResultRecord record : records.getAll()) {
            groups.computeIfAbsent(record.getType(), key -> new ArrayList<>()).add(record);
        }
        return groups;
    }

    public List<ResultRecord> getRecordsSortedByResultDescending() {
        List<ResultRecord> sorted = new ArrayList<>(records.getAll());
        sorted.sort(Comparator.comparingDouble(ResultRecord::getResultValue)
                .reversed()
                .thenComparingInt(ResultRecord::getNumber));
        return List.copyOf(sorted);
    }

    public int removeRecordsBelow(double threshold) {
        int removed = records.removeIf(record -> record.getResultValue() < threshold);
        if (removed > 0) {
            notifyListeners();
        }
        return removed;
    }

    public void clear() {
        records.clear();
        nextNumber = 1;
        notifyListeners();
    }

    private void notifyListeners() {
        List<ResultRecord> snapshot = getRecords();
        for (CalculationListener listener : new ArrayList<>(listeners)) {
            listener.onRecordsChanged(snapshot);
        }
    }
}
