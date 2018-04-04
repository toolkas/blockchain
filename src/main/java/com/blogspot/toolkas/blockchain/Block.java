package com.blogspot.toolkas.blockchain;

public class Block {
    /**
     * Данные блока.
     */
    private final byte[] data;
    /**
     * Хэш блока.
     */
    private final byte[] hash;
    /**
     * Хэш предыдущего блока.
     */
    private final byte[] prevHash;

    /**
     * Время создания блока.
     */
    private final long created;

    public Block(byte[] data, byte[] hash, byte[] prevHash, long created) {
        this.data = data;
        this.hash = hash;
        this.prevHash = prevHash;
        this.created = created;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getPrevHash() {
        return prevHash;
    }

    public long getCreated() {
        return created;
    }
}
