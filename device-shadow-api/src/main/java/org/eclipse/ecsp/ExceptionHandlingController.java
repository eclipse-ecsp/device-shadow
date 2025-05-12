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

package org.eclipse.ecsp;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is a controller advice that handles exceptions thrown by the application.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    /**
     * Handles ConstraintViolationException and returns a ResponseEntity with an ExceptionResponse.
     * This method is responsible for handling ConstraintViolationException and building an ExceptionResponse
     * with the code and description based on the constraint violations.
     *
     * @param ex The ConstraintViolationException to be handled.
     * @return A ResponseEntity containing the ExceptionResponse and HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationExceptions(
        final ConstraintViolationException ex) {

        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> failure : ex.getConstraintViolations()) {
            builder.append(failure.getMessage());
        }
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(builder.toString());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}