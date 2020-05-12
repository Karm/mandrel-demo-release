/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.compiler.hotspot.management;

import org.graalvm.nativeimage.Platform;
import org.graalvm.nativeimage.Platforms;

/**
 * Native methods linked to SVM entry points.
 */
@Platforms(Platform.HOSTED_ONLY.class)
final class HotSpotToSVMCalls {

    /**
     * Used to synchronize libgraal isolates waiting for registration of the native methods in this
     * class. The field is set to {@code true} from the libgraal native library when all natives in
     * this class have been registered.
     */
    private static volatile boolean nativesRegistered;

    private HotSpotToSVMCalls() {
    }

    static native long[] pollRegistrations(long isolateThreadId);

    static native void finishRegistration(long isolateThreadId, long[] svmRegistrations);

    static native String getObjectName(long isolateThreadId, long svmRegistration);

    static native byte[] getMBeanInfo(long isolateThreadId, long svmRegistration);

    static native byte[] getAttributes(long isolateThreadId, long handle, String[] attributes);

    static native byte[] setAttributes(long isolateThreadId, long handle, byte[] rawData);

    static native byte[] invoke(long isolateThreadId, long handle, String actionName, byte[] rawData, String[] signature);
}
