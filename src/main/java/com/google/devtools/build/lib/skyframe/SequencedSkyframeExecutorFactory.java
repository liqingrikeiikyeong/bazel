// Copyright 2014 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.skyframe;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.blaze.BlazeDirectories;
import com.google.devtools.build.lib.events.Reporter;
import com.google.devtools.build.lib.packages.PackageFactory;
import com.google.devtools.build.lib.packages.Preprocessor.Factory.Supplier;
import com.google.devtools.build.lib.util.Clock;
import com.google.devtools.build.lib.util.io.TimestampGranularityMonitor;
import com.google.devtools.build.lib.vfs.PathFragment;
import com.google.devtools.build.lib.view.WorkspaceStatusAction.Factory;
import com.google.devtools.build.lib.view.buildinfo.BuildInfoFactory;

/**
 * A factory of SkyframeExecutors that returns SequencedSkyframeExecutor.
 */
public class SequencedSkyframeExecutorFactory implements SkyframeExecutorFactory {

  @Override
  public SkyframeExecutor create(Reporter reporter, PackageFactory pkgFactory,
      boolean skyframeBuild, TimestampGranularityMonitor tsgm, BlazeDirectories directories,
      Factory workspaceStatusActionFactory, ImmutableList<BuildInfoFactory> buildInfoFactories,
      Iterable<? extends DiffAwareness.Factory> diffAwarenessFactories,
      Predicate<PathFragment> allowedMissingInputs, Supplier preprocessorFactorySupplier,
      Clock clock) {
    return new SequencedSkyframeExecutor(reporter, pkgFactory, skyframeBuild, tsgm, directories,
        workspaceStatusActionFactory, buildInfoFactories, diffAwarenessFactories,
        allowedMissingInputs, preprocessorFactorySupplier, clock);
  }
}