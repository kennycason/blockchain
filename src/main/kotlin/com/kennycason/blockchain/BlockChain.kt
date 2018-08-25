package com.kennycason.blockchain

import com.kennycason.blockchain.data.Record

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
        if (!BlockChainValidator.isValid(block, last())) {
            throw RuntimeException("Invalid Block!")
        }
        // finally add the new block
        chain.add(block)

        // for extra validation, re-validate the entire chain
        if (!BlockChainValidator.isValid(this)) {
            throw RuntimeException("Invalid BlockChain!")
        }
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

    override fun toString(): String {
        return "BlockChain(chain=${chain.joinToString(",\n")})"
    }

}
