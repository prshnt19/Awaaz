package com.pr.f2w;

/**
 * Created by Arpit on 13-Jan-18.
 */

class Singleton
{
    private static Singleton single_instance = null;
    public int j;

   private Singleton() {
        j=1;
    }

 public static Singleton getInstance() {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }
}
