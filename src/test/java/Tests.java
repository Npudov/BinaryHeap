import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import structure.project.BinaryHeap;

import java.util.ArrayList;
import java.util.List;

public class Tests {
   BinaryHeap binaryHeap = new BinaryHeap();
   @Test
    void adder() {
       //BinaryHeap binaryHeap = new BinaryHeap();
       List<Integer> expectedList = new ArrayList<Integer>();
       expectedList.add(1);
       expectedList.add(25);
       expectedList.add(27);
       binaryHeap.adder(1);
       binaryHeap.adder(25);
       binaryHeap.adder(27);
       assertEquals(expectedList, binaryHeap.getHeap());
       binaryHeap.clearHeap();
       binaryHeap.adder(25);
       binaryHeap.adder(1);
       binaryHeap.adder(27);
      assertEquals(expectedList, binaryHeap.getHeap());
   }

   @Test
   void deleteMin() {
      //BinaryHeap binaryHeap = new BinaryHeap();
      binaryHeap.clearHeap();
      binaryHeap.adder(25);
      binaryHeap.adder(1);
      binaryHeap.adder(27);
      binaryHeap.adder(2);
      assertEquals(1, binaryHeap.deleteMin());
   }

   @Test
   void clearHeap() {
      binaryHeap.clearHeap();
      List<Integer> expectedList = new ArrayList<Integer>();
      binaryHeap.adder(28);
      binaryHeap.adder(2);
      expectedList.add(2);
      expectedList.add(28);
      assertEquals(expectedList, binaryHeap.getHeap());
      binaryHeap.clearHeap();
      assertTrue(binaryHeap.getHeap().isEmpty());
   }

   @Test
   void search() {
      binaryHeap.clearHeap();
      binaryHeap.adder(25);
      binaryHeap.adder(2);
      binaryHeap.adder(3);
      assertEquals(25, binaryHeap.search(1));
   }

   @Test
   void heapSort() {
       binaryHeap.clearHeap();
       List<Integer> sortList = new ArrayList<>();
       sortList.add(25);
       sortList.add(2);
       sortList.add(3);
       sortList.add(45);
       sortList.add(20);
       binaryHeap.heapSort(sortList, sortList.size());
       List<Integer> expectedList = new ArrayList<>();
       expectedList.add(45);
       expectedList.add(25);
       expectedList.add(20);
       expectedList.add(3);
       expectedList.add(2);
       assertEquals(expectedList, sortList);
   }

   @Test
   void contains() {
      binaryHeap.clearHeap();
      binaryHeap.adder(25);
      binaryHeap.adder(2);
      binaryHeap.adder(3);
      binaryHeap.adder(45);
      binaryHeap.adder(20);
      assertTrue(binaryHeap.contains(45));
      assertFalse(binaryHeap.contains(1));
      binaryHeap.clearHeap();
      assertThrows(IllegalStateException.class, () -> {
         binaryHeap.contains(25);
      });
   }

   @Test
   void outInArray() {
      binaryHeap.clearHeap();
      binaryHeap.adder(25);
      binaryHeap.adder(3);
      binaryHeap.adder(27);
      String expectedStr = "3 25 27";
      assertEquals(expectedStr, binaryHeap.outInArray());
   }

   @Test
   void outInHeap() {
      binaryHeap.clearHeap();
      binaryHeap.adder(25);
      binaryHeap.adder(3);
      binaryHeap.adder(27);
      String expectedStr = "3 \n" +
                              "25 27";
      assertEquals(expectedStr.replaceAll("\n", " ").trim(), binaryHeap.outInHeap().replaceAll("\n", " ").trim());
      //assertTrue(expectedStr.trim().equals(binaryHeap.outInHeap()));
   }
}