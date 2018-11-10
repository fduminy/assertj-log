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

/**
 * @author Fabien DUMINY
 */
@SuppressWarnings({ "ThrowableInstanceNeverThrown", "unused" })
public enum Throwables {
    NULL_THROWABLE(null),
    THROWABLE(new Exception("error message"));

    private final java.lang.Throwable throwable;

    Throwables(java.lang.Throwable throwable) {
        this.throwable = throwable;
    }

    public java.lang.Throwable getThrowable() {
        return throwable;
    }
}
