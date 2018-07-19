/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.starter;

import java.util.concurrent.TimeUnit;
import org.apache.skywalking.oap.server.library.module.ApplicationConfiguration;
import org.apache.skywalking.oap.server.starter.config.ApplicationConfigLoader;
import org.slf4j.*;

/**
 * @author peng-yongsheng
 */
public class OAPServerStartUp {

    private static final Logger logger = LoggerFactory.getLogger(OAPServerStartUp.class);

    public static void main(String[] args) {
        ApplicationConfigLoader configLoader = new ApplicationConfigLoader();

        try {
            ApplicationConfiguration applicationConfiguration = configLoader.load();
            ModuleManagerImpl moduleManager = new ModuleManagerImpl(applicationConfiguration);
            moduleManager.start();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            System.exit(1);
        }

        logger.info("OAP server start up successful.");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}