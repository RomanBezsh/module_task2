package ad251.bezshyiko.io;

import ad251.bezshyiko.model.CalculationType;
import ad251.bezshyiko.model.ResultRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ResultExporter")
class ResultExporterTest {

    @TempDir
    Path tempDir;

    @Test
    void writesReportToTextFile() throws IOException {
        ResultRecord record = ResultRecord.builder()
                .number(1)
                .type(CalculationType.SIMPLE_INTEREST)
                .amount("1000.00")
                .rate("10.00")
                .term("2.00")
                .extra("—")
                .resultValue(1200.0)
                .resultText("1200.00 грн")
                .build();
        List<ResultRecord> records = List.of(record);
        Path file = tempDir.resolve("report.txt");

        ResultExporter.export(file.toFile(), records);

        String content = Files.readString(file, StandardCharsets.UTF_8);
        assertTrue(content.contains("Звіт інвестиційного калькулятора"));
        assertTrue(content.contains("Прості відсотки"));
        assertTrue(content.contains("1200.00 грн"));
    }
}
