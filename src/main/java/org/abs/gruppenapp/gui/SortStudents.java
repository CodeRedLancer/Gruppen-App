package org.abs.gruppenapp.gui;

import java.util.*;

import org.abs.gruppenapp.entities.StudentToGroup;

public class SortStudents {


    public static List<StudentToGroup> sort(String input, List<StudentToGroup> UnsortedList){
        switch(input)
        {
            case "homogen" : {

                List<StudentToGroup> SortedList = new ArrayList<>();

                int[] intArray = new int[UnsortedList.size()];
                int[] intArraySort = new int[UnsortedList.size()];
                int tCount = 0;
                while(UnsortedList.size() > tCount){
                    StudentToGroup schueler = UnsortedList.get(tCount);
                    intArray[tCount] = schueler.getEvaluation();
                    intArraySort[tCount] = intArray[tCount];
                    tCount++;
                }
                Arrays.sort(intArraySort);
                try{

                    int i, j, temp;
                    boolean swapped;
                    int n = intArraySort.length;
                    for (i = 0; i < n - 1; i++) {
                        swapped = false;
                        for (j = 0; j < n - i - 1; j++) {
                            if (intArraySort[j] < intArraySort[j + 1]) {

                                temp = intArraySort[j];
                                intArraySort[j] = intArraySort[j + 1];
                                intArraySort[j + 1] = temp;
                                swapped = true;
                            }
                        }
                        if (swapped == false){
                            break;
                        }

                    }

                    int tLenght = intArraySort.length;

                    tCount = 0;
                    while(tLenght > tCount) {
                        int tCount2 = 0;
                        while(UnsortedList.size() > tCount2) {
                            if (tCount2 < tLenght) {

                            StudentToGroup schueler = UnsortedList.get(tCount2);

                            if (tCount < tLenght) {
                                if (intArraySort[tCount] == schueler.getEvaluation()) {
                                    SortedList.add(UnsortedList.get(tCount2));
                                }
                            }
                            tCount2++;
                        }
                        }
                        tCount++;
                    }
                     List test = removeDuplicate(SortedList);

                    return test;
                } catch(Exception e){
                    System.out.println("Exception e");
                }
                break;
            }

            case "heterogen" : {
                break;
            }

            case "selbst" : {
                break;
            }

            case "zufall" : {

                break;
            }
        }
        return UnsortedList;
    }



    public static List<?> removeDuplicate(List<?> list) {
        Set<?> set = transformListIntoSet(list);
        return transformSetIntoList(set);
    }

    public static Set<?> transformListIntoSet(List<?> list) {
        Set<Object> set = new LinkedHashSet<>();
        set.addAll(list);
        return set;
    }

    public static List<?> transformSetIntoList(Set<?> set) {
        List<Object> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    public static ArrayList<StudentToGroup[]> output(String input, List<StudentToGroup> sortetList) {
        System.out.println("____________________" + "SortetList Size: " + sortetList.size());
        ArrayList<StudentToGroup[]> ArrayListString = null;
        if (Integer.valueOf(input) != 0) {
            try {
                ArrayListString = new ArrayList<>();

                if (sortetList.isEmpty()) {
                } else {
                    int inputInt = Integer.valueOf(input);

                    int i = 0;
                    StudentToGroup[] tTest;
                    while (!sortetList.get(i).equals("")) {
                        int tCount = 0;
                        int tCount3 = 0;
                        int tSize = sortetList.size();
                        while (tCount < tSize) {
                            if(tCount3 == inputInt){
                                System.out.println("Nächte Gruppe");
                                System.out.println("_______________________________________________________________________________");
                                tCount3 = 0;
                            }
                            //tTest = new StudentToGroup[inputInt];
                            //tTest[tCount] = sortetList.get(tCount);
                            //ArrayListString.add(tTest);
                            System.out.println("tTest + tCount = " + sortetList.get(tCount).getFirstName() + " Note: " + sortetList.get(tCount).getEvaluation());
                            tCount3++;
                            tCount++;
                            i++;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Catch");
            }
        }
        System.out.println("Equals");
        return ArrayListString;
    }

}
