package ad251.bezshyiko.calculator;

import ad251.bezshyiko.model.ResultRecord;

import java.util.List;

public interface CalculationListener {

    void onRecordsChanged(List<ResultRecord> records);
}
