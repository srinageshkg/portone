package com.dcp.portone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com/dcp/ifs", "com/dcp/dss"}
)
public class PortoneApplication {
	public static void main(String[] args) {

		//CoreJava cj = new CoreJava();
		//cj.funcinterface();
		//ArrayDemo ad = new ArrayDemo();
		//ad.demo();
		//Solution mys = new Solution();
		//mys.twoSum();
		//mys.twoSumi();
		//ThreePrizes tp = new ThreePrizes();
		//tp.streamexamples();
		//StringOperations sss = new StringOperations();
		//sss.examples();

		//LibraryServiceImpl lb = new LibraryServiceImpl();
		//lb.sixthree();
		//lb.testme();
		//lb.getBooksByGenre("Horor");
		//lb.getBooksByGroup();
		//lb.ctwkfivegasvnegt();
		//lb.ctwkfivegafourfive();

		//Institute inst = new Institute();

		//lb.ctwkfivegatwo();
		SpringApplication.run(PortoneApplication.class, args);
		/*@Bean
		public CommandLineRunner commandLineRunner(String[] args) {
			return runner -> { System.out.println("Helllllo"); };
		}
*/
		//try(ConfigurableApplicationContext configurableApplicationContext =
		//			SpringApplication.run(PortoneApplication.class, args)) {
			//Institute inst = configurableApplicationContext.getBean(Institute.class);
		//	ShoppingbillServiceImpl shoppingbillService = configurableApplicationContext.getBean(ShoppingbillServiceImpl.class);
		//}


		//OrgService orgService = configurableApplicationContext.getBean(OrgService.class);
		//ShoppingbillRepository shoppingbillRepository = configurableApplicationContext.getBean(ShoppingbillRepository.class);
		//		WordVowel vw = new WordVowel(); 		vw.wordVowelCount();
		//		StudyPairs sp = new StudyPairs();  		sp.findStudyPairs();

		/*
		BigDecimal totalcost = new BigDecimal(5000);
		Shoppingbill shoppingbill = new Shoppingbill(2001L, "BigBasket", "Supraja", totalcost);

		Product product1 = new Product(5000L, "Chicken", "Food", new BigDecimal(6), new BigDecimal(13.99), new BigDecimal(199.99));
		Product product2 = new Product(5001L, "Fan", "Furniture", new BigDecimal(1), new BigDecimal(33.99), new BigDecimal(33.99));

		Set<Product> items = Set.of(product1, product2);

		shoppingbill.setProducts(items);
		shoppingbillRepository.save(shoppingbill);
		*/
	}
}

