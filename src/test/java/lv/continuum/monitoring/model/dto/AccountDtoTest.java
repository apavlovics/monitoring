package lv.continuum.monitoring.model.dto;

import lv.continuum.monitoring.model.AbstractModelTest;
import lv.continuum.monitoring.model.Account;
import org.junit.Assert;
import org.junit.Test;

public class AccountDtoTest extends AbstractModelTest {

    @Test
    public void testMapToAccount() {
        var accountDto = new AccountDto(123, "test");

        var account = modelMapper.map(accountDto, Account.class);
        Assert.assertEquals(accountDto.getId(), account.getId());
        Assert.assertEquals(accountDto.getUsername(), account.getUsername());
    }

    @Test
    public void testMapFromAccount() {
        var account = new Account();
        account.setId(123);
        account.setUsername("test");

        var accountDto = modelMapper.map(account, AccountDto.class);
        Assert.assertEquals(account.getId(), accountDto.getId());
        Assert.assertEquals(account.getUsername(), accountDto.getUsername());
    }

    @Test
    public void testEqualsHashCodeToString() {
        var accountDto = new AccountDto(123, "test");
        var equalAccountDto = new AccountDto(123, "test");
        var notEqualAccountDto = new AccountDto(123, "other");

        super.testEqualsHashCodeToString(accountDto, equalAccountDto, notEqualAccountDto, false);
    }
}
