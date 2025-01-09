# InvisibleEncryptor

InvisibleEncryptor is a lightweight Java-based CLI tool for encoding and decoding strings using invisible characters. It can be used for steganography or hiding messages within text files or other textual content.

## Features
- Encode strings into a format using invisible Unicode characters.
- Decode encoded strings back into their original form.

## How It Works
InvisibleEncryptor leverages a set of invisible Unicode characters to represent byte data. These characters are mapped to numeric values and used to encode or decode strings. The encoded strings are prefixed with specific markers to identify them.

## Getting Started

### Prerequisites
- Java 8 or later.

### Installation
Clone the repository to your local machine:
```bash
git clone https://github.com/Zwylair/InvisibleEncryptor.git
```

Compile the source code using any Java build tool or IDE.

## Usage

### Encode a String
```java
String original = "Hello, world!";
String encoded = InvisibleEncryptor.encode(original);
System.out.println("Encoded: " + encoded);
```

### Decode a String
```java
String decoded = InvisibleEncryptor.decode(encoded);
System.out.println("Decoded: " + decoded);
```

### Check if a String is Encoded
```java
boolean isEncoded = InvisibleEncryptor.isEncrypted(encoded);
System.out.println("Is Encoded: " + isEncoded);
```

## Command-Line Utility
The repository includes a command-line utility for working with encoded files. Run the `Main` class with the following arguments:

```bash
java -jar InvisibleEncrytor.jar <action> <file_path>
```

### Arguments
- `<action>`: `encode` or `decode`.
- `<file_path>`: Path to the file to encode or decode.

### Example
```bash
java -jar InvisibleEncrytor.jar encode myfile.txt
```
This will encode the contents of `myfile.txt` and save the result to `myfile-encoded.txt`.

```bash
java -jar InvisibleEncrytor.jar decode myfile-encoded.txt
```
This will decode the contents of `myfile-encoded.txt` and save the result to `myfile-decoded.txt`.

## License
This project is licensed under the MIT License. See the [license file](./LICENSE) for details.

---

Enjoy using InvisibleEncryptor!

