/********************************************************************************
 * *******************************************************************************
 *  * Copyright (c) 2023-24 Harman International
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *     
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *******************************************************************************
 *******************************************************************************/

package org.eclipse.ecsp.commons.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * This class represents a condition that checks if the active profile matches a specific condition.
 * It implements the {@link Condition} interface.
 */
public class TestProfileCondition implements Condition {

    private ActiveProfileCondition activeProfile = new ActiveProfileCondition();

    /**
        * Determines if the condition matches the given context and metadata.
        *
        * @param context  the condition context
        * @param metadata the annotated type metadata
        * @return true if the condition matches, false otherwise
        */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return (!activeProfile.matches(context, metadata));
    }
}