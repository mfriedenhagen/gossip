/*
 * Copyright (C) 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sonatype.gossip;

import org.slf4j.Logger;

/**
 * Gossip logging event container.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 *
 * @since 1.0
 */
public final class Event
{
    private final Logger logger;

    private final Level level;

    private final String message;

    private final Throwable cause;

    private final long timeStamp;

    private final String threadName;

    //
    // TODO: Optionally (and disabled by default) provide location muck
    //

    public Event(final Logger logger, final Level level, final String message, final Throwable cause) {
        this.logger = logger;
        this.level = level;
        this.message = message;
        this.cause = cause;
        this.timeStamp = System.currentTimeMillis();
        this.threadName = Thread.currentThread().getName();
    }

    public String getName() {
        return logger.getName();
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getThreadName() {
        return threadName;
    }

    @Override
    public String toString() {
        return "Event{" +
            "logger=" + logger +
            ", level=" + level +
            ", message='" + message + '\'' +
            ", cause=" + cause +
            ", timeStamp=" + timeStamp +
            ", threadName='" + threadName + '\'' +
            '}';
    }
}