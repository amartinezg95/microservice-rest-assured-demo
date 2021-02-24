package steps.common.context;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class PerformanceContext {
    private Long responseTimeoutValue = null;
    private TimeUnit responseTimeoutUnits = null;
}
