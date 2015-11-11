/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.log;

import org.assertj.core.util.Arrays;

/**
 * A map between a {@link LogLevel} and another type of value.
 * @param <L> The class of values mapped with a {@link LogLevel}.
 *
 * @author Fabien DUMINY
 */
public class LogLevelMap<L> {
    @SuppressWarnings("unchecked")
    private final L[] userValues;

    public LogLevelMap(L fatal, L error, L warn, L info, L debug, L trace) {
        userValues = Arrays.array(fatal, error, warn, info, debug, trace);
    }

    public L get(LogLevel logLevel) {
        return (logLevel == null) ? null : userValues[logLevel.ordinal()];
    }

    public LogLevel get(L userValue) {
        for (LogLevel logLevel : LogLevel.values()) {
            final L usrValue = userValues[logLevel.ordinal()];
            if ((usrValue != null) && usrValue.equals(userValue)) {
                return logLevel;
            }
        }
        return null;
    }
}
