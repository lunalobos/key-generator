# RSA Key Generator

A lightweight, secure, and local utility to generate RSA key pairs without relying on third-party web services.

This project ensures **absolute privacy**:

* **Local Execution**: Keys are generated within your JVM.

* **No Networking**: No data is sent to external APIs or servers.

* **Standard Formats**: Outputs keys in industry-standard PEM (Base64) format, ready to be used in SSH, JWT, or web servers.

### Prerequisites

Java JDK 21.

## How to Use

1. **Clone the repository**:
    ```bash
    git clone https://github.com/lunalobos/key-generator.git
    cd key-generator
    ```

2. **Run the generator:**
    Using the included Gradle wrapper, execute the following command:
    ```bash
    ./gradlew run
    ```

3. **Get your keys**:
    The application will output the Public and Private keys directly to your terminal in the following format:
    ```Plaintext
    -----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA...
    -----END PUBLIC KEY-----

    -----BEGIN PRIVATE KEY-----
    MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEA...
    -----END PRIVATE KEY-----
    ```

## Technical Details

* Algorithm: RSA

* Key Size: 2048-bit (Standard for most modern security requirements).

* Encoding: 

  * Public Key: X.509

  * Private Key: PKCS#8

* Output: PEM (Privacy-Enhanced Mail) with 64-character line wrapping.