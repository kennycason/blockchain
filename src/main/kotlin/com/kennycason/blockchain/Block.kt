package com.kennycason.blockchain

import org.apache.commons.codec.digest.DigestUtils

class Block(
        val index: Int,
        val timestamp: Long,
        val record: Record,
        val previousHash: String) {

    val hash: String = hash(this)

    companion object {
        private fun hash(block: Block) = DigestUtils.sha256Hex(
                block.index.toString() +
                        block.timestamp.toString() +
                        block.record.hashCode() +
                        block.previousHash)!!
    }

}