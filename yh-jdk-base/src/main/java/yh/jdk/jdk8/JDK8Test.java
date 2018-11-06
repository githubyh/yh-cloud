package yh.jdk.jdk8;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author
 * @create 2018-03-14 9:41
 **/

public class JDK8Test {

    @Test
    public void testCollectionsSort() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println(names);
        List newlist = Lists.newArrayList(names);

        Collections.sort(newlist, (String a,String b)->{ return a.compareTo(b);});
        System.out.println(newlist);
    }


    @Test
    public void testPredicate(){
        JDK8Test jdk8Test = new JDK8Test() ;
        Predicate<JDK8Test> predicate1 = (s) -> s !=null;
        System.out.println(predicate1.test(jdk8Test));
        System.out.println(predicate1.negate().test(jdk8Test));


        Predicate<String> predicate = (s) -> s.length() > 0;
        String s = "", s2="v";
        System.out.println(predicate.test(s2));
        System.out.println(predicate.negate().test(s2));
        System.out.println(predicate.negate().test(s2));

        Boolean b = false;
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(nonNull.test(b));
        System.out.println(isNull.test(b));

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();//取反
        System.out.println(isEmpty.test(s));  // true
        System.out.println(isNotEmpty.test(s));  // true


    }

    @Test
    public void testFile(){
        System.out.println("NK24877425130HAC38AD54CC25247B47".length());
        Stopwatch stopwatch = new Stopwatch() {
            @Override
            public long runtime(TimeUnit unit) {
                return super.runtime(unit);
            }
        };
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get("pom.xml")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        List<String> s = Arrays.asList(str.split("[\\P{L}]+"));
        int count =0;
        for (String st :s){
            if(st.length() >7)count++;
        }

        long  l = s.stream().filter(w -> w.length() >7 ).count();
        System.out.println(l);
           l = s.parallelStream().filter(w -> w.length() >7 ).count();
        System.out.println(l);


        Stream<String> stream = Stream.of("a","b");
//        System.out.println(stream.limit(1));
//        stream = Stream.generate(()->"a");
        Stream istream = Stream.generate(Math::random).limit(50);
        System.out.println(stream);

       //stream.map(n -> n.charAt(0));
//         stream.map(String::toLowerCase).forEach( System.out::println);

//        Stream cstream = Stream.concat(stream,istream);

//        stream.peek(a->System.out.println(a));


//        Optional<String> o = stream.max(String::compareToIgnoreCase);
//        if(o.isPresent())System.out.println(o.get());

        stream.filter(as->as.startsWith("q")).findFirst();
  stream.peek(System.out::print).map(String::toLowerCase);
        stream.filter(as->as.startsWith("a")).findAny();
        boolean d = stream.parallel().anyMatch(as->as.startsWith("a"));

    }

    @Test
    public void testMap(){
        Stream<A> stream = Stream.of(new A("name1","20"), new A("name2", "30"), new A("name2", "35"));
        Map<String,String> map = stream.collect(Collectors.toMap(A::getName,A::getAge));
        System.out.println(map);

//        Map<String,A> mapa = stream.peek(a->System.out.println("=="+a.getName())).collect(Collectors.toMap(A::getName, Function.identity()));
//        System.out.println(mapa);


        map =   stream.collect(Collectors.toMap(A::getName,A::getAge,(exits,newvalue)->newvalue));
        System.out.println(map);
    }

    @Test
    public void testInteger(){
        IntStream intStream = IntStream.of(1,3,3,35);
        intStream.forEach(e->System.out.println(e));

        intStream = IntStream.range(0,166);
        intStream.forEach(e->System.out.println(e));

        Stream<String> stringStream = Stream.of("2342","324535","asdfas");
        intStream = stringStream.mapToInt(String::length);
        intStream.forEach(e->System.out.println(e));
        Stream<Integer> stream = intStream.boxed();
//        Stream<Integer> stream1 = Stream.of(new int[]{1,3}).mapToInt(e->Integer.valueOf(e));

    }



}

class A{
    private String name ;
    private String age ;

    public A(String name,String age ){
        this.name  = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}



