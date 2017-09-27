package wang.mh.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 明辉 on 2017/6/26.
 */
public class StreamDemo {
    public static void main(String[] args) {

        //数组转流
        Integer[] arr = new Integer[]{1,2,3};
        Stream<Integer> streamArr = Stream.of(arr);
        Stream<Integer> streamArr2 = Arrays.stream(arr);

        List<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.CLOSED, 9),
                new Task(Status.CLOSED, 11));
        int sum = tasks.stream()
                .filter(t -> t.getStatus() == Status.CLOSED)
                .mapToInt(Task::getPoints).sum();
        System.out.println(sum);

        //并行处理   多个cpu
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map( task -> task.getPoints() ) // or map( Task::getPoints )
                .reduce( 2, Integer::sum );

        System.out.println( "Total points (all tasks): " + totalPoints );

        //分组
        final Map< Status, List< Task > > map = tasks
                .stream()
                .collect( Collectors.groupingBy( Task::getStatus ) );
        System.out.println( map );


        //分权重
        final Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / totalPoints )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );
    }

    //




    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
}
