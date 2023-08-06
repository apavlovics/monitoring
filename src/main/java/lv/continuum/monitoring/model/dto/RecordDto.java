package lv.continuum.monitoring.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.continuum.monitoring.model.RecordType;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordDto {

    private long id;

    @NotNull
    private RecordType recordType;

    @NotNull
    private Instant createdAt = Instant.now();
}
