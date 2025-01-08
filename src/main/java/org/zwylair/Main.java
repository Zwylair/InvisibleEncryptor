package org.zwylair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        String action = args[0].toLowerCase();
        String filePath = args[1];

        if (!action.equals("encode") && !action.equals("decode")) {
            System.out.println("Invalid action: " + action);
            printUsage();
            return;
        }

        Path inputPath = Paths.get(filePath);
        if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
            System.out.println("File does not exist or is not a regular file: " + filePath);
            return;
        }

        try {
            String content = new String(Files.readAllBytes(inputPath));
            String result;

            if (action.equals("encode")) {
                result = InvisibleEncryptor.encode(content);
            } else {
                result = InvisibleEncryptor.decode(content);
            }

            String outputFilePath = getOutputFilePath(filePath, action);
            Files.write(Paths.get(outputFilePath), result.getBytes());

            System.out.println("Operation completed successfully. Output saved to: " + outputFilePath);
        } catch (IOException e) {
            if (e instanceof java.nio.file.AccessDeniedException) {
                System.out.println("Access denied to file: " + filePath);
            } else {
                System.out.println("An error occurred while processing the file: " + e.getMessage());
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java Main <action> <file_path>");
        System.out.println("<action>: encode or decode");
        System.out.println("<file_path>: Path to the file to encode or decode");
    }

    private static String getOutputFilePath(String originalFilePath, String action) {
        int extensionIndex = originalFilePath.lastIndexOf('.');
        String baseName = extensionIndex == -1 ? originalFilePath : originalFilePath.substring(0, extensionIndex);
        String extension = extensionIndex == -1 ? "" : originalFilePath.substring(extensionIndex);

        return baseName + (action.equals("encode") ? "-encoded" : "-decoded") + extension;
    }
}
