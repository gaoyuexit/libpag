package org.libpag;

import static android.os.Environment.isExternalStorageRemovable;

import android.content.Context;
import android.os.Environment;

import org.extra.tools.LibraryLoadUtils;

import java.io.File;

/**
 * Defines methods to manage the disk cache capabilities.
 */
public class PAGDiskCache {
    /**
     * Returns the size limit of the disk cache in bytes. The default value is 1 GB.
     */
    public static native long MaxDiskSize();

    /**
     * Sets the size limit of the disk cache in bytes, which will immediately trigger the cache
     * cleanup if the disk usage exceeds the limit.
     */
    public static native void SetMaxDiskSize(long size);

    private static String GetCacheDir() {
        Context context = LibraryLoadUtils.getAppContext();
        String cacheDir = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
        return cacheDir + File.separator + "libpag";
    }

    static {
        LibraryLoadUtils.loadLibrary("pag");
    }
}