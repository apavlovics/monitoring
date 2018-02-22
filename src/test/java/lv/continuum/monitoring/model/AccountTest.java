package lv.continuum.monitoring.model;

import org.junit.Test;

public class AccountTest extends AbstractModelTest {

    @Test
    public void testEqualsHashCodeToString() {
        Account account = new Account(123, "test", null);
        Account equalAccount = new Account(123, "other", null);
        Account notEqualAccount = new Account(124, "test", null);

        super.testEqualsHashCodeToString(account, equalAccount, notEqualAccount, true);
    }
}
