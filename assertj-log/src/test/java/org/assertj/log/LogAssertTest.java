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

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Fabien DUMINY
 */
public class LogAssertTest {
    @SuppressWarnings("ResultOfMethodCallIgnored") @Test
    public void testInit() {
        // prepare
        LogAssertRule rule = mock(LogAssertRule.class);

        // test
        LogAssert logAssert = new LogAssert(rule);

        // verify
        verify(rule).getEvents();
        Assertions.assertThat(logAssert).isInstanceOf(AbstractListAssert.class);
    }

    @Test
    public void testHasSize() {
        List<LogEvent> events = new ArrayList<>();
        LogAssertRule rule = mock(LogAssertRule.class);
        when(rule.getEvents()).thenReturn(events);

        LogAssert logAssert = new LogAssert(rule);
        logAssert.hasSize(0);

        events.add(new LogEvent(LogLevel.DEBUG, "a.b.c", "message", new Exception("error")));
        logAssert.hasSize(1);
    }
}