# Spring-Hibernate-for-Beginners

<h3>Inversion of control:</h3>
<ul>
    <li>Outsource to an object factory</li>
    <li>The approach of outsourcing the construction & management of objects</li>
</ul>

<h3>Spring Container: Primary functions</h3>
<ul>
    <li>Create & manage objects (IOC)</li>
    <li>Inject object's dependency (DI) via Constructor || Setter Injection</li>
</ul>

Configure Spring Container: XMl || Java Annotation || Java Source Code

<h3>Spring Development :</h3>
<ul>
    <li>Configure Spring Beans (XML || Java Annotation..)</li>
    <li>Create Spring Container or Application Context from ClassPathXml || AnnotationConfig...</li>
    <li>Retrieve Beans from Spring Container</li>
</ul>

<h3>Note: </h3>
<ul>
    <li>Spring Container is also knows as Application Context</li>
    <li>The Spring container handles the creation, initialization, and destruction of all the Beans with the help of the ApplicationContext</li>
    <li>Spring will scan for Java Classes for special Annotation</li>
    <li>Automatically register the Beans in the Spring Container</li>
</ul>

<h3>Bean Scope: Default is Singleton</h3>
<ul>
    <li>Singleton</li>
    <li>Prototype</li>
    <li>Request : Http Web Request</li>
    <li>Session : Http Web Session</li>
    <li>Global Session : Http Web Session</li>
</ul>


<h3>Bean Lifecycle: </h3>
<ul>
    <li>Container Started</li>
    <li>Bean Instantiated</li>
    <li>Dependencies Injected</li>
    <li>Internal Spring Processing</li>
    <li>Your Custom Init Method</li>
    <li>Bean is ready for use</li>
    <li>Your Custom Destroy Method</li>
    <li>Container Shutdown</li>
</ul>

<h3>Note: </h3>
<ul>
    <li>For "prototype" scoped beans, Spring does not call the destroy method</li>
    <li>Spring does not manage the complete lifecycle of a prototype bean:</li>
    <li>The container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance</li>
    <li>Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding</li>
    <li>You can destroy prototype beans but custom coding is required</li>
</ul>

<h3>Java Annotation: </h3>
<ul>
    <li>Enable Component scanning int the Spring Config XML File or @ComponentScan(base-package optional) to scan, with @Configuration</li>
    <li>Spring will scan for Java Classes for special Annotation @Component</li>
    <li>We can give name for Beans, or we can use default bean id camel-case</li>
    <li>We can use constructor, setter or field injection using @Autowired</li>
    <li>If we have multiple implementations then we can use @Qualifier annotation</li>
    <li>For Bean Scope use @Scope("singleton) or @Scope("prototype)</li>
    <li>For Bean custom init use @PostConstruct and destroy use @PreDestroy</li>
    <li>Apart from @Component, we can also use @Bean annotation with @Configuration, for creating beans and use method name for injection</li>
    <li>To read property from property file from class path we can use @PropertySource("classpath:custom.properties") with @Configuration & for use @Value("${custom-name}")</li>
    <li>Automatically register the Beans in the Spring Container</li>
    <li>Retrieve Bean from Spring Container</li>
</ul>

<h3>Note: </h3>
<ul>
    <li>As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. However, if several constructors are available, at least one must be annotated to teach the container which one to use.</li>
    <li>If the annotation's value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased)</li>
    <li>Example HappyFortuneService --> happyFortuneService</li>
    <li>However, for the special case of when BOTH the first and second characters of the class name are upper case, then the name is NOT converted.</li>
    <li>Example RESTFortuneService --> RESTFortuneService</li>
    <li>Constructor Injection: You have to place the @Qualifier annotation inside of the constructor arguments</li>
    <li>When using Java 9 and higher, javax.annotation has been removed from its default classpath, Download the javax.annotation-api-1.3.2 jar</li>
    <li>We can use @Bean to make an existing third-party class available to your Spring framework application context</li>
    <li>Example AWS SDK for S3Client we can wrap this object with @Bean and @Autowire where needed</li>
</ul>

<h3>Spring MVC: </h3>
<ul>
    <li>Based on Model-View-Controller design pattern</li>
    <li>Web Request -> Front Controller (DispatcherServlet) delegates request to -> Controller Class we write + Business Logic + DAO -> Response Send Back </li>
    <li>@RequestParam("param-name") String name reads query param like /api?name=Rahul & bind it with String name</li>
    <li>@RequestMapping can be used on Controller Class level or Controller Class Method Level, if used in both place then it will be nested</li>
    <li>We can add validation on the DTO like @NotNull @Min @Max @Size @Pattern(regular-expression) even Custom Validations ...</li>
    <li>@Valid will perform the validation rules & BindingResult (hasError()) has the result of validation</li>
    <li>@InitBinder works as a PreProcessor, it will pre-process each web request to our controller, method annotated with @InitBinder in Controller Class is executed</li>
    <li>Example if we need to trim the whitespace in string before request reaches to the particular request mapping</li>
</ul>

<h3>Hibernate: </h3>
<ul>
    <li>Hibernate uses JDBC (Driver) for all database communications</li>
    <li>Java Client -> Hibernate -> JDBC -> Database</li>
    <li>SessionFactory/EntityManagerFactory create sessions/entity-manager objects, create connection-pool, create transaction-integration, create second level cache</li>
    <li>Session/EntityManager wraps a jdbc connection, used for crud on objects, short-lived object, retrieved from session factory</li>
    <li>Create SessionFactory -> Get Session from SessionFactory -> Begin Transaction -> CRUD on Object -> Commit Transaction -> Close SessionFactory</li>
    <li>SQL vs HQL : SQL works on Table/Column & HQL works on Class/Attributes rest Query will be same like WHERE, LIKE, OR ...</li>
    <li>Example HQL: "SELECT from Student s WHERE s.firstName LIKE '%rahul' "  here Student is class & firstName is Attribute of Student Class</li>
    <li>When we update the object with in a transaction we don't need to call update/save as dirty checking will see the changes and fire the update query</li>
    <li>Example you fetch an object from database session is created within that session we have the object as well as we're going to have a clone/proxy of same object, when we call setter on that object then at end dirty check will check these 2 object</li>
    <li>If we create session fetch some object then close the session and call setter on object to update, it won't work as session is closed but if we create another session and attach this object by fire session.update(object) then it will check the object with database, if both are different it will fire update query</li>
    <li>If we create session fetch some object then close the session and call setter on object to update, again we open new session and fetch same object (now we have 2 object one detach which we updated & one in persistent state) if we fire session.update(old object) then we will get exception NonUniqueObjectException, in this case we need to fire session.merge(old object)</li>
    <li></li>
</ul>

<h3>Entity Life Cycle: </h3>
<ul>
    <li>Detach: if entity is detached, it is not associated with hibernate session</li>
    <li>Merge: if instance is detached from session, then merge will reattach to session</li>
    <li>Persist: transition new instance to managed state, next flush/commit will save in db</li>
    <li>Remove: transition managed entity to be removed, next flush/commit will remove from db</li>
    <li>Refresh: reload/sync object with data from db, prevent stale data</li>
</ul>

<h3>Default Fetch Type: Eager & Lazy</h3>
<ul>
    <li>@OneToOne: FetchType.EAGER</li>
    <li>@OneToMany: FetchType.LAZY</li>
    <li>@ManyToOne: FetchType.EAGER</li>
    <li>@ManyToMany: FetchType.LAZY</li>
</ul>

<h3>One To One: </h3>
<ul>
    <li>Instructor & Instructor Detail</li>
</ul>

<h3>One To Many: </h3>
<ul>
    <li>Instructor & Courses: Instructor can have many courses</li>
</ul>

<h3>Many To Many: </h3>
<ul>
    <li>Courses & Instructor: Many courses can have 1 Instructor</li>
</ul>

<h3>Mapping: </h3>
<ul>
    <li>Instructor @OneToOne InstructorDetail</li>
    <li>Instructor @OneToMany Course</li>
    <li>Course @ManyToOne Instructor</li>
    <li>Course @OneToMany Reviews</li>
    <li>@JoinColumn: 
        <ul>
            <li>If the join is for a OneToOne or ManyToOne mapping then source entity</li>
            <li>If the join is for a unidirectional OneToMany mapping then target entity</li>
            <li>If the join is for a ManyToMany mapping or for a OneToOne or bidirectional ManyToOne/OneToMany mapping then join table</li>
            <li>If the join is for an element collection then in collection table</li>
        </ul>
    </li>

</ul>

<h3>JPA Buddy: https://www.youtube.com/watch?v=DC6FrC4olhE </h3>
<ul>
    <li>Use Education Licence</li>
    <li>Generate Entity from Database: Right Click on base package -> New -> JPA Entities from DB -> Update Entity Package to use entity then select tables</li>
    <li>Update Entity: Drag and Drop the Type from Jpa Buddy Palate & Set rule using jpa inspector it will show details when we click on attributes</li>
    <li>Create New Entity: Right click -> New -> JPA Entity</li>
    <li>Right click on Entity name -> show context action -> create jpa repository -> update package name</li>
    <li>Generate methods in Repository -> Option + Enter -> Give attribute name using + icon</li>
    <li>Generate Index on Entity -> Right click on id -> show context actions -> index/constraint by id attribute</li>
    <li>Create DTO -> Right click on entity student -> show context actions -> create dto -> update package name -> + icon click and create mappers for student dto</li>
    <li>In Api -> Control + Enter -> Autowire dependencies</li>
    <li> DB Migration: Liquibase
        <ul>
            <li>Check Liquibase Doc/Course</li>
        </ul> 
    </li>
</ul>

<h3>AOP: Proxy Design Pattern</h3>
<ul>
    <li>Aspect encapsulate cross-cutting logics like logging, security, transactions, audit logs(who,what,when,where), exception handling, api management</li>
    <li>Same Aspect Class (logging, security) can be applied to multiple locations based on configuration</li>
    <li>Code for Aspect is defined in a single class</li>
    <li>Business code in application will be cleaner</li>
    <li>Configurable, based on configuration we can apply aspect to multiple locations </li>
    <li></li>
</ul>

<h3>AOP Terminology: </h3>
<ul>
    <li>Aspect: module of code for cross-cutting concern like logging, security</li>
    <li>Advice: what action is taken and when it should be applied</li>
    <li>Joint Point: when to apply code during program execution</li>
    <li>Pointcut: predicate expression for where advice should be applied</li>
</ul>

<h3>Advice Type</h3>
<ul>
    <li>@Before Advice: run before the method</li>
    <li>@AfterFinally Advice: rin after the method (finally)</li>
    <li>@AfterReturning Advice: run after the method (success execution)</li>
    <li>@AfterThrowing Advice: run after method (if exception thrown)</li>
    <li>@Around Advice: run before and after method</li>
</ul>

<h3>Weaving: </h3>
<ul>
    <li>Connecting aspects to target objects to create an advice object</li>
    <li>Type of weaving: compile-time, load-time, run-time</li>
    <li>Performance: runtime weaving is the slowest</li>
</ul>

<h3>Spring AOP vs AspectJ: </h3>
<ul>
    <li>Spring AOP:
        <ul>
            <li>Support only method level join points</li>
            <li>run-time code weaving (slower than aspectJ very negligible)</li>
            <li>light weight implementation of AOP</li>
            <li>solves common problem in enterprise application</li>
        </ul>
    </li>
    <li>AspectJ:
        <ul>
            <li>Support method-level, constructor, field join points</li>
            <li>run-time, compile-time, post compile-time & load time code weaving</li>
            <li>if we have complex requirement then we can use AspectJ</li>
        </ul>
    </li>
</ul>
<p>Books: AspectJ in Action by Raminvas Laddad & Aspect-Oriented Development with the use case by Ivar Jacobson & Pan-wei ng</p>


<h3>Spring AOP Examples: </h3>
<ul>
    <li>execution(modifier-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern? ) </li>
    <li>all ? are optional</li>
    <li>@Before("execution(public void com.spring.hibernate.aop.AccountDao.addAccount())")</li>
    <li>@Before("execution(public void addAccount())"): All addAccount() method</li>
    <li>@Before("execution(public void add*())"): All method starts with add</li>
    <li>@Before("execution(public VerificationResult processCreditCard*())"): All method starts with processCreditCard & return type VerificationResult</li>
    <li>@Before("execution(public * processCreditCard*())"): All method starts with processCreditCard & return type any</li>
    <li>@Before("execution(* processCreditCard*())"): All method starts with processCreditCard & return type any</li>
    <li>Param Pattern: () - matches a method with no param</li>
    <li>Param Pattern: (*) - matches a method with 1 argument of any type</li>
    <li>Param Pattern: (..) - matches a method with 0 or more argument of any type</li>
    <li>@Before("execution(* addAccount(com.spring.hibernate.aop.Account))")</li>
    <li>@Before("execution(* addAccount(..))")</li>
    <li>@Before("execution(* addAccount(com.spring.hibernate.aop.Account, ..))")</li>
    <li>Package Pattern: @Before("execution(* com.spring.hibernate.aop.*.addAccount(..))") : Any class under this package</li>
    <li>Package Pattern: @Before("execution(* com.spring.hibernate.aop.*.*(..))") : Any Class & Any Method under this package</li>
</ul>

