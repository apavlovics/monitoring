package lv.continuum.monitoring.model;

import org.junit.Assert;
import org.modelmapper.ModelMapper;

import lv.continuum.monitoring.config.MonitoringConfiguration;

public abstract class AbstractModelTest {

    protected static final ModelMapper modelMapper = new MonitoringConfiguration().modelMapper();

    protected <T> void testEqualsHashCodeToString(T model, T equalModel, T notEqualModel, boolean isEntity) {
        Assert.assertEquals(model, equalModel);
        Assert.assertEquals(model.hashCode(), equalModel.hashCode());

        // Equal entities can have different string representations
        if (!isEntity) {
            Assert.assertEquals(model.toString(), equalModel.toString());
        }

        Assert.assertNotEquals(model, notEqualModel);
        Assert.assertNotEquals(model.toString(), notEqualModel.toString());

        Assert.assertNotEquals(equalModel, notEqualModel);
        Assert.assertNotEquals(equalModel.toString(), notEqualModel.toString());
    }
}
