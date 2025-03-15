package org.example.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

//public class IdGenerator {
//    private static final Map<Class<?>, Integer> counters = new ConcurrentHashMap<>();
//    private static final Map<Class<?>, Set<Integer>> existingIds = new ConcurrentHashMap<>();
//
//    // Регистрация существующего ID (например, при загрузке из файла)
//    public static void registerExistingId(Class<?> entityClass, int id) {
//        existingIds.computeIfAbsent(entityClass, k -> ConcurrentHashMap.newKeySet()).add(id);
//    }
//
//    // Генерация нового ID
//    public static long generateId(Class<?> entityClass) {
//        int counter = counters.computeIfAbsent(entityClass, k -> 1);
//        Set<Integer> usedIds = existingIds.computeIfAbsent(entityClass, k -> ConcurrentHashMap.newKeySet());
//
//        int newId;
//        do {
//            newId = counter.incrementAndGet();
//        } while (usedIds.contains(newId)); // Генерируем, пока не найдем свободный ID
//
//        usedIds.add(newId);
//        return newId;
//    }
//}
