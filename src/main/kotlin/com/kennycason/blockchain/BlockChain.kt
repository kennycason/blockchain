package com.kennycason.blockchain

import com.kennycason.blockchain.data.Record
import org.apache.commons.codec.digest.DigestUtils

class BlockChain(private val chain: MutableList<Block> = mutableListOf()) {

    init {
        if (chain.isEmpty()) {
            createGenesisBlock()
        }
    }

    fun length() = chain.size

    fun last() = chain.last()

    fun get(i: Int) = chain[i]

    fun getChain() = chain

    fun maybeReplace(newBlockChain: BlockChain) {
        if (newBlockChain.length() > length()) {
            chain.clear()
            chain.addAll(newBlockChain.chain)
        }
    }

    fun add(record: Record) {
        // wrap our data in a Block
        val block = generate(last(), record)

        // validate the new block we are about to add is consistent with the previous block
        if (!isValid(block, last())) {
            throw RuntimeException("Invalid Block!")
        }
        // finally add the new block
        chain.add(block)

        // for extra validation, re-validate the entire chain
        if (!isValid()) {
            throw RuntimeException("Invalid BlockChain!")
        }
    }

    private fun hash(block: Block) = DigestUtils.sha256Hex(
            block.index.toString() +
                    block.timestamp.toString() +
                    block.record.hashCode() +
                    block.previousHash)!!

    private fun isValid(): Boolean {
        // a blockchain only containing the genesis block is valid by definition
        if (length() == 1) { return true }

        // assert full history is valid
        for (i in (0 until length() - 1)) {
            if (!isValid(get(i + 1), get(i))) {
                return false
            }
        }
        return true
    }

    private fun isValid(newBlock: Block, oldBlock: Block): Boolean {
        // assert indices are sequential
        if (oldBlock.index + 1 != newBlock.index) { return false }
        // assert hashes are linked correctly
        if (oldBlock.hash != newBlock.previousHash) { return false }
        // assert the hashes themselves are correct.
        // this check is important to ensure our data within the block wasn't altered
        if (hash(newBlock) != newBlock.hash) { return false }

        return true
    }


    private fun generate(block: Block, record: Record): Block {
        return Block(
                index = block.index + 1,
                timestamp = System.currentTimeMillis(),
                record = record,
                previousHash = block.hash)
    }

    private fun createGenesisBlock() {
        chain.add(Block(
                index = 0,
                timestamp = 0,
                record = Record(weight = 0.0, date = 0L),
                previousHash = ""
        ))
    }

}
