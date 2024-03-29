package lv.continuum.monitoring.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

class RecordTest extends AbstractModelTest {

    @Test
    void equalsHashCodeToString() {
        var createdAt = Instant.now();
        var record = new Record(123, RecordType.LOGIN, createdAt, null);
        var equalRecord = new Record(123, RecordType.LOGOUT, createdAt, null);
        var notEqualRecord = new Record(124, RecordType.LOGIN, createdAt, null);

        super.equalsHashCodeToString(record, equalRecord, notEqualRecord, true);
    }
}
