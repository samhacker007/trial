package trial.google.guava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class ImmutableTest {
   public static void main(String args[]){                                                                                                                                                                                                                                 
        List<String> list=new ArrayList<String>();                                                                               
        list.add("a");                                                                                                           
        list.add("b");                                                                                                           
        list.add("c");
        
        System.out.println(list);
        
        List<String> unmodifiableList=Collections.unmodifiableList(list); 
        
        System.out.println(unmodifiableList);
        
        List<String> unmodifiableList1=Collections.unmodifiableList(Arrays.asList("a","b","c")); 
        System.out.println(unmodifiableList1);
        
        String temp=unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]："+temp);
                
        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after unmodifiableList:"+unmodifiableList);
        
        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList1);
        
        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList);        
    }
}
