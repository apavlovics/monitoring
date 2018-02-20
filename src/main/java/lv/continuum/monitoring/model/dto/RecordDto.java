package lv.continuum.monitoring.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lv.continuum.monitoring.model.RecordType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RecordDto {

    private long id;

    @NotNull
    private RecordType recordType;
}
