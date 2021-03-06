/*
 * Copyright (c) 2009-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.planet57.gossip.listener;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Writer which counts the number of bytes written.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 1.0
 */
public class CountingWriter
    extends FilterWriter
{
  private long count = 0;

  public CountingWriter(final Writer out) {
    super(out);
  }

  public long size() {
    return count;
  }

  public void reset() {
    count = 0;
  }

  @Override
  public void write(final int c) throws IOException {
    super.write(c);
    count++;
  }

  @Override
  public void write(final char[] cbuf, final int off, final int len) throws IOException {
    super.write(cbuf, off, len);
    count += len - off;
  }

  @Override
  public void write(final String str, final int off, final int len) throws IOException {
    super.write(str, off, len);
    count += str.length();
  }
}