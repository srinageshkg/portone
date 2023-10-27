package com.dcp.portone.service;

import com.dcp.portone.entity.Product;
import com.dcp.portone.entity.Shoppingbill;
import com.dcp.portone.entity.Store;
import com.dcp.portone.model.StoreModel;
import com.dcp.portone.model.response.ProductRest;
import com.dcp.portone.model.response.ShopRest;
import com.dcp.portone.repository.ShoppingbillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingbillServiceImpl implements ShoppingbillService {
    @Autowired
    private ShoppingbillRepository shoppingbillRepository;

    public Set<ShopRest> getAllBills() {
        Set<ShopRest> shoppingBills = new HashSet<>();

        //for (Shoppingbill bill : shoppingbillRepository.findAll()){
        // Iterating Over Bills
        shoppingbillRepository.findAll().forEach(bill -> {
            //AtomicReference<Float> totalAmt = new AtomicReference<>(0F);

            Set<ProductRest> prs = new HashSet<>();

            Float streamMapReduceClassMethosTotal = bill.getProducts().stream().map(Product::getCost).reduce((float) 0, Float::sum);
            System.out.println("BILL TOTAL: "+streamMapReduceClassMethosTotal);

            Float streamReduceTotal = bill.getProducts().stream().map(p -> p.getPrice()).reduce((float)0,(a,b) -> a+b);
            System.out.println("StreamMapReduceTotalab :" + streamReduceTotal);

            Float streamMapReduceCustomMethodTotal = bill.getProducts().stream().map(Product::getCost).reduce((float)0,ArithmaticUtils::add);
            System.out.println("streamMapReduceCustomMethodTotal :" + streamMapReduceCustomMethodTotal);

            Float streamCollectTotal = bill.getProducts().stream().map(Product::getPrice).reduce((float) 0, Float::sum);

            ShopRest sbilltmp = getBillByShopRest(bill);

            /*
            // Iterating Over Products
            bill.getProducts().forEach(product -> {
                ProductRest prRest = ProductRest.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .category(product.getCategory())
                        .quantity(product.getQuantity())
                        .cost(product.getCost())
                        .price(product.getPrice())
                        .build();
                //totalAmonut.updateAndGet(v -> v + product.getCost());
                //totalAmt.updateAndGet(v -> v + product.getCost());
                prs.add(prRest);
                System.out.println("getAllBills:Product Name: " + prRest.getProductName());
            });

            ShopRest sbilltmp = ShopRest.builder()
                    .Id(bill.getId())
                    .shopName(bill.getStoreName())
                    .custName(bill.getCustomerName())
                    .dateCreated(bill.getDateCreated())
                    //.totalAmount(totalAmt.get())
                    .totalAmount(bill.getProducts().stream().map(Product::getPrice).reduce((float) 0, Float::sum))
                    .products(prs)
                    .build();

            sbilltmp.setId(bill.getId());
            sbilltmp.setShopName(bill.getStoreName());
            sbilltmp.setCustName(bill.getCustomerName());
            sbilltmp.setDateCreated(bill.getDateCreated());

            sbilltmp.setProducts(prs);
            //sbilltmp.setProducts(bill.getProducts());
            */
            shoppingBills.add(sbilltmp);
        });
        return shoppingBills;
    }
    public ShopRest getBillByShopRest(Shoppingbill sBill){
        ShopRest shopbill = ShopRest.builder()
                .Id(sBill.getId())
                .shopName(sBill.getStoreName())
                .custName(sBill.getCustomerName())
                .dateCreated(sBill.getDateCreated())
                .totalAmount(sBill.getProducts().stream().map(Product::getPrice).reduce((float)0,(a,b) -> a+b))
                .build();

        StoreModel storeModel = StoreModel.builder()
                .storeName(sBill.getStoreName())
                .location("Plano")
                .build();

        Set<ProductRest> productRestSet = new HashSet<>();
        sBill.getProducts().forEach((bp) -> {
            ProductRest productRest =
                    ProductRest.builder()
                            .id(bp.getId())
                            .productName(bp.getProductName())
                            .category(bp.getCategory())
                            .quantity(bp.getQuantity())
                            .cost(bp.getCost())
                            .price(bp.getPrice())
                            .build();
            productRestSet.add(productRest);
            System.out.println("getShoppingbillById:Product Name: " + productRest.getProductName());
        });
        shopbill.setProducts(productRestSet);

        return shopbill;
    }

    public Set<ShopRest> getShoppingbillByStore(String store) {
        Set<ShopRest> shopRestSet = new HashSet<>();

        shoppingbillRepository.findByStoreNameIgnoreCaseContaining(store).forEach(s -> {
            shopRestSet.add(getBillByShopRest(s));
        });

        return shopRestSet;
    }

    public ShopRest getShoppingbillById(Long Id) {

        Shoppingbill sBill = shoppingbillRepository.getShoppingbillById(Id);
        Set<ProductRest> productRestSet = new HashSet<>();
        ShopRest shopbill = ShopRest.builder()
                .Id(sBill.getId())
                .shopName(sBill.getStoreName())
                .custName(sBill.getCustomerName())
                .dateCreated(sBill.getDateCreated())
                .totalAmount(sBill.getProducts().stream().map(Product::getPrice).reduce((float)0,(a,b) -> a+b))
                .build();

        sBill.getProducts().forEach((bp) -> {
            ProductRest productRest =
                    ProductRest.builder()
                            .id(bp.getId())
                            .productName(bp.getProductName())
                            .category(bp.getCategory())
                            .quantity(bp.getQuantity())
                            .cost(bp.getCost())
                            .price(bp.getPrice())
                            .build();
            productRestSet.add(productRest);
            System.out.println("getShoppingbillById: " + productRest.getId() + "Product Name: " + productRest.getProductName());
        });
        shopbill.setProducts(productRestSet);

        return shopbill;
    }

    // JPQL
    //@Query("select s from Student s where s.mathsMarks > :?1 and s.physicsMarks > ?2 and s.chemistryMarks > ?3 ")
    //public List<Student> findByGreaterThanMarks(Integer m1, Integer m2, Integer m3);
    //@Query( value = "select * from tabl_student", nativeQuery = true)

    //Integer sum = integers.reduce(0, Integer::sum);
    //<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
    //final TreeMultimap<String, Application> results = applications
    //  .stream()
    //  .reduce(TreeMultimap.create(String.CASE_INSENSITIVE_ORDER,
    //          (o1, o2) -> o1.getIssue().getKey().compareTo(o2.getIssue().getKey())), (map, application) -> {
    //      map.put(application.getIssue().getProjectObject().getName(), application);
    //      return map;
    //  }, (map, map2) -> map);
    //final Map<Project, Collection<Application>> results = applications
    //  .stream()
    //  .collect(Collectors.groupingBy(
    //    (application) -> application.getIssue().getProjectObject(),
    //    () -> new TreeMap<>(Named.NAME_COMPARATOR),
    //    Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> o1.getIssue().getKey().compareTo(o2.getIssue().getKey())))));
    // @Query("Select c from Registration c where c.place like %:place%").
    // @Query("Select s from shoppingbills s where s.customer_name like %:customerName%")

    @Query("select s from Shoppingbill s where s.customer_name = ?1")
    public List<Shoppingbill> getShoppingbillByCustomerName(String customerName){
        List<Shoppingbill> sBills = shoppingbillRepository.findByCustomerName(customerName);
                //.orElseThrow(() -> new ResourceNotFoundException("No bills found for cust: " + customerName));

        return sBills;
    }

    //@Query("select s from Shoppingbill s where s.customer_name = ?1")
    public List<Shoppingbill> getShoppingbillByCustomerNameContaining(String customerName){
        List<Shoppingbill> sBills = shoppingbillRepository.findByCustomerName(customerName);
        //findByCustomerNameIgnoreCaseContaining(customerName);
        //ShopRest shopbill = getBillByShopRest(sBill);
        return sBills;
    }

    @Query("select s from shoppingbills s where s.store_name = ?1 and s.customer_name = ?2")
    public List<Shoppingbill> findByStoreAndCustomerName(String storeName, String cust) {
        List<Shoppingbill> sBills = shoppingbillRepository.findByStoreNameAndCustomerName(storeName, cust);

        return  sBills;
    }

    public Shoppingbill saveShoppingBill(ShopRest sr){
        Set<Product> productSet = new HashSet<>();

        sr.getProducts().forEach(srp -> {
            Product product = Product.builder()
                    .price(srp.getPrice())
                    .category(srp.getCategory())
                    .productName(srp.getProductName())
                    .cost(srp.getCost())
                    .quantity(srp.getQuantity())
                    .build();
            productSet.add(product);
        });

        Store store = Store.builder()
                .storeName(sr.getStore().getStoreName())
                .location(sr.getStore().getLocation())
                .build();

        Shoppingbill shoppingbill = Shoppingbill.builder()
                .customerName(sr.getCustName())
                .storeName(sr.getShopName())
                .products(productSet)
                .store(store)
                .build();

        // Check if the Store already exists - if yes, update else insert

        List<Shoppingbill> sbills = findByStoreAndCustomerName(shoppingbill.getStoreName(), shoppingbill.getCustomerName());

        if (sbills.isEmpty()) {
            return shoppingbillRepository.save(shoppingbill);
        } else {
            return sbills.get(0);
        }
        // Ckeck if the customer is old - if yes just add new items for the existing customer

    }
}

