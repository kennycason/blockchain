package com.kennycason.blockchain


import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.kennycason.blockchain.data.Record
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.Jetty

fun main(args: Array<String>) {
    BlockChainViewServer().start()
}

class BlockChainViewServer {
    private val blockChain = BlockChain()

    fun start() {
        embeddedServer(Jetty, 8080) {
            install(CallLogging)
            install(ContentNegotiation) {
                jackson {
                    enable(SerializationFeature.INDENT_OUTPUT)
                    propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
                }
            }
            routing {
                get("/") {
                    println(blockChain)
                    call.respond(blockChain)
                }
                post("/") {
                    val record = call.receive<Record>()
                    blockChain.add(record)

                    println(blockChain)
                    call.respond(blockChain)
                }
            }
        }.start(wait = true)
    }
}