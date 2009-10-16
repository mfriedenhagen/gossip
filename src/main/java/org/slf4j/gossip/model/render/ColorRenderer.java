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

package org.slf4j.gossip.model.render;

import jline.Terminal;
import jline.TerminalFactory;
import org.slf4j.gossip.Event;
import org.slf4j.gossip.Level;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.Ansi.Attribute;

/**
 * Renders events with ANSI colors.
 *
 * @since 1.0
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class ColorRenderer
    extends Renderer
{
    private boolean truncate = false;

    private int maxLength;

    public ColorRenderer() {
        Terminal term = TerminalFactory.get();
        int w = term.getWidth() - 1;
    }

    public String toString() {
        return "ColorRenderer{" +
                "truncate=" + truncate +
                ", maxLength=" + maxLength +
                '}';
    }

    public boolean isTruncate() {
        return truncate;
    }

    public void setTruncate(final boolean flag) {
        this.truncate = flag;
    }

    public void setTruncate(final String flag) {
        setTruncate(Boolean.valueOf(flag).booleanValue());
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(final int length) {
        this.maxLength = length;
    }

    public void setMaxLength(final String length) {
        assert length != null;

        setMaxLength(Integer.parseInt(length));
    }

    public String render(final Event event) {
        assert event != null;

        Ansi ansi = Ansi.ansi();

        ansi = ansi.a("[");

        switch (event.level.id) {
            case Level.TRACE_ID:
            case Level.DEBUG_ID:
                ansi = ansi.fg(Color.YELLOW).a(event.level.label).reset();
                break;

            case Level.INFO_ID:
                ansi = ansi.fg(Color.GREEN).a(event.level.label).reset();
                break;

            case Level.WARN_ID:
            case Level.ERROR_ID:
                ansi = ansi.fg(Color.RED).a(event.level.label).reset();
                break;

            default:
                throw new InternalError();
        }

        ansi = ansi.a("]");

        switch (event.level.id) {
            case Level.INFO_ID:
            case Level.WARN_ID:
                ansi = ansi.a(" ");
        }

        ansi = ansi.a(" ").a(event.logger.getName()).a(" - ").a(event.message).a(NEWLINE);

        if (event.cause != null) {
            ansi = ansi.a(event.toString());

            StackTraceElement[] trace = event.cause.getStackTrace();
            for (int i=0; i<trace.length; i++ ) {
                ansi = ansi.a(trace[i].toString());
            }
        }

        //
        // FIXME: Neeed a better solution for this which handles exceptions, multi-line messages
        //        and color escaping properly...
        //

        // if (truncate && buff.getPlainBuffer().length() > maxLength) {
        //     return buff.toString().substring(0, maxLength - 4) + " ..." + NEWLINE;
        // }

        return ansi.toString();
    }
}