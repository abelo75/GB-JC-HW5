package ru.geekbrains.lesson5;

import java.io.File;

public class Tree {

    public static void main(String[] args) {

        print(new File("."), "", true);

    }

    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        int filesTotal = 0;
        for (File element : files) {
            if (element.isDirectory()) {
                subDirTotal++;
            } else {
                filesTotal++;
            }
        }

        int subDirCounter = 0;
        for (File item : files) {
            if (item.isDirectory()) {
                subDirCounter++;
                print(item, indent, subDirCounter == subDirTotal);
            }
        }

        int fileCounter = 0;
        for (File value : files) {
            if (!value.isDirectory()) {
                fileCounter++;
                System.out.println(indent + (fileCounter < filesTotal ? "├─" : "└─") + value.getName());
            }
        }

    }

}
