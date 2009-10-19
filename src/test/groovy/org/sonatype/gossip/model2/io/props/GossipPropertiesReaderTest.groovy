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

package org.sonatype.gossip.model2.io.props

import org.junit.Test
import org.sonatype.gossip.model2.Model
import org.sonatype.gossip.model2.io.xpp3.GossipXpp3Reader

/**
 * Tests for {@link GossipPropertiesReader}.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
class GossipPropertiesReaderTest
{
    @Test
    void testLoad() {
        URL url = getClass().getResource("gossip1.properties")
        GossipPropertiesReader reader = new GossipPropertiesReader()
        Model model = reader.read(url.openStream())
        println(model)
    }
}