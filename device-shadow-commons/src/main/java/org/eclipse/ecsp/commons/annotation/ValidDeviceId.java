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
 * The {@code ValidDeviceId} annotation is used to validate the device ID.
 * It ensures that the device ID only contains alphanumeric characters.
 * This annotation can be applied to fields, methods, parameters, and constructors.
 */
@Target({METHOD, FIELD, PARAMETER, CONSTRUCTOR})
// specifies where this validation can be used (Field, Method, Parameter etc)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp = "[a-zA-Z0-9]+")
public @interface ValidDeviceId {

    /**
     * Returns the error message to be displayed when the device ID is invalid.
     * The default value is {@link DeviceConfigConstants#INVALID_DEVICE_ID_MSG}.
     *
     * @return the error message for invalid device ID
     */
    String message() default DeviceConfigConstants.INVALID_DEVICE_ID_MSG;

    /**
     * Specifies the validation groups that should be applied when validating the device ID.
     * By default, no groups are specified.
     *
     * @return the validation groups to apply
     */
    Class<?>[] groups() default {};

    /**
     * Returns the payload types associated with the validation constraint.
     *
     * @return an array of payload types
     */
    Class<? extends Payload>[] payload() default {};
}