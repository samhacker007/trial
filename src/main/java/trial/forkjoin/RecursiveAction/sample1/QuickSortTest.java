package trial.forkjoin.RecursiveAction.sample1;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class QuickSortTest extends RecursiveAction {

    public int array[] = null;

    private int start;

    private int end;

    public QuickSortTest( int[] array, int start, int end ) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public QuickSortTest( int[] array ) {
        this.array = array;
    }

    private int partition( int[] array, int beg, int end ) {
        int first = array[ beg ];
        int i = beg, j = end;
        while( i < j ) {
            while( array[ i ] <= first && i < end ) {
                i++;
            }
            while( array[ j ] > first && j >= beg ) {
                j--;
            }
            if( i < j ) {
                array[ i ] = array[ i ] ^ array[ j ];
                array[ j ] = array[ i ] ^ array[ j ];
                array[ i ] = array[ i ] ^ array[ j ];
            }
        }
        if( j != beg ) {
            array[ j ] = array[ beg ] ^ array[ j ];
            array[ beg ] = array[ beg ] ^ array[ j ];
            array[ j ] = array[ beg ] ^ array[ j ];
        }
        return j;
    }

    private void originalQuickSort( int[] array, int start, int end ) {
        if( start > end ) {
            return;
        }
        int p = partition( array, start, end );
        originalQuickSort( array, start, p - 1 );
        originalQuickSort( array, p + 1, end );
    }

    @Override
    protected void compute() {
        if( start > end ) {
            return;
        }
        int p = partition( array, start, end );
        new QuickSortTest( array, start, p - 1 ).fork();
        new QuickSortTest( array, p + 1, end ).fork();
    }

    public static void main( String[] args ) {
        int LENGTH = 50000000 * 2;
        int[] array1 = new int[ LENGTH ];
        int[] array2 = new int[ LENGTH ];
        int[] array3 = new int[ LENGTH ];
        for( int i = 0; i < LENGTH; i++ ) {
            int n = ( int )( Math.random() * Integer.MAX_VALUE ) % Integer.MAX_VALUE;
            array1[ i ] = n;
            array2[ i ] = n;
            array3[ i ] = n;
        }
        
        System.out.println( "run" );
        
        //简单快排
        QuickSortTest qst = new QuickSortTest( array2 );
        long startTime = System.currentTimeMillis();
        qst.originalQuickSort( array2, 0, array2.length - 1 );
        System.out.println( "original quick sort : " + ( float )( System.currentTimeMillis() - startTime ) / 1000f + "s" );
        
        
        //Fork/Join并行计算版本
        startTime = System.currentTimeMillis();
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.submit( new QuickSortTest( array1, 0, array1.length - 1 ) );
        fjp.shutdown();
        try {
            fjp.awaitTermination( 10, TimeUnit.SECONDS );
        } catch( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println( "multiThread quick sort : " + ( float )( System.currentTimeMillis() - startTime ) / 1000f + "s" );
         
        //jdk排序
        startTime = System.currentTimeMillis();
        Arrays.sort( array3 );
        System.out.println( "jdk sort : " + ( float )( System.currentTimeMillis() - startTime ) / 1000f + "s" );
    }

}
//original quick sort : 13.032s
//multiThread quick sort : 3.502s
//jdk quick sort : 9.033s