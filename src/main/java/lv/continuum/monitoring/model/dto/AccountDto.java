package lv.continuum.monitoring.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AccountDto {

    private long id;

    @NotNull
    @Pattern(regexp = "^[a-z0-9\\-]{3,200}$")
    private String username;
}
