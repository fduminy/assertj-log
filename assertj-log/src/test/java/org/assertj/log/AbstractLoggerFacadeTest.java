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
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.After;
import org.junit.Test;

/**
 * @param <A> Class of appender.
 * @param <LF> Class of {@link LoggerFacade}.
 *
 * @author Fabien DUMINY
 */
public abstract class AbstractLoggerFacadeTest<A, LF extends LoggerFacade<A>> {
    private final Class<A> appenderClass;

    protected AbstractLoggerFacadeTest(Class<A> appenderClass) {
        this.appenderClass = appenderClass;
    }

    abstract protected LF createLoggerFacade();

    abstract protected List<A> getAllAppenders(Class<A> clazz);

    abstract protected void removeAllAppenders();

    @After
    public final void tearDown() {
        removeAllAppenders();
    }

    @Test
    public final void testInit() {
        createLoggerFacade();

        assertThat(getAllAppenders(appenderClass)).isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Test
    public final void testSetUp() {
        LF facade = createLoggerFacade();

        A appender = facade.setUp(mock(Appender.class));

        assertThat(appender).isNotNull();
        assertThat(getAllAppenders(appenderClass)).containsExactly(appender);
    }

    @SuppressWarnings("unchecked")
    @Test
    public final void testTearDown() {
        LF facade = createLoggerFacade();
        A appender = facade.setUp(mock(Appender.class));

        facade.tearDown(appender);

        assertThat(getAllAppenders(appenderClass)).isEmpty();
    }
}