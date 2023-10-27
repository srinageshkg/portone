package com.dcp.portone.service;

import java.util.*;

public class StudyPairs {
    public void findStudyPairs(){

        Integer count1, count2, count3;

        List<Studentiit> stuDetails = new ThreePrizes().getStudentiitList();
        // count1 = studyPair(stuDetails);

        // Get the pairs of Students from same city but opposite gender

        System.out.println(similarTotal(stuDetails));
    }

    public List similarTotal(List<Studentiit> stuList){
        List<Integer> studentListCount = new ArrayList<>();
        HashMap<Integer, Studentiit> studentPairCounts = new HashMap<>();

        Integer cntOL = 0;
        Integer cntIL = 0;
        Integer studentCount = 0;
        List<Integer> pairedStuList = new ArrayList<>();

        while (cntOL < stuList.size()){
            Studentiit XStu = stuList.get(cntOL);
            studentCount = 0;
            cntIL = cntOL + 1;
            if(!pairedStuList.contains(XStu.getCardNo())) {
                while (cntIL < stuList.size()) {
                    Studentiit YStu = stuList.get(cntIL);
                    if (XStu.getCity() == YStu.getCity()) {
                        if (XStu.getGender() == YStu.getGender()) {
                            pairedStuList.add(YStu.getCardNo());
                            studentCount = studentCount + 1;
                        }
                    }
                    cntIL = cntIL + 1;
                }
                if (studentCount > 0) {
                    studentListCount.add(XStu.getCardNo());
                    studentListCount.add(studentCount + 1);
                    studentPairCounts.put(XStu.getCardNo(),XStu);
                }
            }
            cntOL = cntOL + 1;
        }

        String uuid = UUID.randomUUID().toString();
        System.out.println("Here are the details: " + uuid  + " OK " + studentPairCounts.values());
        return studentListCount;
    }

    public Integer studyPair(List<Studentiit> stuDetails) {
        Integer A = 0;
        Integer cnt = 0;

        HashMap<Integer, Studentiit> stuidpairCnt = new HashMap<>();
        //List<Studentiit>
        while (cnt < stuDetails.size()) {
            Integer cntr = 0;
            while (cntr < (stuDetails.size())) {
                cntr = cnt + 1;
                Integer marksDiff = (stuDetails.get(cnt).getMarksiit().getMaths() - stuDetails.get(cntr).getMarksiit().getMaths());

                if ( marksDiff >= -10 || marksDiff <= 10) {
                    A = A + 1;
                }
                cntr = cntr + 1;
            }
            if (A != 0){
                stuidpairCnt.put(cnt, stuDetails.get(cnt));
                System.out.println(stuDetails.get(cnt));

            }
            cnt = cnt + 1;
        }
        System.out.println(stuidpairCnt + " Here it is..");
        return A;
    }

/*    {
        list.add(new Product(1,"HP Laptop",25000f));
        // implementing lambda expression
        Collections.sort(list,(p1,p2)->{
            return p1.name.compareTo(p2.name);
        });
        for(Product p:list){
            System.out.println(p.id+" "+p.name+" "+p.price);
        }
        List<Product> list=new ArrayList<Product>();
        list.add(new Product(1,"Samsung A5",17000f));
        // using lambda to filter data
        Stream<Product> filtered_data = list.stream().filter(p -> p.price > 20000);

        // using lambda to iterate through collection
        filtered_data.forEach(
                product -> System.out.println(product.name+": "+product.price)
        );
        // using lambda to filter data
        Stream<Product> filtered_data = list.stream().filter(p -> p.price > 20000);

        // using lambda to iterate through collection
        filtered_data.forEach(
                product -> System.out.println(product.name+": "+product.price)
        );
        List<String> list=new ArrayList<String>();
        list.add("ankit");
        list.add("mayank");
        list.add("irfan");
        list.add("jai");

        list.forEach(
                (n)->System.out.println(n)
        );
        // Lambda expression without return keyword.
        Addable ad1=(a,b)->(a+b);
        System.out.println(ad1.add(10,20));

        // Lambda expression with return keyword.
        Addable ad2=(int a,int b)->{
            return (a+b);
        };
        System.out.println(ad2.add(100,200));

        interface Sayable{
            public String say();
        }
        public class LambdaExpressionExample3{
            public static void main(String[] args) {
                Sayable s=()->{
                    return "I have nothing to say.";
                };
                System.out.println(s.say());
            }
        }*/
}

