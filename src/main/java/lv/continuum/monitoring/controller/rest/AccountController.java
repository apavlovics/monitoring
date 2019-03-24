package lv.continuum.monitoring.controller.rest;

import io.swagger.annotations.ApiOperation;
import lv.continuum.monitoring.exception.AccountNotFoundException;
import lv.continuum.monitoring.model.Account;
import lv.continuum.monitoring.model.Record;
import lv.continuum.monitoring.model.dto.AccountDto;
import lv.continuum.monitoring.model.dto.RecordDto;
import lv.continuum.monitoring.repo.AccountRepository;
import lv.continuum.monitoring.repo.RecordRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RecordRepository recordRepository;

    @ApiOperation("Get the account by the account id")
    @GetMapping(value = "/{accountId}", consumes = MediaType.ALL_VALUE)
    public AccountDto getAccountDto(
            @PathVariable long accountId) throws Exception {
        Account account = getAccount(accountId);
        return modelMapper.map(account, AccountDto.class);
    }

    @ApiOperation("Save the given account")
    @PostMapping
    public AccountDto saveAccountDto(
            @RequestBody @Validated AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);

        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountDto.class);
    }

    @ApiOperation("Get the records by the account id")
    @GetMapping(value = "/{accountId}/records", consumes = MediaType.ALL_VALUE)
    public List<RecordDto> getRecordDtosByAccountId(
            @PathVariable long accountId) throws Exception {
        Account account = getAccount(accountId);
        List<Record> records = recordRepository.findByAccountOrderByCreatedAtDesc(account);
        return modelMapper.map(records, new TypeToken<List<RecordDto>>() {}.getType());
    }

    @ApiOperation("Save the given record")
    @PostMapping(value = "/{accountId}/records")
    public RecordDto saveRecordDto(
            @PathVariable long accountId,
            @RequestBody @Validated RecordDto recordDto) throws Exception {
        Account account = getAccount(accountId);
        Record record = modelMapper.map(recordDto, Record.class);
        record.setAccount(account);

        Record savedRecord = recordRepository.save(record);
        return modelMapper.map(savedRecord, RecordDto.class);
    }

    private Account getAccount(long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
