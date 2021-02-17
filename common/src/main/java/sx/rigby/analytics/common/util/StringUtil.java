package sx.rigby.analytics.common.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class StringUtil {
    private final Pattern PATTERN = Pattern.compile(":");

    /**
     * Removes the port attached to a hostname.
     */
    public String parse(String string) {
        return PATTERN.split(string)[0];
    }
}
