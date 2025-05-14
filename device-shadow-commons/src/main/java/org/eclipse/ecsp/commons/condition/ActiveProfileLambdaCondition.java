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

import org.eclipse.ecsp.commons.constants.DeviceConfigConstants;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * This class represents a condition that checks if the active profile is set to "lambda".
 * It implements the {@link Condition} interface and is used to determine if a bean should be created
 * based on the active profile and the execution environment.
 */
public class ActiveProfileLambdaCondition implements Condition {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(ActiveProfileLambdaCondition.class);

    /**
     * Determines if the condition matches the given context and metadata.
     *
     * @param context  the condition context
     * @param metadata the metadata of the annotated type
     * @return true if the condition matches, false otherwise
     */
    @Override
    public boolean matches(ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
        boolean flag = false;
        if (context.getEnvironment().getActiveProfiles().length > 0) {
            String activeProfile = context.getEnvironment().getActiveProfiles()[0];
            String apiExeEnv = context.getEnvironment().getProperty(DeviceConfigConstants.API_EXECUTION_ENV_PROP);
            if (apiExeEnv != null && apiExeEnv.equals(DeviceConfigConstants.LAMBDA)) {
                flag = true;
                LOGGER.info("ActiveProfileLambdaCondition set with active profile {}", activeProfile);
            }
        }
        return flag;
    }
}