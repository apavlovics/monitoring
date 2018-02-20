package lv.continuum.monitoring.util;

import org.apache.commons.lang3.StringUtils;

public class VersionUtils {

    public static final String getVersionNumber() {
        String versionNumber = VersionUtils.class.getPackage().getImplementationVersion();
        return StringUtils.isNotBlank(versionNumber) ? versionNumber : "N/A";
    }
}
