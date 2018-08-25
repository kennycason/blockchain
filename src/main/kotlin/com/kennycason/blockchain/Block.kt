package com.kennycason.blockchain

import com.kennycason.blockchain.data.Record
import com.kennycason.blockchain.hash.Sha256Hasher

class Block(
        val index: Int,
        val timestamp: Long,
        val record: Record,
        val previousHash: String) {

    val hash: String = Sha256Hasher.hash(this)

    override fun toString(): String {
        return "Block(index=$index, timestamp=$timestamp, record=$record, previousHash='$previousHash', hash='$hash')"
    }

}