package com.bt.utils;

import com.bt.dtos.BuildLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogger {
    private void logInfo(final BuildLog buildLogInfo) {
        log.info("{} - {} -> {}", buildLogInfo.getNameClass(), buildLogInfo.getNameMethod(),
                buildLogInfo.getMessage())
        ;
    }

    private void logErorr(final BuildLog buildLogError) {
        log.error("{} - {} -> {}", buildLogError.getNameClass(), buildLogError.getNameMethod(),
                buildLogError.getMessage(), buildLogError.getError())
        ;
    }

    public void logMain(final Boolean isEror, final BuildLog buildLog) {
        if(Boolean.FALSE.equals(isEror)) {
            buildLog.setError(null);
            logInfo(buildLog);
        } else {
            logErorr(buildLog);
        }
    }
}
