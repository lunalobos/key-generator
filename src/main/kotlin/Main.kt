package io.github.lunalobos

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val keys = keyPair()
    val publicPem = formatToPem(keys.public.encoded, "PUBLIC KEY")
    println(publicPem)
    val privatePem = formatToPem(keys.private.encoded, "PRIVATE KEY")
    println(privatePem)
}

fun keyPair() : KeyPair {
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
        .replace("\\s".toRegex(), "") // Quita espacios y saltos de línea
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