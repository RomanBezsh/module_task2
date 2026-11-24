package ad251.bezshyiko.io;

import ad251.bezshyiko.model.ResultRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class ResultExporter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void export(File file, List<ResultRecord> records) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            writer.write("Звіт інвестиційного калькулятора");
            writer.newLine();
            writer.write("Дата формування: " + LocalDateTime.now().format(DATE_FORMAT));
            writer.newLine();
            writer.write("Кількість розрахунків: " + records.size());
            writer.newLine();
            writer.newLine();
            writer.write("№\tТип розрахунку\tСума\tСтавка, %\tТермін, років\tДодатково\tРезультат");
            writer.newLine();
            for (ResultRecord record : records) {
                writer.write(record.getNumber()
                        + "\t" + record.getType().getDisplayName()
                        + "\t" + record.getAmount()
                        + "\t" + record.getRate()
                        + "\t" + record.getTerm()
                        + "\t" + record.getExtra()
                        + "\t" + record.getResultText());
                writer.newLine();
            }
        }
    }
}
