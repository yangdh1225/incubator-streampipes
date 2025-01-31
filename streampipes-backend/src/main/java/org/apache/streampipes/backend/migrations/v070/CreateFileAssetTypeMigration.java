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

package org.apache.streampipes.backend.migrations.v070;

import org.apache.streampipes.backend.migrations.Migration;
import org.apache.streampipes.commons.constants.GenericDocTypes;
import org.apache.streampipes.commons.random.UUIDGenerator;
import org.apache.streampipes.model.assets.AssetLinkType;
import org.apache.streampipes.storage.management.StorageDispatcher;

import java.io.IOException;
import java.util.List;

public class CreateFileAssetTypeMigration implements Migration {

  @Override
  public boolean shouldExecute() {
    try {
      return StorageDispatcher
          .INSTANCE
          .getNoSqlStore()
          .getGenericStorage()
          .findAll(GenericDocTypes.DOC_ASSET_LINK_TYPE)
          .stream()
          .noneMatch(al -> al.get("linkType").equals("file"));
    } catch (IOException e) {
      return true;
    }
  }

  @Override
  public void executeMigration() throws IOException {
    var fileAsset = new AssetLinkType("file", "File", "var(--color-file)", "draft", "file", List.of(), false);
    fileAsset.setId(UUIDGenerator.generateUuid());
    StorageDispatcher.INSTANCE.getNoSqlStore().getGenericStorage().create(fileAsset, AssetLinkType.class);

  }

  @Override
  public String getDescription() {
    return "Create asset type 'File'";
  }
}
