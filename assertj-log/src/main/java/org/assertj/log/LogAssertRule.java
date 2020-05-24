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

import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A JUnit {@link TestRule} recording all logs.
 *
 * @param <A> The class of appender.
 *
 * @author Fabien DUMINY
 */
public class LogAssertRule<A> implements TestRule, Appender {
    private final List<LogEvent> events = new ArrayList<>();
    private final LoggerFacade<A> loggerFacade;

    public LogAssertRule(LoggerFacade<A> loggerFacade) {
        this.loggerFacade = loggerFacade;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                A appender = loggerFacade.setUp(LogAssertRule.this);
                try {
                    base.evaluate();
                } finally {
                    loggerFacade.tearDown(appender);
                }
            }
        };
    }

    @Override
    public void append(String loggerName, LogLevel level, Object message, Throwable throwable) {
        //TODO add filtering
        events.add(new LogEvent(level, loggerName, message, throwable));
    }

    public List<LogEvent> getEvents() {
        return events;
    }
}
