package com.blogspot.toolkas.blockchain;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlockChain {
    private final List<Block> blocks = new ArrayList<Block>();

    public BlockChain() {
        blocks.add(new Block(null, new byte[]{1, 2, 3}, null, System.currentTimeMillis()));
    }

    public void add(byte[] data) throws IOException, NoSuchAlgorithmException {
        Objects.requireNonNull(data);

        long now = System.currentTimeMillis();
        byte[] fullData = getFullData(data, now);
        byte[] hash = hash(fullData);

        Block last = blocks.get(blocks.size() - 1);
        Block block = new Block(data, hash, last.getHash(), now);
        blocks.add(block);
    }

    private byte[] getFullData(byte[] data, long now) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(DataOutputStream output = new DataOutputStream(baos)) {
            output.write(data);
            output.writeLong(now);
        }

        return baos.toByteArray();
    }

    public boolean checkValidity() throws NoSuchAlgorithmException, IOException {
        for (int i = 1; i < blocks.size(); i++) {
            Block prev = blocks.get(i - 1);
            Block block = blocks.get(i);

            byte[] newHash = hash(block);
            if (!Arrays.equals(newHash, block.getHash())) {
                return false;
            }

            if (!Arrays.equals(prev.getHash(), block.getPrevHash())) {
                return false;
            }
        }
        return true;
    }

    private byte[] hash(Block block) throws NoSuchAlgorithmException, IOException {
        byte[] fullData = getFullData(block.getData(), block.getCreated());
        return hash(fullData);
    }

    private byte[] hash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }
}
