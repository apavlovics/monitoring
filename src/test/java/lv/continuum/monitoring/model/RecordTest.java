package lv.continuum.monitoring.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class RecordTest extends AbstractModelTest {

    @Test
    public void testEqualsHashCodeToString() {
        var createdAt = new Date();
        var record = new Record(123, RecordType.LOGIN, createdAt, null);
        var equalRecord = new Record(123, RecordType.LOGOUT, createdAt, null);
        var notEqualRecord = new Record(124, RecordType.LOGIN, createdAt, null);

        super.testEqualsHashCodeToString(record, equalRecord, notEqualRecord, true);
    }
}
