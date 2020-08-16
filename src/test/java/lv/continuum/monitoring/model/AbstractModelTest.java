package lv.continuum.monitoring.model;

import lv.continuum.monitoring.config.MonitoringConfig;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractModelTest {

    protected static final ModelMapper modelMapper = new MonitoringConfig().modelMapper();

    protected <T> void testEqualsHashCodeToString(T model, T equalModel, T notEqualModel, boolean isEntity) {
        assertEquals(model, equalModel);
        assertEquals(model.hashCode(), equalModel.hashCode());

        // Equal entities can have different string representations
        if (!isEntity) {
            assertEquals(model.toString(), equalModel.toString());
        }

        assertNotEquals(model, notEqualModel);
        assertNotEquals(model.toString(), notEqualModel.toString());

        assertNotEquals(equalModel, notEqualModel);
        assertNotEquals(equalModel.toString(), notEqualModel.toString());
    }
}
