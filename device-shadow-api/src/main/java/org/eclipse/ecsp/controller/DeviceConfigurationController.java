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

package org.eclipse.ecsp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.eclipse.ecsp.commons.annotation.ApiVersion1;
import org.eclipse.ecsp.commons.annotation.ValidDeviceId;
import org.eclipse.ecsp.entities.Configuration;
import org.eclipse.ecsp.security.HeaderContext;
import org.eclipse.ecsp.security.Security;
import org.eclipse.ecsp.security.UserDetails;
import org.eclipse.ecsp.service.DeviceConfigurationService;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


/**
 * Controller class for managing device configurations.
 */
@RestController
//@RequestMapping(value = "/{apiVersion}/devices")
@Validated
public class DeviceConfigurationController {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(DeviceConfigurationController.class);

    /**
     * Autowires the DeviceConfigurationService dependency.
     */
    @Autowired
    private DeviceConfigurationService service;

    /**
     * Retrieves the configuration for a specific device.
     *
     * @param apiVersion The version of the API.
     * @param deviceId The ID of the device.
     * @param since The timestamp indicating the starting point for retrieving the configuration.
     * @return A list of maps representing the configuration.
     */
    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/{apiVersion}/devices/{deviceId}/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "GET /{apiVersion}/devices/{deviceId}/configuration",
        description = "Fetch configuration request sent by Device-Message",
        responses = { @ApiResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = Configuration.class))))})
    @SecurityRequirement(name = "JwtAuthValidator", scopes = {"dongle,hu,tcu,Dongle,dashcam"})
    public List<Map> getConfigurationV1(@ApiVersion1 @PathVariable("apiVersion") String apiVersion,
                                      @ValidDeviceId @PathVariable("deviceId") String deviceId,
                                      @RequestParam(value = "since", required = true) @Valid @Pattern(
                                          message = "Invalid Timestamp", regexp = "[0-9]+") String since) {
        UserDetails userContext = HeaderContext.getUserDetails();
        if (userContext != null) {
            LOGGER.info("user details -> userId: {}, scope: {}", userContext.getUserId(),
                userContext.getScopeAsString());
        } else {
            LOGGER.info("User details are null");
        }
        LOGGER.info("Received API version:{}  received deviceID:{}", apiVersion, deviceId);
        return service.getConfig(deviceId, Long.parseLong(since));
    }

    /**
     * Retrieves the configuration for a specific device using device id and since
     *
     * @param deviceId deviceId.
     * @param since since.
     * @return configuration list.
     */
    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/devices/{deviceId}/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "GET /devices/{deviceId}/configuration",
            description = "Fetch configuration request sent by Device-Message",
            responses = { @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Configuration.class))))})
    @SecurityRequirement(name = "JwtAuthValidator", scopes = {"dongle,hu,tcu,Dongle,dashcam"})
    public List<Map> getConfiguration(@ValidDeviceId @PathVariable("deviceId") String deviceId,
                                      @RequestParam(value = "since", required = true) @Valid @Pattern(
                                              message = "Invalid Timestamp", regexp = "[0-9]+") String since) {
        UserDetails userContext = HeaderContext.getUserDetails();
        if (userContext != null) {
            LOGGER.info("user details -> userId: {}, scope: {}", userContext.getUserId(),
                    userContext.getScopeAsString());
        } else {
            LOGGER.info("User details are null");
        }
        LOGGER.info("Received getConfiguration deviceID:{}", deviceId);
        return service.getConfig(deviceId, Long.parseLong(since));
    }
}