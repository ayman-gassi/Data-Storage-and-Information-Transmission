# Blockchain Implementation in Java

This project demonstrates a simple blockchain implementation in Java. A blockchain is a distributed ledger that maintains a continuously growing list of records, called blocks, which are linked and secured using cryptography. Each block contains a cryptographic hash of the previous block, a timestamp, and transaction data.

## Features

- **Block Creation**: Each block contains an index, data, a timestamp, a hash of the current block, and a hash of the previous block.
- **Hash Calculation**: The hash of each block is calculated using the SHA-256 algorithm, ensuring the integrity of the block.
- **Chain Verification**: The blockchain can be verified to ensure that all blocks are correctly linked and that no tampering has occurred.

## Code Structure

### `Block.java`

This class represents a block in the blockchain. It contains the following attributes:

- `index`: The position of the block in the blockchain.
- `data`: The data stored in the block.
- `timestamp`: The time when the block was created.
- `currentHash`: The hash of the current block, calculated using the SHA-256 algorithm.
- `previousHash`: The hash of the previous block in the chain.

The class also includes methods for:

- Calculating the hash of the block (`calculateHash`).
- Verifying the integrity of the blockchain (`verifychain`).

### `Main.java`

This class demonstrates the creation of a blockchain and its verification. It performs the following steps:

1. Creates a new blockchain (an `ArrayList` of `Block` objects).
2. Adds several blocks to the blockchain.
3. Prints the contents of each block.
4. Verifies the integrity of the blockchain.
5. Attempts to tamper with the blockchain by changing the `previousHash` of a block and verifies the integrity again.


