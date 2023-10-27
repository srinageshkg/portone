package com.dcp.portone.utils;

import com.dcp.portone.model.Book;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Library {

    public static final Set<Book> BOOK_SET = new HashSet<>(Arrays.asList(
            Book.builder()
                    .rowNo(0L).name("Name 1").author("Author 1").genre("Comic")
                    .language("English").pages(600).publisher("AK").year(2002).yearlySales(450).price(199.99D)
                .build(),
            Book.builder()
                    .rowNo(1L).name("Name 2").author("Author 2").genre("Horror")
                    .language("French").pages(550).publisher("RK").year(2010).yearlySales(250).price(249.99D)
                    .build(),
            Book.builder()
                    .rowNo(2L).name("Name 3").author("Author 1").genre("Drama")
                    .language("English").pages(345).publisher("SV").year(2012).yearlySales(215).price(129.99D)
                    .build(),
            Book.builder()
                    .rowNo(3L).name("Name 4").author("Author 2").genre("Comic")
                    .language("German").pages(500).publisher("AK").year(2002).yearlySales(145).price(99.99D)
                    .build(),
            Book.builder()
                    .rowNo(4L).name("Name 5").author("Author 3").genre("Drama")
                    .language("English").pages(550).publisher("RK").year(2005).yearlySales(475).price(59.99D)
                    .build(),
            Book.builder()
                    .rowNo(5L).name("Name 6").author("Author 2").genre("Horror")
                    .language("French").pages(190).publisher("AK").year(2016).yearlySales(356).price(19.99D)
                    .build(),
            Book.builder()
                    .rowNo(6L).name("Name 7").author("Author 4").genre("Fantacy")
                    .language("English").pages(435).publisher("SV").year(2012).yearlySales(458).price(159.99D)
                    .build(),
            Book.builder()
                    .rowNo(7L).name("Name 8").author("Author 2").genre("Comic")
                    .language("English").pages(245).publisher("SV").year(2022).yearlySales(1435).price(89.99D)
                    .build(),
            Book.builder()
                    .rowNo(8L).name("Name 9").author("Author 3").genre("Comic")
                    .language("Dutch").pages(245).publisher("SV").year(2016).yearlySales(1435).price(192.99D)
                    .build(),
            Book.builder()
                    .rowNo(9L).name("Name 10").author("Author 4").genre("Horror")
                    .language("English").pages(245).publisher("SV").year(2017).yearlySales(1435).price(27.99D)
                    .build()
    ));
}
