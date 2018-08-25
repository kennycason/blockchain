package com.kennycason.blockchain.hash

import com.kennycason.blockchain.Block
import org.apache.commons.codec.digest.DigestUtils

object Sha256Hasher {
    fun hash(block: Block) = DigestUtils.sha256Hex(
            block.index.toString() +
                    block.timestamp.toString() +
                    block.record.hashCode() +
                    block.previousHash)
}