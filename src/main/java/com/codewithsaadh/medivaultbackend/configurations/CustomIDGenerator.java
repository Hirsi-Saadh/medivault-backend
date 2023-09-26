package com.codewithsaadh.medivaultbackend.configurations;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CustomIDGenerator implements IdentifierGenerator {

    private static final AtomicLong counter = new AtomicLong(0);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long currentTimeMillis = System.currentTimeMillis();
        long uniqueValue = currentTimeMillis * 1000 + counter.incrementAndGet();
        return String.format("%07d", uniqueValue);
    }
}

