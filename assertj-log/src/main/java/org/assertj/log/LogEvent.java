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

/**
 * @author Fabien DUMINY
 */
class LogEvent {
    private final LogLevel level;
    private final String loggerName;
    private final String message;
    private final Throwable throwable;

    LogEvent(LogLevel level, String loggerName, Object message, Throwable throwable) {
        this.level = level;
        this.loggerName = loggerName;
        this.message = (message == null) ? null : message.toString();
        this.throwable = throwable;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "level=" + level +
                ", loggerName='" + loggerName + '\'' +
                ", message='" + message + '\'' +
                ", throwable=" + throwable +
                '}';
    }
}
