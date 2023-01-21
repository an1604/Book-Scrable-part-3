package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Objects;

public class BloomFilter {
        private  BitSet bitSet;
        private ArrayList<MessageDigest> md;

        public BloomFilter(int k, String... algs) {
            this.bitSet = new BitSet(k);
            this.md=new ArrayList<>();
            for (String s : algs) {
                try {
                    this.md.add(MessageDigest.getInstance(s));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException();
                }
            }
        }

        public void add(String s) {
            for (MessageDigest m : this.md) {
                BigInteger integer = new BigInteger(m.digest(s.getBytes()));
                this.bitSet.set(Math.abs(integer.intValue()) % this.bitSet.size(), true);
            }
        }

        public boolean contains(String s) {
            for (MessageDigest m : this.md) {
                BigInteger integer = new BigInteger(m.digest(s.getBytes()));
                if (!this.bitSet.get(Math.abs(integer.intValue()) % this.bitSet.size())) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < this.bitSet.length(); i++) {
                s.append(this.bitSet.get(i) ? 1 : 0);
            }
            return s.toString();
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloomFilter that = (BloomFilter) o;
        return Objects.equals(bitSet, that.bitSet) && Objects.equals(md, that.md);
    }

}

