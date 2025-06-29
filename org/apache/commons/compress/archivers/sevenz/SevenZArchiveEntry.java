/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration;
import org.apache.commons.compress.utils.TimeUtils;

public class SevenZArchiveEntry
implements ArchiveEntry {
    static final SevenZArchiveEntry[] EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY = new SevenZArchiveEntry[0];
    private String name;
    private boolean hasStream;
    private boolean isDirectory;
    private boolean isAntiItem;
    private boolean hasCreationDate;
    private boolean hasLastModifiedDate;
    private boolean hasAccessDate;
    private FileTime creationDate;
    private FileTime lastModifiedDate;
    private FileTime accessDate;
    private boolean hasWindowsAttributes;
    private int windowsAttributes;
    private boolean hasCrc;
    private long crc;
    private long compressedCrc;
    private long size;
    private long compressedSize;
    private Iterable<? extends SevenZMethodConfiguration> contentMethods;

    @Deprecated
    public static long javaTimeToNtfsTime(Date date) {
        return TimeUtils.toNtfsTime(date);
    }

    @Deprecated
    public static Date ntfsTimeToJavaTime(long ntfsTime) {
        return TimeUtils.ntfsTimeToDate(ntfsTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        SevenZArchiveEntry other = (SevenZArchiveEntry)obj;
        return Objects.equals(this.name, other.name) && this.hasStream == other.hasStream && this.isDirectory == other.isDirectory && this.isAntiItem == other.isAntiItem && this.hasCreationDate == other.hasCreationDate && this.hasLastModifiedDate == other.hasLastModifiedDate && this.hasAccessDate == other.hasAccessDate && Objects.equals(this.creationDate, other.creationDate) && Objects.equals(this.lastModifiedDate, other.lastModifiedDate) && Objects.equals(this.accessDate, other.accessDate) && this.hasWindowsAttributes == other.hasWindowsAttributes && this.windowsAttributes == other.windowsAttributes && this.hasCrc == other.hasCrc && this.crc == other.crc && this.compressedCrc == other.compressedCrc && this.size == other.size && this.compressedSize == other.compressedSize && this.equalSevenZMethods(this.contentMethods, other.contentMethods);
    }

    private boolean equalSevenZMethods(Iterable<? extends SevenZMethodConfiguration> c1, Iterable<? extends SevenZMethodConfiguration> c2) {
        if (c1 == null) {
            return c2 == null;
        }
        if (c2 == null) {
            return false;
        }
        Iterator<? extends SevenZMethodConfiguration> i2 = c2.iterator();
        for (SevenZMethodConfiguration sevenZMethodConfiguration : c1) {
            if (!i2.hasNext()) {
                return false;
            }
            if (sevenZMethodConfiguration.equals(i2.next())) continue;
            return false;
        }
        return !i2.hasNext();
    }

    public Date getAccessDate() {
        return TimeUtils.toDate(this.getAccessTime());
    }

    public FileTime getAccessTime() {
        if (this.hasAccessDate) {
            return this.accessDate;
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    @Deprecated
    int getCompressedCrc() {
        return (int)this.compressedCrc;
    }

    long getCompressedCrcValue() {
        return this.compressedCrc;
    }

    long getCompressedSize() {
        return this.compressedSize;
    }

    public Iterable<? extends SevenZMethodConfiguration> getContentMethods() {
        return this.contentMethods;
    }

    @Deprecated
    public int getCrc() {
        return (int)this.crc;
    }

    public long getCrcValue() {
        return this.crc;
    }

    public Date getCreationDate() {
        return TimeUtils.toDate(this.getCreationTime());
    }

    public FileTime getCreationTime() {
        if (this.hasCreationDate) {
            return this.creationDate;
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    public boolean getHasAccessDate() {
        return this.hasAccessDate;
    }

    public boolean getHasCrc() {
        return this.hasCrc;
    }

    public boolean getHasCreationDate() {
        return this.hasCreationDate;
    }

    public boolean getHasLastModifiedDate() {
        return this.hasLastModifiedDate;
    }

    public boolean getHasWindowsAttributes() {
        return this.hasWindowsAttributes;
    }

    @Override
    public Date getLastModifiedDate() {
        return TimeUtils.toDate(this.getLastModifiedTime());
    }

    public FileTime getLastModifiedTime() {
        if (this.hasLastModifiedDate) {
            return this.lastModifiedDate;
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    public int getWindowsAttributes() {
        return this.windowsAttributes;
    }

    public int hashCode() {
        String n2 = this.getName();
        return n2 == null ? 0 : n2.hashCode();
    }

    public boolean hasStream() {
        return this.hasStream;
    }

    public boolean isAntiItem() {
        return this.isAntiItem;
    }

    @Override
    public boolean isDirectory() {
        return this.isDirectory;
    }

    public void setAccessDate(Date accessDate) {
        this.setAccessTime(TimeUtils.toFileTime(accessDate));
    }

    public void setAccessDate(long ntfsAccessDate) {
        this.accessDate = TimeUtils.ntfsTimeToFileTime(ntfsAccessDate);
    }

    public void setAccessTime(FileTime time) {
        boolean bl = this.hasAccessDate = time != null;
        if (this.hasAccessDate) {
            this.accessDate = time;
        }
    }

    public void setAntiItem(boolean isAntiItem) {
        this.isAntiItem = isAntiItem;
    }

    @Deprecated
    void setCompressedCrc(int crc) {
        this.compressedCrc = crc;
    }

    void setCompressedCrcValue(long crc) {
        this.compressedCrc = crc;
    }

    void setCompressedSize(long size) {
        this.compressedSize = size;
    }

    public void setContentMethods(Iterable<? extends SevenZMethodConfiguration> methods) {
        if (methods != null) {
            LinkedList l2 = new LinkedList();
            methods.forEach(l2::addLast);
            this.contentMethods = Collections.unmodifiableList(l2);
        } else {
            this.contentMethods = null;
        }
    }

    public void setContentMethods(SevenZMethodConfiguration ... methods) {
        this.setContentMethods(Arrays.asList(methods));
    }

    @Deprecated
    public void setCrc(int crc) {
        this.crc = crc;
    }

    public void setCrcValue(long crc) {
        this.crc = crc;
    }

    public void setCreationDate(Date creationDate) {
        this.setCreationTime(TimeUtils.toFileTime(creationDate));
    }

    public void setCreationDate(long ntfsCreationDate) {
        this.creationDate = TimeUtils.ntfsTimeToFileTime(ntfsCreationDate);
    }

    public void setCreationTime(FileTime time) {
        boolean bl = this.hasCreationDate = time != null;
        if (this.hasCreationDate) {
            this.creationDate = time;
        }
    }

    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public void setHasAccessDate(boolean hasAcessDate) {
        this.hasAccessDate = hasAcessDate;
    }

    public void setHasCrc(boolean hasCrc) {
        this.hasCrc = hasCrc;
    }

    public void setHasCreationDate(boolean hasCreationDate) {
        this.hasCreationDate = hasCreationDate;
    }

    public void setHasLastModifiedDate(boolean hasLastModifiedDate) {
        this.hasLastModifiedDate = hasLastModifiedDate;
    }

    public void setHasStream(boolean hasStream) {
        this.hasStream = hasStream;
    }

    public void setHasWindowsAttributes(boolean hasWindowsAttributes) {
        this.hasWindowsAttributes = hasWindowsAttributes;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.setLastModifiedTime(TimeUtils.toFileTime(lastModifiedDate));
    }

    public void setLastModifiedDate(long ntfsLastModifiedDate) {
        this.lastModifiedDate = TimeUtils.ntfsTimeToFileTime(ntfsLastModifiedDate);
    }

    public void setLastModifiedTime(FileTime time) {
        boolean bl = this.hasLastModifiedDate = time != null;
        if (this.hasLastModifiedDate) {
            this.lastModifiedDate = time;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setWindowsAttributes(int windowsAttributes) {
        this.windowsAttributes = windowsAttributes;
    }
}

