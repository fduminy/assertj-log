/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.log;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * A JUnit {@link TestRule} recording all logs.
 *
 * @author Fabien DUMINY
 */
public class LogAssertRule<T> implements TestRule, Appender {
    private final List<LogEvent> events = new ArrayList<>();
    private final LoggerFacade<T> loggerFacade;

    public LogAssertRule(LoggerFacade<T> loggerFacade) {
        this.loggerFacade = loggerFacade;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                T appender = loggerFacade.setUp(LogAssertRule.this);
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

    List<LogEvent> getEvents() {
        return events;
    }
}
