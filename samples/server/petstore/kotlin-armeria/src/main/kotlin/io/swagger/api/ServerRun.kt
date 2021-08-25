package io.swagger.api

import com.linecorp.armeria.server.Server

fun main() {
    val sb = Server.builder()

    sb.http(8080)

    sb.annotatedService(PetService())

    val server = sb.build()
    server.start()
}