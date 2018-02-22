package lv.continuum.monitoring.model.dto;

import org.junit.Assert;
import org.junit.Test;

import lv.continuum.monitoring.model.AbstractModelTest;
import lv.continuum.monitoring.model.Account;

public class AccountDtoTest extends AbstractModelTest {

    @Test
    public void testMapToAccount() {
        AccountDto accountDto = new AccountDto(123, "test");

        Account account = modelMapper.map(accountDto, Account.class);
        Assert.assertEquals(accountDto.getId(), account.getId());
        Assert.assertEquals(accountDto.getUsername(), account.getUsername());
    }

    @Test
    public void testMapFromAccount() {
        Account account = new Account();
        account.setId(123);
        account.setUsername("test");

        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        Assert.assertEquals(account.getId(), accountDto.getId());
        Assert.assertEquals(account.getUsername(), accountDto.getUsername());
    }

    @Test
    public void testEqualsHashCodeToString() {
        AccountDto accountDto = new AccountDto(123, "test");
        AccountDto equalAccountDto = new AccountDto(123, "test");
        AccountDto notEqualAccountDto = new AccountDto(123, "other");

        super.testEqualsHashCodeToString(accountDto, equalAccountDto, notEqualAccountDto, false);
    }
}
