package lv.continuum.monitoring.model.dto;

import lv.continuum.monitoring.model.AbstractModelTest;
import lv.continuum.monitoring.model.Record;
import lv.continuum.monitoring.model.RecordType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordDtoTest extends AbstractModelTest {

    @Test
    void mapToRecord() {
        var recordDto = new RecordDto(123, RecordType.LOGIN, Instant.now());

        var record = modelMapper.map(recordDto, Record.class);
        assertEquals(recordDto.getId(), record.getId());
        assertEquals(recordDto.getRecordType(), record.getRecordType());
        assertEquals(recordDto.getCreatedAt(), record.getCreatedAt());
    }

    @Test
    void mapFromRecord() {
        var record = new Record();
        record.setId(123);
        record.setRecordType(RecordType.LOGIN);
        record.setCreatedAt(Instant.now());

        var recordDto = modelMapper.map(record, RecordDto.class);
        assertEquals(record.getId(), recordDto.getId());
        assertEquals(record.getRecordType(), recordDto.getRecordType());
        assertEquals(record.getCreatedAt(), recordDto.getCreatedAt());
    }

    @Test
    void equalsHashCodeToString() {
        var createdAt = Instant.now();
        var recordDto = new RecordDto(123, RecordType.LOGIN, createdAt);
        var equalRecordDto = new RecordDto(123, RecordType.LOGIN, createdAt);
        var notEqualRecordDto = new RecordDto(123, RecordType.LOGOUT, Instant.now());

        super.equalsHashCodeToString(recordDto, equalRecordDto, notEqualRecordDto, false);
    }
}
