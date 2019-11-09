package org.ff4j.event;
/*-
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2018 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.ff4j.audit.AuditTrailInMemoryTest;
import org.ff4j.audit.AuditTrailRepositoryInMemory;
import org.ff4j.feature.usage.repository.EventRepositorySupport;
import org.ff4j.feature.usage.repository.FeatureUsageRepositoryInMemory;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit tests for {@link AuditTrailRepositoryInMemory}, in-memory store for {@link AuditTrailInMemoryTest}
 */
@DisplayName("RepositoryEvent_InMemory_Test")
public class EventRepositoryTestInMemory extends EventRepositoryTestSupport {
 
    /** {@inheritDoc} */
    @Override
    public EventRepositorySupport initRepository() {
        return new FeatureUsageRepositoryInMemory();
    }
    
}