/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.streampipes.wrapper.context;

import org.apache.streampipes.client.StreamPipesClient;
import org.apache.streampipes.container.config.ConfigExtractor;
import org.apache.streampipes.container.monitoring.SpMonitoringManager;
import org.apache.streampipes.model.runtime.SchemaInfo;
import org.apache.streampipes.model.runtime.SourceInfo;

import java.io.Serializable;
import java.util.List;

public class SpEventProcessorRuntimeContext extends SpRuntimeContext implements
    EventProcessorRuntimeContext, Serializable {

  private SchemaInfo outputSchemaInfo;
  private SourceInfo outputSourceInfo;

  public SpEventProcessorRuntimeContext(List<SourceInfo> inputSourceInfo,
                                        List<SchemaInfo> inputSchemaInfo,
                                        SourceInfo outputSourceInfo,
                                        SchemaInfo outputSchemaInfo,
                                        String correspondingUser,
                                        ConfigExtractor configExtractor,
                                        StreamPipesClient streamPipesClient,
                                        SpMonitoringManager logManager) {
    super(inputSourceInfo, inputSchemaInfo, correspondingUser, configExtractor, streamPipesClient, logManager);
    this.outputSchemaInfo = outputSchemaInfo;
    this.outputSourceInfo = outputSourceInfo;
  }

  public SpEventProcessorRuntimeContext() {
    super();
  }

  @Override
  public SchemaInfo getOutputSchemaInfo() {
    return outputSchemaInfo;
  }

  @Override
  public SourceInfo getOutputSourceInfo() {
    return outputSourceInfo;
  }

}
