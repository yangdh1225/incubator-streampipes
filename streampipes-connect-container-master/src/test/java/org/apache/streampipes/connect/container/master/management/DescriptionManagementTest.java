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

package org.apache.streampipes.connect.container.master.management;

import org.apache.streampipes.connect.adapter.AdapterRegistry;
import org.apache.streampipes.connect.adapter.format.json.arraykey.JsonFormat;
import org.apache.streampipes.connect.api.IFormat;
import org.apache.streampipes.model.connect.grounding.FormatDescription;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AdapterRegistry.class})
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "javax.management.*"})
public class DescriptionManagementTest {


  @Test
  public void getFormats() {
    Map<String, IFormat> allFormats = new HashMap<>();
    allFormats.put(JsonFormat.ID, new JsonFormat());

    PowerMockito.mockStatic(AdapterRegistry.class);
    Mockito.when(AdapterRegistry.getAllFormats())
        .thenReturn(allFormats);

    DescriptionManagement descriptionManagement = new DescriptionManagement();

    List<FormatDescription> result = descriptionManagement.getFormats();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(JsonFormat.ID, result.get(0).getAppId());
  }

}
