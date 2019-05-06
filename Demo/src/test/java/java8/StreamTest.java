package java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: qixiujuan
 * @date: 2019/4/26
 */
public class StreamTest {

    /**
     * logger for StreamTest
     */
    private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);

    @Test
    public void streamTest1() {
        // IntStream
        // IntStream.range(1, 4).forEach(System.out::println);
        // Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n +
        // 2).average().ifPresent(System.out::println);
        // Stream 顺序
        /*Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
            System.out.println("filter: " + s);
            return true;
        }).forEach(s -> System.out.println("forEach: " + s));*/
        // 构建一个 Person 集合
        List<Person> persons = Arrays.asList(
                new Person("Max", 18, 2),
                new Person("Peter", 23, 3),
                new Person("Pamela", 23, 2),
                new Person("David", 12, 3));

        IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age)); // 生成摘要统计

        // System.out.println(ageSummary);

        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner("|"),
                        (j, p) -> j.add(p.name.toUpperCase()),
                        (j1, j2) -> j1.merge(j2),
                        StringJoiner::toString);

        String names = persons.stream().collect(personNameCollector);
        System.out.println(names);

        persons.stream()
                .filter(d -> d.age > 1)
                .sorted(Comparator.comparing(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());

        persons.parallelStream()
                .filter(d -> d.age > 1)
                .sorted(Comparator.comparing(Person::getAge))
                .map(Person::getName) // Stream<String>
                .map(String::length) // Strean<Integer>
                .collect(Collectors.toList());

        Map<Integer, List<Person>> mapOfPerson = persons.stream()
                .collect(Collectors.groupingBy(Person::getClassId));
        
        logger.info("分组后的集合：{}", mapOfPerson);

        String[] arrayOfWords = {"Goodlbye", "World"};
        Stream<String> stringOfWord = Arrays.stream(arrayOfWords);
        persons.stream()
                .map(Person::getName)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .reduce()
                .collect(Collectors.toList());

        List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(4,5);
        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream().map(j -> new int[] { i, j }))
                .collect(Collectors.toList());

    }

    public Optional<Integer> getValue(Integer value){
        return Optional.of(value);
    }

    @Getter
    @Setter
    class Person {

        String name;

        int age;

        int classId;

        Person(String name, int age, int classId) {
            this.name = name;
            this.age = age;
            this.classId = classId;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
