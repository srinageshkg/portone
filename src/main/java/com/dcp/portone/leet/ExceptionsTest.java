package com.dcp.portone.leet;

public class ExceptionsTest {
    public static void main(String[] args) {
/*        try {
            checkedMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
//        anotherMethod();
        try {
            checkedMethod();
        }catch (ArithmeticException e) {
            System.out.println(e.getStackTrace().toString());
        } catch (Exception e){
            System.out.println(e.getMessage().toString());
        } finally {
            System.out.println("All GooD");
        }
    }

    public static void checkedMethod() throws Exception{
        try {
            int a = 5 / 0;
            a += 5;
        } catch (Exception e) {
            throw new Exception("Failed to Load");
        }
    }

    public static void anotherMethod() throws RuntimeException {
        throw new RuntimeException("Wrong Parameter");
    }
}
