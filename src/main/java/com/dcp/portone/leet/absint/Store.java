package com.dcp.portone.leet.absint;

import java.util.ArrayList;
record OrderItem (String description, int quantity, ProductForSale product, SalesPerson sp) {}

record SalesPerson (String name, int id) {
    public String printDetails() {
        return ("%10s (%5d)".formatted(name, id));
    };
}

public class Store {
    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();

    public static void main(String[] args) {

        // Add Products to store Products

        storeProducts.add(new ArtObject("Oil Painting", 1350, "Paint in Red"));
        storeProducts.add(new ArtObject("Sculpture", 2000, "Sculpture is special"));
        storeProducts.add(new FurnitureObject("Sofa", 3500, "Sofa Special"));
        storeProducts.add(new FurnitureObject("Table", 300, "Table Takewood"));
        ProductForSale pfs = new FurnitureObject("Chairs", 150, "Chairs");
        storeProducts.add(pfs);
        listProducts();

        //---------------------------------------------------------------------

        SalesPerson sp1 = new SalesPerson("Rao", 101);
        SalesPerson sp2 = new SalesPerson("Grill", 102);

        // Order Item contains description, qty and ProductForSale and additional field contains the store number
        var order1 = new ArrayList<OrderItem>();
        addItemToOrder(order1,"Order1",1,2, sp1);
        addItemToOrder(order1,"Order1",0,1, sp1);
        var order2 = new ArrayList<OrderItem>();
        addItemToOrder(order2,"Order2",2,3, sp2);
        addItemToOrder(order2,"Order2",3,4, sp2);
        addItemToOrder(order2,"Order2",4,5, sp2);
        printOrder(order1);
        printOrder(order2);

    }
    public static void listProducts() {
        for (var item: storeProducts) {
            System.out.println("_".repeat(30));
            item.showDetails();
        }
    }
    public static void addItemToOrder(ArrayList<OrderItem> order, String desc, int orderIndex, int qty, SalesPerson sp) {
        order.add(new OrderItem(desc, qty, storeProducts.get(orderIndex),sp));
    }
    private static void printOrder(ArrayList<OrderItem> orderItems) {
        double salesTotal = 0;
        for (var oi: orderItems) {
            // System.out.println("Order Id: " + (orderItems.indexOf(oi) + 1));
            System.out.printf("%-15s", oi.description());
            oi.product().printPricedLineItem(oi.quantity());
            salesTotal += oi.product().getSalesPrice(oi.quantity());
            System.out.println("Sales Total  " + salesTotal + "   Sold by: " + oi.sp().printDetails());
        }
    }
    String coding = """
            Abstract Class
            Create an abstract class to define items that can be stored in a tree.

            Implement the following:
 1.  ListItem (abstract class)
     -  It has three protected fields. Two ListItems called rightLink and leftLink, and an Object called value.
         -  A constructor that takes an Object and initialises the field value with the parameter that was passed in.
    -  And seven methods:
       -  next(), setNext(), previous(), setPrevious() and compareTo() which are package-private and abstract (see child class for declaration).
      -  getValue(), takes no parameters and returns its value.
   -  setValue(), takes an Object and assigns it to value.
  2.  Node (concrete class)
  -  It extends ListItem.
 -  It has a constructor that takes an Object, then calls its parent constructor with the parameter that was passed in.
   -  And five methods which are package-private:
  -  next(), takes no parameters and returns the ListItem to its right.
  -  setNext(), takes a ListItem and sets it as its rightLink, then it returns rightLink.
  -  previous(), takes no parameters and returns the ListItem to its left.
   -  setPrevious(), takes a ListItem and sets it as its leftLink, then it returns leftLink.
   -  compareTo(), takes a ListItem and compares it to the ListItem that called this method. Use value from ListItem for comparison. If this value is greater than the value that was passed in, then it should return a number greater than zero. If vice versa, then it should return a number less than zero, and zero if equal.
 3.  MyLinkedList (concrete class)
 -  It implements NodeList.
  -  It has one field of type ListItem called root.
  -  A constructor that takes a ListItem and initialises the field root with the newly passed in parameter.
   -  And four methods:
  -  getRoot(), getter for root.
 -  addItem(), takes a ListItem and returns true if it was added successfully or false otherwise. If the item is already present, it doesn't get added. Use compareTo() to place the item in its proper order.
   -  removeItem(), takes a ListItem and returns true if it was removed successfully or false otherwise.
  -  traverse(), takes the root as an argument and does not return anything. If the root is null it prints out: The list is empty, otherwise print each value on a separate line.
   
 4.  NodeList (interface)
  -  It has four methods:
      -  getRoot(), addItem(), removeItem() and traverse() which are package-private and abstract (see child class for declaration).

 5.  SearchTree (concrete class)
 -  It implements NodeList.
  -  It has one field of type ListItem called root.
 -  A constructor that takes a ListItem and initialises the field root with the newly passed in parameter.
 -  And five methods:
   -  getRoot(), getter for root.
  -  addItem(), same as MyLinkedList.
  -  removeItem(), same as MyLinkedList.
  -  performRemoval(), takes two ListItems, the item to be removed and its parent. It doesn't return anything and is declared as private. Call this method from removeItem() when the item is found.
    -  traverse(), takes the root as an argument and does not return anything. It uses recursion to visit all the branches in the tree (Inorder). Print each value on a seperate line.
                 TIP:  The rules for adding an item to the linked tree are:
                      If the head of the tree is null, make the head refer to the item to be added.
                      If the item to be added is less than the current item in the tree, add the item before the current item (i.e., make the previous item's "next" refer to the new item, and the new item's "next" refer to the current item).
                      If the item to be added is greater than the current item, move onto the next item and compare again (if there is no next item, then that is where the new item belongs).
            TIP:  When adding items to a Binary Search Tree, if the item to be added is less than the current item - then move to the left.
                     If it is greater than the current item - then move to the right.
                     The new item is added when an attempt to move in the required direction would involve following a null reference.
                     Once again, duplicates are not allowed.
            TIP:  Inorder = print the previous node, then the parent node, and then the next node (left -> node -> right).
            TIP:  Be extremely careful with the spelling of the names of the fields, constructors and methods.
            TIP:  Be extremely careful about spaces and spelling in the printed output from the traverse() method.
            NOTE:  All fields are private (unless stated otherwise).
            NOTE:  All constructors are public.
            NOTE:  All methods are public (unless stated otherwise).
            NOTE:  Do not add a main method to the solution code.""";
}
