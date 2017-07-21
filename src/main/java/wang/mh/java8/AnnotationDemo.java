package wang.mh.java8;

import java.lang.annotation.*;

/**
 * Created by 明辉 on 2017/6/26.
 * java8可以引入了重复注解机制，这样相同的注解可以在同一地方声明多次。
 */
public class AnnotationDemo {



    public static class RepeatingAnnotations {
        @Target( ElementType.TYPE )
        @Retention( RetentionPolicy.RUNTIME )
        public @interface Filters {
            Filter[] value();
        }

        @Target( ElementType.TYPE )
        @Retention( RetentionPolicy.RUNTIME )
        @Repeatable( Filters.class )
        public @interface Filter {
            String value();
        };

        @Filter( "filter1" )
        @Filter( "filter2" )
        public interface Filterable {
        }

        public static void main(String[] args) {
            for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
                System.out.println( filter.value() );
            }
        }
    }
}
