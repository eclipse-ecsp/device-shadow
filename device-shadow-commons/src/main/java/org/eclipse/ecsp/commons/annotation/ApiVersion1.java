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

package org.eclipse.ecsp.commons.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.eclipse.ecsp.commons.constants.DeviceConfigConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * This annotation is used to specify that a field, method, constructor, or parameter should be validated
 * against the API version 1.
 *
 * <p>
 * The validation is performed using the following constraints:
 * - The value must not be blank.
 * - The value must match the regular expression "^v1$".
 *
 * <p>
 * If the validation fails, a single violation is reported with the message defined in
 * {@link DeviceConfigConstants#UNSUPPORTED_API_VERSION_MSG}.
 */
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
// specifies where this validation can be used (Field, Method, Parameter etc)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@NotBlank
@Pattern(regexp = "^v1$")

public @interface ApiVersion1 {

    /**
     * Returns the message associated with the unsupported API version.
     * The default value is {@link DeviceConfigConstants#UNSUPPORTED_API_VERSION_MSG}.
     *
     * @return the message associated with the unsupported API version
     */
    String message() default DeviceConfigConstants.UNSUPPORTED_API_VERSION_MSG;

    /**
     * Specifies the groups that this API version belongs to.
     * This can be used for grouping related APIs together.
     * By default, no groups are assigned.
     *
     * @return an array of classes representing the groups
     */
    Class<?>[] groups() default {};

    /**
     * Returns the payload types associated with this API version.
     *
     * @return an array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}