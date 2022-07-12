package io.github.radium0028.xxycopybook.dict;

import java.util.EnumSet;

/**
 * @author Radium
 * @date 2022/3/15 11:00 上午
 */
public class EnumUtil {
    public static <E extends Enum<E>, T, K> EnumInterface<T, K> getEnumByCode(Class<E> enumClass, T code) {
        if (null == code) {
            return null;
        }
        EnumSet<E> es = EnumSet.allOf(enumClass);
        for (E anEnum : es) {
            if (anEnum instanceof EnumInterface) {
                if (((EnumInterface<T, K>) anEnum).codeEquals(code)) {
                    return (EnumInterface<T, K>) anEnum;
                }
            }
        }
        return null;
    }
}