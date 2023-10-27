package com.dcp.portone.service;

import com.dcp.portone.model.Book;
import com.dcp.portone.utils.Library;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LibraryServiceImpl implements MyInterface{
    private Set<Book> myBooksList = Library.BOOK_SET;
    public Set<Book> getAllBooks(){
        return myBooksList;
    }

    MyInterface fobj = (int x, int y) -> System.out.println(x+y);
    public Set<Book> getBooksByGenre(String arg){
        System.out.println("Genre :" + arg);
        Set<Book> booksByComic = (Set<Book>) myBooksList.stream().filter(b -> b.getGenre() == arg).collect(Collectors.toSet());
        if (booksByComic.isEmpty()) {
            System.out.println("No matching records found for the Genre :" + arg);
        }
        //(!booksByComic.isEmpty()) ? (booksByComic.stream().forEach(System.out::println)) : System.out.println("No matching records found for the Genre :" + arg);
        booksByComic.stream().forEach(System.out::println);
        return booksByComic;
    }

    public Map<String, List<Book>> getBooksByGroup(){
        Map<String, List<Book>> booksByGrp, booksByGrpParallelism;
        List<Book> booksByYearAfter;
        booksByYearAfter = myBooksList.stream().filter(bk -> bk.getYear() >= 2015).sorted(Comparator.comparing(b -> b.getPrice()))
                .sorted(Comparator.comparing(Book::getYear)).collect(Collectors.toList());
        booksByYearAfter.stream().forEach(System.out::println);
        booksByGrp =  myBooksList.stream().collect(Collectors.groupingBy(Book::getGenre));
        Map<String, Double> booksByGenAvgYearlySales =
                myBooksList.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.averagingDouble(Book::getYearlySales)));
        booksByGenAvgYearlySales.keySet().stream().sorted().forEach(genre->{
            System.out.print("Genre with Avg Yearly Sales: "+genre + " ");
            System.out.format("%10.2f%n ",booksByGenAvgYearlySales.get(genre));
            //System.out.println(booksByGenAvgYearlySales.get(genre));
        });
        booksByGrpParallelism = myBooksList.parallelStream().collect(Collectors.groupingBy(Book::getGenre));
        booksByGrp.keySet().stream().sorted().forEach(genre->{
            System.out.print("Genre: "+genre + "  ");
            System.out.println(booksByGrp.get(genre));
        });
        booksByGrpParallelism.keySet().stream().sorted().forEach(genre->{
            System.out.print("Genre: "+genre + "  ");
            System.out.println(booksByGrpParallelism.get(genre));
        });
        // The Collector parameter is called a downstream collector.
        // https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
        // https://docs.oracle.com/javase/tutorial/collections/streams/examples/ParallelismExamples.java
        // A pipeline that contains one or more downstream collectors, like this example, is called a multilevel reduction.
        // Map<Person.Sex, Double> averageAgeByGender = roster.stream().collect(Collectors.groupingBy(Person::getGender,Collectors.averagingInt(Person::getAge)));
        Map<String, Integer> myAuthBookSales = myBooksList.stream().collect(Collectors.groupingBy(Book::getAuthor, Collectors.reducing(0,Book::getYearlySales,Integer::sum)));
        myAuthBookSales.keySet().stream().sorted().forEach(art->{
            System.out.print("Author Name: " + art + "  ");
            System.out.println(myAuthBookSales.get(art));
        });
        Map<String, Double> avgPubPrice = myBooksList.stream().collect(Collectors.groupingBy(Book::getPublisher, Collectors.averagingDouble(Book::getPrice)));
        avgPubPrice.keySet().stream().sorted().forEach(pub->{
            System.out.print("Publisher Name and Avg price of all books: " + pub + "  ");
            System.out.format("%.2f%n",avgPubPrice.get(pub).doubleValue());
        });
        return booksByGrp;
    }

    public static final int NOT_FOUND = -1;
    public int binarySearch( Comparable [ ] a, Comparable x )
    {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while( low <= high )
        {
            mid = ( low + high ) / 2;
            if( a[ mid ].compareTo( x ) < 0 )
                low = mid + 1;
            else if( a[ mid ].compareTo( x ) > 0 )
                high = mid - 1;
            else
                return mid;
        }
        return NOT_FOUND;     // NOT_FOUND = -1
    }

    // Test program
    public void bscall() {
        int SIZE = 8;
        Comparable [ ] a = new Integer [ SIZE ];
        for( int i = 0; i < SIZE; i++ )
            a[ i ] = ( i * 2 );
        for( int i = 0; i < SIZE * 2; i++ )
            System.out.println( "Found " + i + " at " +binarySearch( a,( i ) ) );
    }

    public void ctwkfivegaOne(){
        // IntStream List<List<Integer>> lists = IntStream.rangeClosed(1, 3).boxed()
        //                .map(i -> Collections.nCopies(4, i))
        //                .collect(Collectors.toList());
        // Arrays.asList() and List.of() method creates an unmodifiable instance of the list
        // for mutable instance, wrap each list instance using the ArrayList constructor
        //List<List<Integer>> lists = new ArrayList<>(List.of(
        //        new ArrayList<>(List.of(1, 2, 3)),
        //        new ArrayList<>(List.of(4, 5, 6)),
        //        new ArrayList<>(List.of(6, 7, 8))));
        //List<List<Integer>> lists = new ArrayList<>();
        //        int M = 3;
        //        for (int i = 0; i < M; i++)  {
        //            lists.add(new ArrayList<>());
        //            lists.add(new ArrayList<>(Collections.nCopies(N, null)));
        //        }
        // List<List<Integer>> lists = Arrays.asList(Arrays.asList(1, 2, 3),Arrays.asList(4, 5, 6),Arrays.asList(6, 7, 8));
        // List<List<Integer>> lists = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(6, 7, 8));
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(0, 210),
                Arrays.asList(1, 198),
                Arrays.asList(2, 188),
                Arrays.asList(3, 173),
                Arrays.asList(4, 240));
        List<Integer> mList = new ArrayList<>();

        lists.forEach(l -> {
            mList.add(l.get(l.size()-1));
        });

        System.out.println("Last Element of Arrays: " + mList);

    }
/*
    malayalam  odd 9 mm aa ll aa y  returns ture
    malayalan   exiting the loop returning false
    elle ee   ll  returns true
    L = thinking  length = 8 t g  L thinking
            after ist iteration L = hinkin l=6  h n
            alfter 2nd iteration l = inki  l = 4 i i  returns true
    fist(first(lists)) = 0
    last((first(lists))) = 78
    rest(first(lists)) = [210,78]
    init(last(lists)) = [4,240]
    member of (l1, 5) = returns boolean false
*/
    // List functions by IITM
    // length counts the number of elements in the list   member of returns boolean if a numner exists in the list
    // first ( returns index[0], last returns index(length -1 ) rest -> all but first element init-> all but last

    // V L6.4  sorted list birthday - count similar items like no of
    public void sixthree(){
        List<String> authList = List.of("Aut1", "Aut1","Aut2","Aut2","Aut2","Aut3","Aut3");
        List<String> uniqueList = new ArrayList<>();
        uniqueList.add(authList.get(0));
        String prev = "";

        prev = authList.get(0);

        for( String x : authList) {
            if (!x.equals(prev)){
                uniqueList.add(x);
            }
            prev = x;
        }
        System.out.println("uniqueList: " + uniqueList);
    }

    public void ctwkfivegatwo(){
        List<List<Integer>> lists = List.of(
                List.of(0, 210, 78),
                List.of(1, 198, 91),
                List.of(2, 188, 77),
                List.of(3, 173, 78),
                List.of(4, 240, 89));

        List<Integer> mList = new ArrayList<>();

        List<List<Integer>> listsone = List.of(List.of(0, 210),List.of(1, 198),List.of(2, 188),List.of(3, 173),List.of(4, 240));

        listsone.forEach( e -> System.out.println(e));

        for ( List l : listsone) {
            Integer i = (Integer) l.get(1);
            System.out.println("1st Element of listone:  " + l + " is: " + i);
        }

        // mList = mList ++ [last(init(element))]  last but one

        lists.forEach(l -> {
            mList.add(l.get(l.size()-2));
        });

        System.out.println("Last Element of Arrays: " + mList);

    }

    public void ctwkfivegathree(){

    }

    public void testme() {

        Integer value = 0;
        Integer passingvalue = 5;
        Integer returnvalue = 0;


        returnvalue = dosomething(passingvalue);
    }

    Integer dosomething(Integer val) {
        val = val + 1000;

        return val;
    }

    public void ctwkfivegafourfive(){
        List<Integer> mylist1 = List.of(7,1,55,7);
        List<Integer> mylist2 = List.of(6,10,11,23,8,50);
        List<List<Integer>> lllist = List.of(mylist1,mylist2 );

        System.out.println("My new L of list is : " + lllist);

        Boolean found = false;
        List<Integer> mlist = new ArrayList<>();
        for (Integer one : mylist1) {
            for (Integer two : mylist2) {
                if (one == two) {
                    found = true;
                }
            }
            if(!found)
                mlist.add(one);
            found = false;
        }
        System.out.println("L3 is : " + mlist);
        System.out.println( " Count of L1: " + mylist1.size() + "  L2: " + mylist2.size() + "  L3: " + mlist.size());
    }
    public void ctwkfivegasvnegt(){
        String sent = "I ordered this product from Gitark. I am very happy to share my review regarding this awesome product. It is not only nice to use, but also has a very cool look. I think this is the best product which can be bought in this price range.";
        String[] sentArray = sent.split(" ");
        List<String> sentstream = new ArrayList<>(Arrays.asList(sentArray));
        //Arrays.stream(sentArray).forEach(System.out::print);
        //System.out.println(Arrays.stream(sentArray).collect(Collectors.joining(", ")));
        List<String> positiveList = new ArrayList<>(Arrays.asList("happy","awesome", "nice", "fine", "best", "cool"));
        AtomicReference<Integer> posSen = new AtomicReference<>(0);
        List<String> li = new ArrayList<>();

        for (String s: sentstream) {
            //
        }

        Arrays.stream(sentArray).forEach(s -> {
            s = s.replaceAll("[-+^,]*", "");
            li.add(s.toLowerCase());
            if (s.contains(".")) {

                li.forEach(a -> a.replaceAll(".", ""));
                // get unique words lu from l using Set
                //Set<String> uniqueWords = new HashSet<String>(li);
                List<String> ul = li.stream().distinct().collect(Collectors.toList());
                System.out.println(li.stream().collect(Collectors.joining(", ")));
                // get posCnt from comNo (positiveList, uniqueWords)

                // ul.retainAll(positiveList);
                List<String> newlist = ul.stream().filter(positiveList::contains).collect(Collectors.toList());
                Integer cnt =  ul.stream().filter(positiveList::contains).collect(Collectors.toList()).size();
                System.out.println("List : " + newlist + " Count : " + newlist.size() + " and cnt is: " + cnt);
                // if there are atleast 2 words matching increment posSen
                if (newlist.size() >= 2)
                    posSen.updateAndGet(v -> v + 1);
                // reset li to empty
                li.clear();
            }
        });
        System.out.println("No of Sentences with at least 2 words from postive list " + posSen );
    }

    /*
    Mona tells Sona that at least 50 percent of sentences have nouns just after an adjective. Sona writes the following pseudocode to find if Mona is right or not. At the end of the execution of the pseudocode given below, A stores True if Mona is right otherwise False. But Sona might have made mistakes in one or more lines. Identify such lines (if any). It is a Multiple Select Question (MSQ). Assume that there is at least one adjective in every sentence.
    A = False, trueCount = 0, totalCount = 0, posList = []
while(Table 1 has more rows){
    Read the first row X in Table 1
    posList = posList ++ [X.PartOfSpeech]
    if(X.Word ends with a full stop){
    	if(isTrue(posList) == 0){
    		trueCount = trueCount ++ [1]
    	}
    	else{
    		totalCount = totalCount + 1
    	}
        posList = []
    }
    Move X to Table 2
}
if(trueCount / totalCount >= 0.5){
	A = True
}

Procedure isTrue(L)
	count = 0
	while(length(L) >= 2){
		if(first(L) == "Adjective"){
			count = count + 1
			if(first(rest(L)) == "Noun"{
				count = count - 1
			}
		}
        L = rest(L)
	}
	return(count)
End isTrue
    */

    public void ctwkfivegaNineTen(){
        Integer[] array = {6,10,11,23,7,50};  // Share reference
        List<Integer> myListfromarray = Arrays.asList(new Integer[] {6,10,11,23,7,50});
        List<Integer> mystreamlist = Stream.of(6,10,11,23,7,50).collect(Collectors.toList());
        List<Integer> mylistfactory = List.of(6,10,11,23,7,50);  // Factory Method immutable, threadsafe and efficient
        List<Integer> listAnonymousInnercalss_thenInitialise = new ArrayList<>() {{
            add(6); add(10); add(11); add(23); add(7); add(50);      // anti pattern
        }};

        List<Integer> list = Arrays.asList(new Integer[] {157,157,11,23,7,50,131,80,157,183});
        List<Integer> mlist = new ArrayList<>();

        AtomicReference<Integer> i = new AtomicReference<>(1);
        myListfromarray.forEach(l -> {
            if (doSomething(i.get()) && doSomething(l)){
                mlist.add(l);
            }
            i.updateAndGet(v -> v + 1);
        });
        System.out.println();
        System.out.println("MList is : " + mlist);
    }

    Boolean doSomething(Integer x) {


        Integer j = 2;
        Boolean flag = true;

        if (x == 1) return false;

        while (j<x){
            //System.out.print("i  :" + x + "  ");
            if ((x % j) == 0) {
                System.out.print("x :" + x + "  ");
                return false;
            }
            j += 1;
        }
        System.out.println();
        return flag;
    }

    @Override
    public void abstract_func(int x, int y) {
        System.out.println(x+y);
    }

    //your bicycle is an instance of the class of objects known as bicycles
    // Q: A sequence of aggregate operations is known as a Pipeline, intermediate and terminal operations, lamda expression, method reference
    // Stream.reduce always creates a new value when it processes an element. Stream.collect modifies (or mutates) the existing value.
    // .filter(a -> a.tracks.anyMatch(t -> (t.rating >= 4)))
    // int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary)));
    // String joined = elements.stream().map(Object::toString).collect(Collectors.joining(", "));
    // .collect(Collectors.toSet()); .collect(Collectors.toCollection(TreeSet::new)); Collection<Type> noDups = new LinkedHashSet<Type>(c);
    // // Partition students into passing and failing   Map<Boolean, List<Student>> passingFailing = students.stream()
    //                                                              .collect(Collectors.partitioningBy(s -> s.getGrade()>= PASS_THRESHOLD));
    // Cascade Collectors Map<String, Map<String, List<Person>>> peopleByStateAndCity = personStream.collect(Collectors.groupingBy(Person::getState, Collectors.groupingBy(Person::getCity)))
    // Name three different ways to iterate over the elements of a List. //Answer: You can iterate over a List using streams, the enhanced for statement, or iterators.
    // https://docs.oracle.com/javase/8/docs/api/index.html

    // Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };  List<Integer> listOfIntegers =  new ArrayList<>(Arrays.asList(intArray));
    // listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
    // Comparator<Integer> normal = Integer::compare;
    //Comparator<Integer> reversed = normal.reversed();
    // Collections.sort(listOfIntegers, reversed);
    // listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
    // listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
    // listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
    // listOfIntegers.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
    // The reference to a static method holds the syntax ContainingClass::methodName
    // boolean isReal = list.stream().anyMatch(u -> User.isRealUser(u));
    // boolean isReal = list.stream().anyMatch(User::isRealUser);
    // reference to an instance method holds the syntax containingInstance::methodName.
    // User user = new User();  boolean isLegalName = list.stream().anyMatch(user::isLegalName);
    // Reference to an Instance Method of an Object of a Particular Type   ContainingType::methodName
    // long count = list.stream().filter(String::isEmpty).count();
    // Reference to a Constructor  --> ClassName::new.   Stream<User> stream = list.stream().map(User::new);
    // Optional<String> optional = Optional.empty(); String str = "value";  Optional<String> optional = Optional.of(str);
    // Optional<String> optional = Optional.ofNullable(getString());
    // List<String> listOpt = getList().orElseGet(() -> new ArrayList<>());



}
