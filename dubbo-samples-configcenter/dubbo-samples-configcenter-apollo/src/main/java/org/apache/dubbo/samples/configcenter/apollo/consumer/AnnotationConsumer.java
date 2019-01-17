/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.apache.dubbo.samples.configcenter.apollo.consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * CallbackConsumer
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.configcenter.apollo.consumer")
public class AnnotationConsumer implements CommandLineRunner {

    @Autowired
    private AnnotationAction action;

    public static void main(String[] args) {
        new SpringApplicationBuilder(AnnotationConsumer.class).web(false).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Please input any key to test. Input timeout to simulate 5 seconds of latency. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).readLine();
            if (input == null || input.length() == 0) {
                continue;
            }
            input = input.trim();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            try {
                System.out.println(action.doSayHello(input));
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
}
