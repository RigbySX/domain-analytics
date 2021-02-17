package sx.rigby.analytics.common.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WrappedValue {
    @Getter private final int uniqueJoins;
    @Getter private final int totalJoins;
}
