package com.dcp.portone.designpatterns;

import lombok.*;

@ToString
public class AdaDPayDemo {

    public static void main(String[] args) {
        AdaDPayDemo adaTest = new AdaDPayDemo();
        adaTest.testAda();
    }
    // create an object of Xpay+
    public void testAda(){
        AdaXPay xPay = new AdaXpayImpl("OldXPayClient", 767, "2027", "09");
        System.out.println(xPay);

        AdaDpay dPay = new AdaXpayToDpayImpl(xPay);
        System.out.println(dPay);
    }
}

class AdaXpayToDpayImpl implements AdaDpay {
    private String clientName;
    private String expiryMthYr;
    private Integer securityNum;

    private final AdaXPay xPay;

    public AdaXpayToDpayImpl(AdaXPay xPay){
        //System.out.println(xPay.toString());
        this.xPay = xPay;
        this.expiryMthYr = xPay.getExpMth()+"/"+xPay.getExpYr();
        this.clientName = xPay.getCustName();
        this.securityNum = xPay.getCvv();
    }
    @Override
    public String getClientName(){
        return this.xPay.getCustName();
    }
    @Override
    public Integer getSecurityNum(){
        return this.xPay.getCvv();
    }
    @Override
    public String getExpiryMthYr(){
        return this.xPay.getExpMth()+"/"+this.xPay.getExpYr();
    }

    @Override
    public String toString() {
        return "%s xPayWith New Face: (%s) with Sec # %d Expiry Mth Yr %s ".formatted(clientName, xPay, securityNum ,expiryMthYr);
    }
}
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
class AdaXpayImpl implements AdaXPay{
    private String custName;
    private Integer cvv;
    private String expYr;
    private String expMth;

    @Override
    public String toString() {
        return "%s AdaXPay: with Sec # %d  Expiry Mth %s Yr %s ".formatted(custName, cvv, expMth ,expYr);
    }
}
interface AdaXPay {
    String getCustName();
    Integer getCvv();
    String getExpYr();
    String getExpMth();
}
interface AdaDpay {
    public String getClientName();
    public String getExpiryMthYr();
    public Integer getSecurityNum();

}