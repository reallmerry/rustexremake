/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public final class Lister {
    private static final ArchiveStreamFactory FACTORY = ArchiveStreamFactory.DEFAULT;

    private static ArchiveInputStream createArchiveInputStream(String[] args, InputStream fis) throws ArchiveException {
        if (args.length > 1) {
            return FACTORY.createArchiveInputStream(args[1], fis);
        }
        return FACTORY.createArchiveInputStream(fis);
    }

    private static String detectFormat(File f2) throws ArchiveException, IOException {
        try (BufferedInputStream fis = new BufferedInputStream(Files.newInputStream(f2.toPath(), new OpenOption[0]));){
            String string = ArchiveStreamFactory.detect(fis);
            return string;
        }
    }

    private static void list7z(File f2) throws IOException {
        try (SevenZFile z2 = new SevenZFile(f2);){
            SevenZArchiveEntry ae;
            System.out.println("Created " + z2);
            while ((ae = z2.getNextEntry()) != null) {
                String name = ae.getName() == null ? z2.getDefaultName() + " (entry name was null)" : ae.getName();
                System.out.println(name);
            }
        }
    }

    private static void listStream(File f2, String[] args) throws ArchiveException, IOException {
        try (BufferedInputStream fis = new BufferedInputStream(Files.newInputStream(f2.toPath(), new OpenOption[0]));
             ArchiveInputStream ais = Lister.createArchiveInputStream(args, fis);){
            ArchiveEntry ae;
            System.out.println("Created " + ais.toString());
            while ((ae = ais.getNextEntry()) != null) {
                System.out.println(ae.getName());
            }
        }
    }

    private static void listZipUsingTarFile(File f2) throws IOException {
        try (TarFile t2 = new TarFile(f2);){
            System.out.println("Created " + t2);
            t2.getEntries().forEach(en -> System.out.println(en.getName()));
        }
    }

    private static void listZipUsingZipFile(File f2) throws IOException {
        try (ZipFile z2 = new ZipFile(f2);){
            System.out.println("Created " + z2);
            Enumeration<ZipArchiveEntry> en = z2.getEntries();
            while (en.hasMoreElements()) {
                System.out.println(en.nextElement().getName());
            }
        }
    }

    public static void main(String[] args) throws ArchiveException, IOException {
        String format;
        if (args.length == 0) {
            Lister.usage();
            return;
        }
        System.out.println("Analysing " + args[0]);
        File f2 = new File(args[0]);
        if (!f2.isFile()) {
            System.err.println(f2 + " doesn't exist or is a directory");
        }
        String string = format = args.length > 1 ? args[1] : Lister.detectFormat(f2);
        if ("7z".equalsIgnoreCase(format)) {
            Lister.list7z(f2);
        } else if ("zipfile".equals(format)) {
            Lister.listZipUsingZipFile(f2);
        } else if ("tarfile".equals(format)) {
            Lister.listZipUsingTarFile(f2);
        } else {
            Lister.listStream(f2, args);
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [archive-type]\n");
        System.out.println("The magic archive-type 'zipfile' prefers ZipFile over ZipArchiveInputStream");
        System.out.println("The magic archive-type 'tarfile' prefers TarFile over TarArchiveInputStream");
    }
}

