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

package com.google.devtools.build.lib.rules.cpp;

import static com.google.devtools.build.lib.packages.ImplicitOutputsFunction.fromTemplates;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.ALWAYS_LINK_LIBRARY;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.ALWAYS_LINK_PIC_LIBRARY;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.ARCHIVE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.ASSEMBLER_WITH_C_PREPROCESSOR;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.CPP_HEADER;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.CPP_SOURCE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.C_SOURCE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.OBJECT_FILE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.PIC_ARCHIVE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.PIC_OBJECT_FILE;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.SHARED_LIBRARY;
import static com.google.devtools.build.lib.rules.cpp.CppFileTypes.VERSIONED_SHARED_LIBRARY;

import com.google.devtools.build.lib.packages.ImplicitOutputsFunction.SafeImplicitOutputsFunction;
import com.google.devtools.build.lib.rules.test.InstrumentedFilesCollector.InstrumentationSpec;
import com.google.devtools.build.lib.util.FileTypeSet;
import com.google.devtools.build.lib.view.LanguageDependentFragment.LibraryLanguage;

/**
 * Rule class definitions for C++ rules.
 */
public class CppRuleClasses {
  // Artifacts of these types are discarded from the 'hdrs' attribute in cc rules
  static final FileTypeSet DISALLOWED_HDRS_FILES = FileTypeSet.of(
      ARCHIVE,
      PIC_ARCHIVE,
      ALWAYS_LINK_LIBRARY,
      ALWAYS_LINK_PIC_LIBRARY,
      SHARED_LIBRARY,
      VERSIONED_SHARED_LIBRARY,
      OBJECT_FILE,
      PIC_OBJECT_FILE);

  /**
   * The set of instrumented source file types; keep this in sync with the list above. Note that
   * extension-less header files cannot currently be declared, so we cannot collect coverage for
   * those.
   */
  static final InstrumentationSpec INSTRUMENTATION_SPEC = new InstrumentationSpec(
      FileTypeSet.of(CPP_SOURCE, C_SOURCE, CPP_HEADER, ASSEMBLER_WITH_C_PREPROCESSOR),
      "srcs", "deps", "data", "hdrs", "implements", "implementation");

  public static final LibraryLanguage LANGUAGE = new LibraryLanguage("C++");

  /**
   * Implicit outputs for cc_binary rules.
   */
  public static final SafeImplicitOutputsFunction CC_BINARY_STRIPPED =
      fromTemplates("%{name}.stripped");


  // Used for requesting dwp "debug packages".
  public static final SafeImplicitOutputsFunction CC_BINARY_DEBUG_PACKAGE =
      fromTemplates("%{name}.dwp");


  /**
   * Path of the build_interface_so script in the Blaze binary.
   */
  public static final String BUILD_INTERFACE_SO = "build_interface_so";

  /**
   * A string constant for the layering_check feature.
   */
  public static final String LAYERING_CHECK = "layering_check";
}