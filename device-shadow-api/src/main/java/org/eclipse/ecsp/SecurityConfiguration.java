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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * SecurityConfiguration class is responsible for configuring the security settings
 * of the application. It defines the security filter chain to manage HTTP security.
 *
 * <p>This configuration allows all incoming HTTP requests without any restrictions.
 * It uses Spring Security's {@link HttpSecurity} to define the security rules and
 * builds a {@link SecurityFilterChain} instance.
 *
 * <p>Key Features:
 * <ul>
 *   <li>Permits all incoming HTTP requests.</li>
 *   <li>Provides a customizable security filter chain.</li>
 * </ul>
 *
 * <p>Usage:
 * <pre>
 * &#64;Configuration
 * public class SecurityConfiguration {
 *     &#64;Bean
 *     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 *         // Security configuration logic
 *     }
 * }
 * </pre>
 *
 * @see HttpSecurity
 * @see SecurityFilterChain
 */
@Configuration
public class SecurityConfiguration {

    
    /**
     * Configures the security filter chain for the application.
     *
     * <p>This method sets up the HTTP security configuration to allow all requests
     * without any restrictions. It uses the {@link HttpSecurity} object to define
     * the security rules and builds the filter chain.
     *
     * @param http the {@link HttpSecurity} object used to configure security settings
     * @return the configured {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz ->
                authz.anyRequest().permitAll()
        );
        return http.build();
    }
}