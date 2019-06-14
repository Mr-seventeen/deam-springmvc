package java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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

    @Test
    public void iterateTest(){
        Stream.iterate(0, n -> n+2).limit(10).forEach(System.out::println);
        /**
         * 斐波纳契元组序列
         */
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("参数："+t[0]+" and :"+t[1]));
    }

    @Test
    public void generateTest(){
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    /**
     * 终端操作
     */
    @Test
    public void finalOptionTest(){
        List<Person> persons = Arrays.asList(
                new Person("Max", 18, 2),
                new Person("Peter", 23, 3),
                new Person("Pamela", 23, 2),
                new Person("David", 12, 3));
        persons.stream().forEach(p -> System.out.println("结果"+p.getName()));
        persons.stream().count();
        persons.stream().findFirst();
        // 根据一定的规则将Stream中的元素进行计算后返回一个唯一的值
        Optional<Person> op = persons.stream().reduce((person, person2) -> person.getAge() > person2.getAge() ? person : person2);
    }

    @Test
    public void collectorsTest() {
//        Collectors.toList()
    }
    List<Person> persons = Arrays.asList(
            new Person("Max", 18, 2),
            new Person("Peter", 23, 3),
            new Person("Pamela", 23, 2),
            new Person("David", 12, 3));
    @Test
    public void thirdMethod() {
        int youngAge = 18;
        Predicate<Person> agePerson = person -> person.getAge() >= youngAge;
        Predicate<Person> namePerson = p -> p.getName().length() > 3;
        Consumer<Person> personConsumer = p -> p.setAge(p.getAge() + 10);

        persons.stream().forEach(personConsumer);
        persons.stream()
                .filter(agePerson)
                .filter(namePerson)
                .forEach(person -> System.out.println("name : "+ person.getName() +"age :"+ person.getAge()));

        Function<Person, Integer> function = p -> p.getAge();
    }

    @Test
    public void testMethod() {
        List<Integer> integers = Arrays.asList(1, 6, 2, 3, 4);
        integers.stream().forEach(s -> System.out.println("传入数据："+ s));
        Collections.reverse(integers);
        integers.stream()
                .map(i -> (Consumer<Runnable>) runnable -> {
                        System.out.println(i);
                        runnable.run();
                })
                .reduce((Runnable) () -> System.out.println("ok"),
                        (result, fun) -> () -> fun.accept(result),
                        (x, y) -> x)
                .run();

        integers.stream()
                .map(i -> {
                    return (Consumer<Runnable>) runnable -> {
                        System.out.println(i);
                        runnable.run();
                    };
                })
                .reduce((Runnable) () -> System.out.println("ok"), (result, fun) -> () -> {
                    fun.accept(result);
                }, (x, y) -> {
                    return x;
                })
                .run();
    }

    @Test
    public void collectMethodTest(){

        persons.stream().collect(Collectors.toSet());
        persons.stream().collect(Collectors.summarizingInt(Person::getAge));
/*        persons.stream().collect(Collectors.toList()
                .characteristics()
                .add()
        );*/
        persons.stream().collect(Collectors.toList());
    }
}
