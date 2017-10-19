package wang.mh.java8.stream_core_java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) throws  Exception {
        testParallel();
    }


    /**
     *thread safe  about parallelStream
     */
    public static void testParallel(){
        int[] arrInt = new int[10];
        getStream().parallel().forEach(s -> {
            if (s.length() < 10) {
                arrInt[s.length()] ++;  //not thread safe
            }
        });
        System.out.println("arrInt : " + Arrays.toString(arrInt));
        Arrays.fill(arrInt, 0);
        getStream().parallel().forEach(s -> {
            if (s.length() < 10) {
                arrInt[s.length()] ++;
            }
        });
        System.out.println("maybe not same as last arrInt : " + Arrays.toString(arrInt));


        Map<Integer, List<String>> lenToList = getStream().parallel()
                                               .collect(Collectors.groupingByConcurrent(String::length));

        System.out.println(lenToList.get(3));


        lenToList = getStream().parallel()
                .collect(Collectors.groupingByConcurrent(String::length));
        System.out.println("maybe not same order : " + lenToList.get(3));

    }


    /**
     *PrimitiveStream
     */
    public static void testPrimitiveStream(){
        IntStream stream = IntStream.rangeClosed(0, 10);
        System.out.println(stream.sum());

        Random random = new Random();
        IntStream randomInt = random.ints(10l,10,50);
        randomInt.forEach(System.out :: println);
    }


    /**
     * partition   分割成两个list  true/false
     */
    public static void testPartition(){
        Map<Boolean, List<Locale>> partitionMap = Stream.of(Locale.getAvailableLocales())
                .collect(Collectors.partitioningBy(l -> l.getCountry().equals("US")));
        System.out.println("true : " + partitionMap.get(true));
        System.out.println("false : " + partitionMap.get(false));
    }


    /**
     * group
     */
    public static  void  testGroup(){
        Map<String, Set<Locale>> groupMap = Stream.of(Locale.getAvailableLocales())
                .collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
        System.out.println(groupMap);
    }


    /**
     *生成map   有直接对象的concurrent map方法
     */
    public static void testMap(){

        //若存在相同的key  throw  IllegalStateException
//        Map<Integer, String> idToName = getPerson().collect(Collectors.toMap(Person::getId,
//                Person::getName));
//        System.out.println("idToName : " + idToName);

//        //返回自身对象
//        Map<Integer, Person> idToPerson = getPerson().collect(Collectors.toMap(Person::getId,
//                Function.identity()));
//        System.out.println("idToPerson : " + idToPerson);


        //第三个参数  可以处理多个相同键的情况  mergeFunction
        Map<Integer, Person> idToPerson = getPerson().collect(Collectors.toMap(Person::getId,
                Function.identity(),
                (old,newValue) -> old));
        System.out.println("idToPerson : " + idToPerson.getClass().getName() + "---" + idToPerson);

        //指定 哪种map
        idToPerson = getPerson().collect(Collectors.toMap(Person::getId,
                Function.identity(),
                (old,newValue) -> old,
                TreeMap :: new));
        System.out.println("idToPerson : " + idToPerson.getClass().getName() + "---" + idToPerson);


        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> setMap = locales.collect(Collectors.toMap(Locale::getDisplayCountry,
                l -> Collections.singleton(l.getDisplayLanguage()),
                (a, b) -> {
                    Set<String> set = new HashSet<>(a);
                    set.addAll(b);
                    return set;
                }));

        System.out.println("setMap : " + setMap);

        //另外一种实现方式 通过groupBy  mapping
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> setMap2 = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry,
                Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("setMap2 : " + setMap2);

    }


    /**
     *collect()
     */
    public static void testCollection(){

        //list
        List<String> list = getStream().collect(Collectors.toList());
        System.out.println("list : " + list);

        //set
        Set<String> set = getStream().collect(Collectors.toSet());
        System.out.println("set : " + set);

        //treeSet
        TreeSet<String> treeSet = getStream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("treeSet : " + treeSet);

        //join
        String join = getStream().collect(Collectors.joining("---", "(", ")"));
        System.out.println("join : " + join);


        //summarize
        IntSummaryStatistics summaryStatistics = getStream().collect(Collectors.summarizingInt(String::length));
        System.out.println("average : " + summaryStatistics.getAverage());

    }





    /**
     * safe return type   Optional
     */
    public static void testOption(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);

        //orElse
        Optional<Integer> first = stream.filter(i -> i > 2).findFirst();
        System.out.printf("orElse:%d%n",first.orElse(100));

        //orElseGet
        Optional<Integer> empty = Optional.empty();
        Integer orElseGet = empty.orElseGet(() -> {
            System.out.println("orElseGet");
            return 1 * 2 * 3 * 5;
        });
        System.out.printf("orElseGet:%d%n",orElseGet);

        //orElseThrow
        try {
            empty.orElseThrow(IllegalAccessException::new);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        }


        Set<Integer> set = new HashSet<>();
        first.ifPresent(set :: add);
        System.out.println("set:" + set);

        Optional<Boolean> isAdd = first.map(set::add);
        System.out.println("isAdd : " + isAdd.get());


        System.out.println(inverse(4.0).map(i -> i.intValue()));
        System.out.println(inverse(1.0).flatMap(StreamDemo::squareRoot).get());//1.0
        System.out.println(inverse(2.0).map(StreamDemo::squareRoot).get());//Optional[0.7071067811865476]


    }


    /**
     * 多种获取stram的方式
     * @throws Exception
     */
    public static void testCreateStream() throws Exception{
        Path path = Paths.get("/Users/wmh/mine/IdeaProjects/mine/java-learning/src/main/resources/streamWord");
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        //Stream.of  接受数组
        Stream<String> words = Stream.of(content.split("ll"));
        show("words",words);

        Stream<Integer> nums = Stream.of(1, 2, 3, 4);
        show("nums",nums);

        //无限的generate   配合limit()使用
        Stream<String> generate = Stream.generate(() -> "hello");
        show("generate",generate);
        //根据一个seed无限iterate
        Stream<Integer> iterate = Stream.iterate(0, n -> ++n);
        show("iterate",iterate);

        Stream<String> pattern = Pattern.compile("ll").splitAsStream(content);
        show("pattern",pattern);

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        show("lines",lines);


    }






    /**
     * parallelStream是并行计算,通过自身的ForkJoinPool,默认线程数是cpu核数(main线程包含在内)
     * 可以通过-Djava.util.concurrent.ForkJoinPool.common.parallelism=16  更改
     * 或 通过自己定义的ForkJoinPool
     */
    public static void testPatallelCount() throws ExecutionException, InterruptedException {
        DoublePredicate predicate = d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return d > 0.5;
        };
        List<Double> list = getList(10);
        Set<Thread> set  = new CopyOnWriteArraySet<>();

        long a = System.currentTimeMillis();
        long count = list.stream().filter(i -> {
            set.add(Thread.currentThread());
            return predicate.test(i);
        }).count();
        long b = System.currentTimeMillis();
        System.out.printf("个数:%d,耗时:%dms,线程数量:%d%n",count,(b-a),set.size());
        System.out.println(set);
        set.clear();
        long paraCount = list.parallelStream().filter(i -> {
            set.add(Thread.currentThread());
            return predicate.test(i);
        }).count();
        long c = System.currentTimeMillis();
        System.out.printf("个数:%d,耗时:%dms,线程数量:%d%n",count,(c-b),set.size());
        System.out.println(set);

        set.clear();
        //自己定制线程池数量    这里不会算上main线程
        final ForkJoinPool pool = new ForkJoinPool(6);
        Long paraCount2 = pool.submit(() -> {
            return list.parallelStream().filter(i -> {
                set.add(Thread.currentThread());
                return predicate.test(i);
            }).count();
        }).get();

        long d = System.currentTimeMillis();
        System.out.printf("个数:%d,耗时:%dms,线程数量:%d%n",count,(d-c),set.size());
        System.out.println(set);


    }


    private static Optional<Double> inverse(Double d){
        return d == 0 ? Optional.empty() : Optional.of(1 / d);
    }

    private static Optional<Double> squareRoot(Double d){
        return d < 0 ? Optional.empty() : Optional.of(Math.sqrt(d));
    }



    private static List<Double>  getList(int num){
        return Stream.generate(Math::random).limit(num)
                .collect(Collectors.toList());

    }


    private static <T> void show(String title,Stream<T> strem){
        final int SIZE = 10;
        List<T> list = strem.limit(SIZE + 1).collect(Collectors.toList());
        System.out.println(title + ":");
        for (int i = 0; i < list.size(); i++) {
            if(i > 0) System.out.print(i+"-->");
            if(i < SIZE) System.out.print(list.get(i));
            else System.out.print("...");
            System.out.println();
        }
    }

    private static Stream<String> getStream(){
        return Stream.of("aaa", "bbb", "aaa", "ddd", "bbb", "fff", "ggg");
    }

    private static Stream<Person> getPerson(){
        return Stream.of(new Person(1,"aaa"),
                new Person(2,"bbb"),
                new Person(2,"bbb2"),
                new Person(4,"ccc"),
                new Person(5,"ddd"));
    }


}


@Getter
@Setter
@ToString
@AllArgsConstructor
class Person{
    private int id;
    private String name;
}
