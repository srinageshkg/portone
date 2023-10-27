package com.dcp.portone.designpatterns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AdapterBankDemo {
    public static void main(String[] args) {
        AdapterCreditCard cc = new AdapterAdapterBankCustomer();
        System.out.println("Credit card Adapter Pattern: "+cc.getCreditCard());
    }
}

class AdapterAdapterBankCustomer extends AdapterBankDetails implements AdapterCreditCard {

    AdapterBankDetails bd = new AdapterBankDetails("SBI", "Ramana", 879797977L);
    @Override
    public String getCreditCard() {
        return "Adapter Pattern: BankCust Adapter BankDetails Adaptee Credit card Interface "+bd.toString();
    }
}
@AllArgsConstructor
@NoArgsConstructor
class AdapterBankDetails {
    private String bankName;
    private String accHolderName;
    private Long accNumber;
}
interface AdapterCreditCard {
    public String getCreditCard();
}
