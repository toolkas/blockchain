package com.blogspot.toolkas.blockchain;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Example {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BlockChain blockChain = new BlockChain();
        blockChain.add("AAA".getBytes("UTF-8"));
        blockChain.add("BBB".getBytes("UTF-8"));
        blockChain.add("CCC".getBytes("UTF-8"));
        System.out.println(blockChain.checkValidity());
    }
}
