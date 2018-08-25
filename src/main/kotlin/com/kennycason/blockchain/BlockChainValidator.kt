package com.kennycason.blockchain

import com.kennycason.blockchain.hash.Sha256Hasher

object BlockChainValidator {
    fun isValid(blockChain: BlockChain): Boolean {
        if (blockChain.length() == 1) { return true }

        for (i in (0 until blockChain.length() - 1)) {
            if (!isValid(blockChain.get(i + 1), blockChain.get(i))) {
                return false
            }
        }
        return true
    }

    fun isValid(newBlock: Block, oldBlock: Block): Boolean {
        if (oldBlock.index + 1 != newBlock.index) { return false }
        if (oldBlock.hash != newBlock.previousHash) { return false }
        if (Sha256Hasher.hash(newBlock) != newBlock.hash) { return false }

        return true
    }
}