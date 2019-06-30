package com.anthonyzero.scaffold.common.function;

import com.anthonyzero.scaffold.common.exception.RedisConnectException;

/**
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {

    R excute(T t) throws RedisConnectException;
}
