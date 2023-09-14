package ru.geekbrains.lesson5;

import java.io.*;

public class Backup {
    public static void main(String[] args) {
        try {
            copyFolder(".", "backup");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void copyFile(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
    public static void copyFolder(String source, String destination) throws IOException {
        File src = new File(source);
        File dst = new File(destination);

        if (src.isDirectory()) {
            if (!dst.exists()) {
                dst.mkdir();
            }

            String[] files = src.list();

            boolean isDstFolder = dst.getCanonicalPath().startsWith(src.getCanonicalPath());

            if (files != null) {
                for (String file : files) {
                    if (isDstFolder && file.equals(dst.getName())) {
                        continue;
                    }
                    copyFolder(source + File.separator + file, destination + File.separator + file);
                }
            }
        } else {
            copyFile(src, dst);
        }
    }}
