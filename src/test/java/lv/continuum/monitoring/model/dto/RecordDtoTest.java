package lv.continuum.monitoring.model.dto;

import lv.continuum.monitoring.model.AbstractModelTest;
import lv.continuum.monitoring.model.Record;
import lv.continuum.monitoring.model.RecordType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordDtoTest extends AbstractModelTest {

    @Test
    public void testMapToRecord() {
        var recordDto = new RecordDto(123, RecordType.LOGIN, new Date());

        var record = modelMapper.map(recordDto, Record.class);
        assertEquals(recordDto.getId(), record.getId());
        assertEquals(recordDto.getRecordType(), record.getRecordType());
        assertEquals(recordDto.getCreatedAt(), record.getCreatedAt());
    }

    @Test
    public void testMapFromRecord() {
        var record = new Record();
        record.setId(123);
        record.setRecordType(RecordType.LOGIN);
        record.setCreatedAt(new Date());

        var recordDto = modelMapper.map(record, RecordDto.class);
        assertEquals(record.getId(), recordDto.getId());
        assertEquals(record.getRecordType(), recordDto.getRecordType());
        assertEquals(record.getCreatedAt(), recordDto.getCreatedAt());
    }

    @Test
    public void testEqualsHashCodeToString() {
        var createdAt = new Date();
        var recordDto = new RecordDto(123, RecordType.LOGIN, createdAt);
        var equalRecordDto = new RecordDto(123, RecordType.LOGIN, createdAt);
        var notEqualRecordDto = new RecordDto(123, RecordType.LOGOUT, new Date());

        super.testEqualsHashCodeToString(recordDto, equalRecordDto, notEqualRecordDto, false);
    }
}
