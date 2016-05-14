package com.demo.util;

public class BitUtils
{
    public static int getBitValue(int source, int pos)
    {
        return source >> pos - 1 & 0x1;
    }

    public static int getBitValue(long source, int pos)
    {
        long result = source >> pos - 1 & 1L;
        return (int) result;
    }

    public static int setBitValue(int source, int pos, int value)
    {
        pos--;
        return source & (1 << pos ^ 0xFFFFFFFF) | value << pos;
    }

    public static long setBitValue(long source, int pos, long value)
    {
        pos--;
        long n = 1L;
        return source & (n << pos ^ 0xFFFFFFFF) | value << pos;
    }
}