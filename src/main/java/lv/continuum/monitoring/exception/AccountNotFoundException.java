package lv.continuum.monitoring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {

    private static final long serialVersionUID = -5918401872709055353L;

    public AccountNotFoundException(long accountId) {
        super("Account id " + accountId + " not found");
    }
}
