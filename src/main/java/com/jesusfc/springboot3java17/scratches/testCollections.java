package com.jesusfc.springboot3java17.scratches;

import java.util.ArrayList;
import java.util.List;

public class testCollections {

    public static void main(String[] args) {
        diferenciasEntreDosListados();


    }
    private static void diferenciasEntreDosListados(){
        List<Long> oldList = List.of(1L,5L,7L);
        List<Long> newList = List.of(1L,7L, 9L);

        List<Long> removed = new ArrayList<>(oldList);
        removed.removeAll(newList);

        List<Long> same = new ArrayList<>(oldList);
        same.retainAll(newList);

        List<Long> added = new ArrayList<>(newList);
        added.removeAll(oldList);

        System.out.println("removed: " + removed);
        System.out.println("same: " + same);
        System.out.println("added: " + added);

    }

}

