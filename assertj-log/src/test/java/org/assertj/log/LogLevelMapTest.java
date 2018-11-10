/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.log;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Tests for class {@link LogLevelMap}.
 *
 * @author Fabien DUMINY
 */
@RunWith(Theories.class)
public class LogLevelMapTest {
    @Theory
    public void testGet_userValue_notNullArg(UserValue nullValue) {
        LogLevelMap<UserValue> map = createLogLevelMap(nullValue);

        LogLevel logLevel = map.get(nullValue);

        assertThat(logLevel).as("logLevel").isNull();
    }

    @Theory
    public void testGet_userValue_nullInConstructor(UserValue userValue) {
        LogLevelMap<UserValue> map = createLogLevelMap(null);

        LogLevel logLevel = map.get(userValue);

        assertThat(logLevel).as("logLevel").isSameAs(userValue.logLevel);
    }

    @Test
    public void testGet_userLevel_nullArg() {
        LogLevelMap<UserValue> map = createLogLevelMap(null);

        LogLevel logLevel = map.get((UserValue) null);

        assertThat(logLevel).as("logLevel").isNull();
    }

    @Theory
    public void testGet_logLevel_notNullArg(UserValue expectedUserValue) {
        LogLevelMap<UserValue> map = createLogLevelMap(null);

        UserValue userValue = map.get(expectedUserValue.logLevel);

        assertThat(userValue).as("userValue").isSameAs(expectedUserValue);
    }

    @Theory
    public void testGet_logLevel_nullInConstructor(UserValue nullValue) {
        LogLevelMap<UserValue> map = createLogLevelMap(nullValue);

        UserValue userValue = map.get(nullValue.logLevel);

        assertThat(userValue).as("userValue").isNull();
    }

    @Test
    public void testGet_logLevel_nullArg() {
        LogLevelMap<UserValue> map = createLogLevelMap(null);

        UserValue userValue = map.get((LogLevel) null);

        assertThat(userValue).as("userValue").isNull();
    }

    private static LogLevelMap<UserValue> createLogLevelMap(UserValue nullValue) {
        return new LogLevelMap<>(levelOrNull(UserValue.FATAL, nullValue), levelOrNull(UserValue.ERROR, nullValue),
                levelOrNull(UserValue.WARN, nullValue), levelOrNull(UserValue.INFO, nullValue),
                levelOrNull(UserValue.DEBUG, nullValue), levelOrNull(UserValue.TRACE, nullValue));
    }

    private static UserValue levelOrNull(UserValue level, UserValue nullValue) {
        return level.equals(nullValue) ? null : level;
    }

    public enum UserValue {
        FATAL(LogLevel.FATAL),
        ERROR(LogLevel.ERROR),
        WARN(LogLevel.WARN),
        INFO(LogLevel.INFO),
        DEBUG(LogLevel.DEBUG),
        TRACE(LogLevel.TRACE);

        private final LogLevel logLevel;

        UserValue(LogLevel logLevel) {
            this.logLevel = logLevel;
        }
    }
}