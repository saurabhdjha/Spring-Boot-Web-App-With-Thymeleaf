
#What is Thymeleaf?
------------------

> powerful server-side java template engine.
> capable of processing HTML, XML, JavaScript, CSS and even plain text.
> Mostly used to generate html views (ie. dynamic html pages) for web applications. 
> It's rendering is done at server side
> thymeleaf template (.html) = html + thymeleaf expression
> Thymeleaf engine parse thymleaf template. Put Dynamic Content where ever required.
  Example: <p th:text="${name}"> , Here name is java data (dynamic content. Therefore thymeleaf put it here dynamically)
  Output: Saurabh



#How to create and configure thymleaf ?
-------------------------------------
> add thymleaf dependency.
> add thymleaf templates inside "src/main/resources/templates" folder. [ why? As we know that Spring MVC requires view resolver in order to resolve views. Whenever Spring boot finds thymeleaf starter dependency in classpath, it auto configures view resolver for thymleaf. By default, spring boot will pick up thymleaf templates form templates folder]
> xmls:th="http://www.thymeleaf.org" add this plugin inside <html> tag , on every template that you create. 



#Working?
---------

step1: suppose i have created a method inside controller class:

@GetMapping(value="/homePage")
public String myHomePage()
{ 
   return "index";
}


Step 2: Now, i run this spring boot application.

Step 3: in browser:  http://localhost:8080/homePage

Step 4: ERROR comes up as output

Step 5: To solve this error, create html page with name: "index.html" inside templates folder, with content:  <p> Welcome to my home page </p>

step 6: Again run this project. browse: http://localhost:8080/homePage


OUTPUT: Welcome to my home page 

HOW? 
> Request goes to controller class -> mapped with @GetMapping(value="/homePage") -> myHomePage() method runs, which return string "index" -> thymleaf engine checks for availability of HTML file with name "index.html" inside templates folder. Runs it if present else throws error if not able to locate



#Learned About:-
----------------

(A) Model?

(B) How to Insert dynamic value =>  th:text="${variableName}"

(C) Whenever you have you use property of predefined object (like array, string etc. ), use #
    
    Example(Capitalize name): <h2> Name is <span th:text="${#strings.toUpperCase(name)}"> </span></h2>
                                                      ------------------------------
(D) to create local variables, use:-  th:with
    
    Example: <div th:with="a=5, b=7, n=${name}"> </div>, Now scope of a, b and n is within this span
               ------------------------------

(E) Thymeleaf iterations:

 th:each
 -------
 example:->
 
 Step 1: inside controller, 

 @GetMapping(value="/getNames")
 public String getNames(Model model)
 { List<String> names=List.of("Suraj","Ajay","Laxmi","Suman");
   model.addAttribute("names",names);
   return "iterateNames";
  }

 Step 2: create html page in "templates" folder with name "iterateNames.html", inside it write logic to iterate the list of names:
 
  <ul>
    <li th:each="n : ${names}">
      <span th:text="${n}"></span>
    </li>
  </ul>


 OUTPUT: 
              Suraj 
              Ajay 
              Laxmi 
              Suman
 
 
 Use of status:
 -------------
  <ul>
    <li th:each="n,status : ${names}">  [Note: this status can provide us: index, count, size, even/odd, first, last. Just use it like: 
      <span th:text="${status.index}+' '+${n}"></span>
    </li>
  </ul>

  OUTPUT: 
              1 Suraj 
              2 Ajay 
              3 Laxmi 
              4 Suman

  
(F) Conditional in thymeleaf:

    3 types: Elvis Operator (similar like ternary operator), If-Unless (like If-else) and Switch-case

    (i) Elvis Operator( ? : )
        Example:  <span th:text=" ${isActive} ? 'Yes Active' : 'Not Active' "></span>
     
   (ii) If-Unless( th:if  th:unless)  
        Example: <h1 th:if=" ${gender}=='M' ">Male</h1>
                 <h1 th:else=" ${gender}=='M' ">Female</h1> 

   (iii)Switch-Case
        Example: <div th:switch="${#list.size(mylist)}">
                     <h1 th:case="'0'">List contain 0 element</h1>
                     <h1 th:case="'1'">List contain 1 element</h1>
                     <h1 th:case="*">List contain more than 1 elements</h1>
                 </div>




*************PROJECT: Created Spring Boot + Thymeleaf project to perform CRUD operations***********************
             ----------------------------------------------------------------------------


################# UPDATE ############################

Implemented Paging and Sorting concept.
> On each page only 5 records will be displayed. 
> Made headers of table sortable by providing hyperlinks   
> provided toggling to change sorting back to original order.                                       