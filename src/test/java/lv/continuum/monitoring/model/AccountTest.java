package lv.continuum.monitoring.model;

import org.junit.jupiter.api.Test;

class AccountTest extends AbstractModelTest {

    @Test
    void equalsHashCodeToString() {
        var account = new Account(123, "test", null);
        var equalAccount = new Account(123, "other", null);
        var notEqualAccount = new Account(124, "test", null);

        super.testEqualsHashCodeToString(account, equalAccount, notEqualAccount, true);
    }
}
