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
package org.assertj.jul;

import static org.assertj.jul.JulLoggerFacade.JUL;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.assertj.log.AbstractLogAssertRuleIT;

/**
 * @author Fabien DUMINY
 */
public class JulLogAssertRuleIT extends AbstractLogAssertRuleIT {
    public JulLogAssertRuleIT() {
        super(JulIntegration.class);
    }

    public static class JulIntegration extends AbstractIntegration<Level, Logger, JulListAppender> {
        public JulIntegration() {
            super(JulListAppender.LEVELS, JulListAppenderTest.getLogMethodMap(), Logger.getLogger(JulLogAssertRuleIT.class.getName()), JUL);
        }
    }
}
