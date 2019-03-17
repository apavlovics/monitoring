package lv.continuum.monitoring.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    private long id;

    @NotNull
    @Pattern(regexp = "^[a-z0-9\\-]{3,200}$")
    private String username;
}
