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
package org.apache.streampipes.node.controller.container.management.relay.bridges;

import org.apache.streampipes.messaging.jms.ActiveMQConsumer;
import org.apache.streampipes.messaging.kafka.SpKafkaProducer;
import org.apache.streampipes.model.grounding.JmsTransportProtocol;
import org.apache.streampipes.model.grounding.KafkaTransportProtocol;

public class JmsToKafkaBridge extends MultiBrokerBridge<JmsTransportProtocol, KafkaTransportProtocol> {

    public JmsToKafkaBridge(JmsTransportProtocol sourceProtocol, KafkaTransportProtocol targetProtocol,
                            String relayStrategy) {
        super(sourceProtocol, targetProtocol, relayStrategy, ActiveMQConsumer::new, SpKafkaProducer::new,
                JmsTransportProtocol.class,
                KafkaTransportProtocol.class);
    }

    @Override
    protected void modifyProtocolForDebugging() {
        this.sourceProtocol.setBrokerHostname("localhost");
        this.targetProtocol.setBrokerHostname("localhost");
        this.targetProtocol.setZookeeperHost("localhost");
        this.targetProtocol.setKafkaPort(9094);
    }
}
