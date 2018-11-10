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

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import static org.assertj.log.LogAssertions.assertThat;

/**
 *
 * @author Fabien DUMINY
 */
abstract public class AbstractLogAssertRuleIT {
    private final Class<? extends AbstractIntegration> integrationClass;

    protected AbstractLogAssertRuleIT(Class<? extends AbstractIntegration> integrationClass) {
        this.integrationClass = integrationClass;
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    @Test
    public final void testIntegration() {
        Result result = JUnitCore.runClasses(integrationClass);
        if (result.getFailureCount() > 0) {
            System.out.println(result.getFailures().get(0).getMessage());
            result.getFailures().get(0).getException().printStackTrace();
        }
        assertThat(result.wasSuccessful()).isTrue();
    }

    /**
     /**
     * @param <LV> Level class.
     * @param <LG> Logger class.
     * @param <A> Appender class.
     *
     * @author Fabien DUMINY
     */
    @RunWith(Theories.class)
    public static abstract class AbstractIntegration<LV, LG, A> {
        private final LogLevelMap<LV> levels;
        private final LogLevelMap<LogMethod<LG>> methods;
        private final LG logger;

        @Rule
        public final LogAssertRule<A> logs;

        protected AbstractIntegration(LogLevelMap<LV> levels, LogLevelMap<LogMethod<LG>> methods, LG logger, LoggerFacade<A> loggerFacade) {
            this.levels = levels;
            this.methods = methods;
            this.logger = logger;
            this.logs = new LogAssertRule<>(loggerFacade);
        }

        @Theory
        public final void testLog(LogLevel logLevel, Messages message, Throwables throwableEnum) {
            System.out.println("testLog(" + logLevel + ", " + message + ", " + throwableEnum + ")");

            final LV level = levels.get(logLevel);
            Assume.assumeNotNull(level);

            methods.get(logLevel).log(logger, message.getMessage(), throwableEnum.getThrowable());

            assertThat(logs).hasSize(1);
        }
    }
}
