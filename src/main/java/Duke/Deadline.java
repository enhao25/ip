package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
            super.getStatusIcon(), super.toString(), by.format(DateTimeFormatter.ofPattern("d MMM yyyy - HHmm")));
    }
}