import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class LogsProcessor {
    private String date;
    private String type;

    public LogsProcessor() {
    }

    public LogsProcessor(String date, String type) {
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long writeLogsWithContentToFile(String inFilePath, String outFilePath) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(this.getDate() + " " + this.getType() + " - " + start + " -- started");
        List<String> found = Files.lines(Paths.get(inFilePath))
                .filter(lines -> lines.contains(this.getDate()))
                .filter(lines -> lines.contains(this.getType()))
                .collect(toList());
        Files.write(Paths.get(outFilePath), found);
        LocalDateTime finish = LocalDateTime.now();
        System.out.println(this.getDate() + " " + this.getType() + " - " + finish + " -- finished");
        System.out.println("Execution time of " + this.getDate() + " " + this.getType() + " is: "
                + ChronoUnit.MILLIS.between(start, finish));
        return ChronoUnit.MILLIS.between(start, finish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogsProcessor that = (LogsProcessor) o;
        return that.getType().equals(this.getType()) && that.getDate().equals(this.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, type);
    }

    @Override
    public String toString() {
        return "LogsProcessor{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
