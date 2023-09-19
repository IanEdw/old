package ru.aston.ian_ev.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    @DisplayName(value = "Соберите даты в Stream в одну дату, сложив дни, месяцы и годы и выведите результат.")
    void first() {
        Stream<LocalDate> stream = Stream.of(
                LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1, 1),
                LocalDate.of(1, 1,1)
        );

        LocalDate result = stream.reduce((o1, o2) -> {
            LocalDate res = o1.plusYears(o2.getYear());
            res = res.plusMonths(o2.getMonthValue());
            return res.plusDays(o2.getDayOfMonth());
        }).orElseThrow();

        System.out.println(result);
    }

    @Test
    @DisplayName(value = "Соберите числа в Stream в одно число, перемножив их между собой и выведите результат.")
    void second() {
        Stream<Integer> stream = Stream.of(2, 2, 2, 2, 2, 2, 2, 2);
        Integer res = stream.reduce((a, b ) -> a * b).orElseThrow();
        System.out.println(res);
    }

    @Test
    @DisplayName(value = "Задан массив строк. Используя средства StreamAPI отсортировать строки в лексикографическом порядке.")
    void third() {
        Stream<String> stream = Stream.of("e", "d", "c", "b", "a");
        stream.sorted(String::compareTo).forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Задано множество фамилий сотрудников. Разработать программу, которая отображает все фамилии, начинающиеся на букву «J» . Задачу решить с использованием Stream API.")
    void fourth() {
        Stream<String> stream = Stream.of("Surname", "Jsurname", "NoJsurname", "Jonson");
        stream.filter(s -> s.startsWith("J")).forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Вывести все четные числа в диапазоне от 1 до 100")
    void fifth() {
        Stream.iterate(1, i -> i + 1).limit(100).filter(i -> i % 2 == 0).forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Собрать элементы Stream в карту, где ключом будет первая буква каждого слова, а значением — само слово. Отсортировать ключи в алфавитном порядке.")
    void sixth() {
        Stream<String> stream = Stream.of("Cfirst", "Bsecond", "Athird");

        Map<String, String> map = stream.collect(Collectors.toMap(s -> s.substring(0, 1), s -> s));
        System.out.println(map);
    }

    @Test
    @DisplayName(value = "Создайте Stream из массива чисел, выведите на экран числа, кратные 3 и 5 одновременно.")
    void seventh() {
        Stream.iterate(1, i -> i + 1)
                .limit(100)
                .filter(i -> (i % 3 == 0) && (i % 5 == 0))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Создайте два Stream-а: один из массива чисел 1…5, второй из массива 5…10. Объедините эти два Stream-а в один и выведите на экран.")
    void eighth() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = Stream.of(6, 7, 8, 9, 10);
        Stream.concat(stream, stream1).forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Создайте Stream-у чисел от 1 до 20. Создайте новый Stream, который будет выводить на экран только четные числа и числа, кратные 3. Затем объедините эти два Stream-a в один и выведите результирующий Stream.")
    void ninth() {
        Stream<Integer> s1 = Stream.iterate(1, i -> i + 1).limit(20);
        Stream<Integer> s2 = Stream.iterate(6, i -> {
            int res = i + 2;
            while (res % 2 != 0 || res % 3 != 0) {
                res += 2;
            }
            return res;
        }).limit(20);
        Stream.concat(s1, s2).forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "Создать стрим четных чисел от 2 до 40 и вывести на экран количество элементов в этом стриме")
    void tenth() {
        long count = Stream.iterate(2, i -> i + 2).limit(20).count();
        System.out.println("count = " + count);
    }
}
