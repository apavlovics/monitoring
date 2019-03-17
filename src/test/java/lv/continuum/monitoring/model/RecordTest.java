package lv.continuum.monitoring.model;

import org.junit.Test;

import java.util.Date;

public class RecordTest extends AbstractModelTest {

    @Test
    public void testEqualsHashCodeToString() {
        Date createdAt = new Date();
        Record record = new Record(123, RecordType.LOGIN, createdAt, null);
        Record equalRecord = new Record(123, RecordType.LOGOUT, createdAt, null);
        Record notEqualRecord = new Record(124, RecordType.LOGIN, createdAt, null);

        super.testEqualsHashCodeToString(record, equalRecord, notEqualRecord, true);
    }
}
