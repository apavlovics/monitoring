package lv.continuum.monitoring.controller.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import lv.continuum.monitoring.model.Record;
import lv.continuum.monitoring.model.dto.AccountDto;
import lv.continuum.monitoring.model.dto.RecordDto;
import lv.continuum.monitoring.repo.AccountRepository;
import lv.continuum.monitoring.repo.RecordRepository;

@RestController
@RequestMapping(value = "/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping(value = "/{accountId}", consumes = MediaType.ALL_VALUE)
    public AccountDto getAccountDto(
            @PathVariable long accountId) throws Exception {
        Account account = getAccount(accountId);
        return modelMapper.map(account, AccountDto.class);
    }

    @PostMapping
    public Account saveAccountDto(
            @RequestBody @Validated AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        return accountRepository.save(account);
    }

    @GetMapping(value = "/{accountId}/records", consumes = MediaType.ALL_VALUE)
    public List<RecordDto> getRecordsByAccountId(
            @PathVariable long accountId) throws Exception {
        Account account = getAccount(accountId);
        List<Record> records = recordRepository.findByAccount(account);
        return modelMapper.map(records, new TypeToken<List<RecordDto>>() {}.getType());
    }

    @PostMapping(value = "/{accountId}/records")
    public Record saveRecord(
            @PathVariable long accountId,
            @RequestBody @Validated RecordDto recordDto) throws Exception {
        Account account = getAccount(accountId);
        Record record = modelMapper.map(recordDto, Record.class);
        record.setAccount(account);
        return recordRepository.save(record);
    }

    private Account getAccount(long accountId) throws AccountNotFoundException {
        return accountRepository.findOne(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
