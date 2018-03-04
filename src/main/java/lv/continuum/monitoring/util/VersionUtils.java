package lv.continuum.monitoring.util;

import org.apache.commons.lang3.StringUtils;

public class VersionUtils {

    private static final String VERSION_NUMBER_DEFAULT = "N/A";

    /**
     * Gets the version number of the application, as defined in <i>pom.xml</i>.
     * 
     * @return
     *         The version number or {@value #VERSION_NUMBER_DEFAULT},
     *         if it is not known.
     */
    public static final String getVersionNumber() {
        String versionNumber = VersionUtils.class.getPackage().getImplementationVersion();
        return StringUtils.isNotBlank(versionNumber) ? versionNumber : VERSION_NUMBER_DEFAULT;
    }
}
