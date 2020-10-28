package com.dandandog.framework.mapstruct.model;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author JohnnyLiu
 */
@Slf4j
public final class MapperUrl implements Serializable, Comparable<String>, CharSequence {

    private final char[] value;


    public MapperUrl() {
        value = new char[0];
    }

    public MapperUrl(String str) {
        this.value = str.toCharArray();
    }

    public MapperUrl(char[] value) {
        this.value = value;
    }

    public MapperUrl(byte[] bytes) {
        this.value = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            this.value[i] = (char) bytes[i];
        }
    }

    public MapperUrl(char[] value, int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= value.length) {
                this.value = new char[0];
                return;
            }
        }
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        this.value = Arrays.copyOfRange(value, offset, count);
    }


    @Override
    public int length() {
        return value.length;
    }

    @Override
    public char charAt(int index) {
        if (index > this.value.length || index < 0) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return this.value[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > value.length) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (start > end) {
            throw new StringIndexOutOfBoundsException("start index is bigger than end index.");
        }

        return new MapperUrl(Arrays.copyOfRange(value, start, end));
    }


    @Override
    public int compareTo(String o) {
        return 0;
    }

    public int compareTo(MapperUrl anotherString) {
        if (this == anotherString) {
            return 0;
        }

        return 0;
    }

    @Override
    public String toString() {
        return new String(this.value);
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof MapperUrl) {
            MapperUrl anString = (MapperUrl) anObject;
            if (anString.length() == value.length) {
                char[] v1 = anString.value;
                char[] v2 = value;
                int n = v1.length;
                while (n-- != 0) {
                    if (v1[n] != v2[n]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
