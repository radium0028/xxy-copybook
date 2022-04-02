package com.gitee.radium.xxycopybook.dict;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Radium
 * @date 2022/3/15 10:26 上午
 */
public interface EnumInterface<T, K> {
    T getCode();

    K getValue();

    default boolean codeEquals(T enumCode) {
        AtomicBoolean flag = new AtomicBoolean(false);
        Optional.ofNullable(enumCode).ifPresent(code -> {
            if (code instanceof String) {
                String codeString = (String) code;
                flag.set(((String) getCode()).equalsIgnoreCase(codeString));
            } else {
                flag.set(Objects.equals(this.getCode(), code));
            }
        });
        return flag.get();
    }

    default boolean equals(EnumInterface<T, K> enumInterface) {
        return this == enumInterface;
    }
}