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
package org.apache.streampipes.connect.adapter.preprocessing.elements;

import org.apache.streampipes.connect.api.IAdapterPipelineElement;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DebugAdapterSink implements IAdapterPipelineElement {
  private final BlockingQueue<Map<String, Object>> events;

  public DebugAdapterSink() {
    this.events = new LinkedBlockingDeque<>();
  }

  @Override
  public Map<String, Object> process(Map<String, Object> event) {
    events.add(event);
    return event;
  }

  public Map<String, Object> takeEvent() throws InterruptedException {
    return takeEvent(5, TimeUnit.SECONDS);
  }

  public Map<String, Object> takeEvent(long timeout, TimeUnit unit) throws InterruptedException {
    return events.poll(timeout, unit);
  }
}
