package com.dcp.portone.designpatterns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class FactoryDemo {
    public static void main(String[] args) {

        FactoryComputer cf = FactoryComputerFactory.getComputer("pc","500", "1TB", "6");
        System.out.println("Factory Pattern: "+cf.toString());
        FactoryComputer sf = FactoryComputerFactory.getComputer("server","500", "1TB", "6");
        System.out.println("Factory Pattern: "+sf.toString());
    }
}
class FactoryComputerFactory {
    public static FactoryComputer getComputer(String type, String ram, String hdd, String cpu) {
        if (type.isEmpty()) {
            return null;
        }
        if ("PC".equalsIgnoreCase(type)) {
            return new FactoryPc(ram, hdd, cpu);
        } else if ("Server".equalsIgnoreCase(type)) {
            return new FactoryServer(ram, hdd, cpu);
        } else {
            throw new IllegalArgumentException("Unknown Computer type :"+type);
        }
    }
}

@NoArgsConstructor
@AllArgsConstructor
@ToString
class FactoryServer extends FactoryComputer {
    private String ram;
    private String hdd;
    private String cpu;
    @Override
    public String getRAM() {
        return this.ram;
    }
    @Override
    public String getHDD() {
        return this.hdd;
    }
    @Override
    public String getCPU() {
        return this.cpu;
    }
}
@NoArgsConstructor
@AllArgsConstructor
@ToString
class FactoryPc extends FactoryComputer {
    private String ram;
    private String hdd;
    private String cpu;
    @Override
    public String getRAM() {
        return this.ram;
    }
    @Override
    public String getHDD() {
        return this.hdd;
    }
    @Override
    public String getCPU() {
        return this.cpu;
    }
}

abstract class FactoryComputer {
    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();

    @Override
    public String toString() {
        return ("Print from Abstract Class Computer - RAM= "+this.getRAM()+"HDD= "+this.getHDD()+"CPU= "+this.getCPU());
    }
}