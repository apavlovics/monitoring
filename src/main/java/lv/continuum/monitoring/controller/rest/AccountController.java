package lv.continuum.monitoring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.continuum.monitoring.exception.AccountNotFoundException;
import lv.continuum.monitoring.model.Account;
import lv.continuum.monitoring.repo.AccountRepository;

@RestController
@RequestMapping(value = "/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(value = "/{accountId}", consumes = MediaType.ALL_VALUE)
    public Account getAccount(@PathVariable long accountId) throws Exception {
        return accountRepository.findOne(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    @PostMapping
    public Account registerAccount(@RequestBody @Validated Account account) {
        return accountRepository.save(account);
    }
}
