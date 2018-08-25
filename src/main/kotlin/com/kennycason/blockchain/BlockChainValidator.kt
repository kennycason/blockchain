package com.kennycason.blockchain

import com.kennycason.blockchain.hash.Sha256Hasher

object BlockChainValidator {
    fun isValid(blockChain: BlockChain): Boolean {
        // a blockchain only containing the genesis block is valid by definition
        if (blockChain.length() == 1) { return true }

        // assert full history is valid
        for (i in (0 until blockChain.length() - 1)) {
            if (!isValid(blockChain.get(i + 1), blockChain.get(i))) {
                return false
            }
        }
        return true
    }

    fun isValid(newBlock: Block, oldBlock: Block): Boolean {
        // assert indices are sequential
        if (oldBlock.index + 1 != newBlock.index) { return false }
        // assert hashes are linked correctly
        if (oldBlock.hash != newBlock.previousHash) { return false }
        // assert the hashes themselves are correct.
        // this check is important to ensure our data within the block wasn't altered
        if (Sha256Hasher.hash(newBlock) != newBlock.hash) { return false }

        return true
    }
}