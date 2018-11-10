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
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.InOrder;

/**
 * Test for class {@link LogAssertRule}.
 *
 * @author Fabien DUMINY
 */
@RunWith(Theories.class)
public class LogAssertRuleTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testInit() {
        LogAssertRule<String> rule = new LogAssertRule<>(mock(LoggerFacade.class));

        assertThat(rule.getEvents()).isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Theory
    public void testApply(boolean expectError) throws Throwable {
        System.out.println("testApply(expectError=" + expectError + ")");
        // prepare
        Statement statement = mock(Statement.class);
        if (expectError) {
            doThrow(Throwable.class).when(statement).evaluate();
        }
        LoggerFacade<String> loggerFacade = mock(LoggerFacade.class);
        LogAssertRule rule = new LogAssertRule(loggerFacade);
        String logAppender = "mockAppender";
        when(loggerFacade.setUp(refEq(rule))).thenReturn(logAppender);

        // test
        Statement resultStatement = rule.apply(statement, mock(Description.class));
        assertThat(resultStatement).as("resultStatement").isNotNull().isNotSameAs(statement);
        Throwable thrown = null;
        try {
            resultStatement.evaluate();
        } catch (Throwable throwable) {
            thrown = throwable;
        }

        // verify
        if (expectError) {
            assertThat(thrown).as("thrown").isNotNull();
        } else {
            assertThat(thrown).as("thrown").isNull();
        }
        InOrder inOrder = inOrder(statement, loggerFacade);
        inOrder.verify(loggerFacade).setUp(refEq(rule));
        inOrder.verify(statement).evaluate();
        inOrder.verify(loggerFacade).tearDown(refEq(logAppender));
        verifyNoMoreInteractions(statement, loggerFacade);
    }

    @SuppressWarnings("unchecked")
    @Theory
    public void testAppend(LogLevel level, Messages messages, Throwables throwable) {
        System.out.println("testAppend(" + level + ", " + messages + ", " + throwable + ")");
        // prepare
        LoggerFacade<String> loggerFacade = mock(LoggerFacade.class);
        LogAssertRule rule = new LogAssertRule(loggerFacade);
        final LogEvent expectedEvent = new LogEvent(level, "a.b.c", messages.getMessage(), throwable.getThrowable());

        // test
        rule.append(expectedEvent.getLoggerName(), expectedEvent.getLevel(), expectedEvent.getMessage(), expectedEvent.getThrowable());

        // verify
        assertThat(rule.getEvents()).usingFieldByFieldElementComparator().containsExactly(expectedEvent);
    }
}