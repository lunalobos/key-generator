/*
 * Copyright 2026 Miguel Angel Luna Lobos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.lunalobos

import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

fun main() {
    val keys = keyPair()
    val publicPem = formatToPem(keys.public.encoded, "PUBLIC KEY")
    println(publicPem)
    val privatePem = formatToPem(keys.private.encoded, "PRIVATE KEY")
    println(privatePem)
}

fun keyPair(): KeyPair {
    return KeyPairGenerator.getInstance("RSA").apply {
        initialize(2048)
    }.generateKeyPair()
}

fun formatToPem(encodedKey: ByteArray, type: String): String {
    val base64Key = Base64.getMimeEncoder(64, "\n".toByteArray()).encodeToString(encodedKey)
    return "-----BEGIN $type-----\n$base64Key\n-----END $type-----"
}

fun pemToPublicKey(pem: String): PublicKey {
    val cleanPem = pem
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replace("\\s".toRegex(), "")
    val decoded = Base64.getDecoder().decode(cleanPem)
    val spec = X509EncodedKeySpec(decoded)
    return KeyFactory.getInstance("RSA").generatePublic(spec)
}

fun pemToPrivateKey(pem: String): PrivateKey {
    val cleanPem = pem
        .replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .replace("\\s".toRegex(), "")
    val decoded = Base64.getDecoder().decode(cleanPem)
    val spec = PKCS8EncodedKeySpec(decoded)
    return KeyFactory.getInstance("RSA").generatePrivate(spec)
}