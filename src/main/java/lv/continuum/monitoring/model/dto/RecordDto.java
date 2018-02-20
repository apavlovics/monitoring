package lv.continuum.monitoring.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lv.continuum.monitoring.model.RecordType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RecordDto {

    private long id;

    @NotNull
    private RecordType recordType;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdAt = new Date();
}
