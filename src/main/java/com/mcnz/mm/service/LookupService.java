package com.mcnz.mm.service;

public abstract class LookupService<T, K> {

    public abstract T lookup(K name);

}