Reflection: Assignment 2 vs Assignment 3

#### What is different about this design?

In assignment 2 we have 2 classes(Product and EtlPipeline) with most 
of the work(reading,transforming and creating a file) being done in 
the EtlPipeline with each different action being done in a method 
within the class. The data was passed between methods as primitive 
lists, such as `List<String[]>`.

In Assignment 3 the logic is moved from using mainly methods within a single
class to having a different classes for each unique action. These classes include:

- Product.java: A dedicated data class (POJO) that encapsulates all attributes 
of a product. This replaces the use of raw string arrays, making the data strongly typed 
and the code easier to read and maintain.

- CsvExtractor.java: Its sole responsibility is to read the CSV file and produce a 
list of `Product` objects. It knows nothing about transformations or writing files.

- ProductTransformer.java: Its sole responsibility is to apply business rules to a 
list of `Product` objects. It is decoupled from the source and destination of the data.

- CsvLoader.java: Its sole responsibility is to take a list of `Product` objects and 
write them to a CSV file.

- EtlPipeline.java: This class now acts as an orchestrator or a "client." Its job is 
to instantiate the other classes and coordinate their execution in the correct order.

#### How is Assignment 3 more object-oriented?

Assignment 3 is more object-oriented because it adheres to fundamental OO principles:

>Encapsulation: The Product class encapsulates product data and behavior (like
`toCsvString()`). Other classes don't need to know the internal details of a Product
 ;they just interact with its public methods. This hides complexity and protects the 
 data's integrity.
 
>Single Responsibility Principle (SRP): Each class now has one clear reason to 
 change. If the file reading logic needs to be updated (e.g., to support a different 
 format), only `CsvExtractor` changes. If a business rule changes, only `ProductTransformer` 
 is affected. This makes the system more modular and easier to modify without breaking unrelated parts.

>Clear Abstractions: The new classes provide clear abstractions for each phase of the ETL process. 
 One can understand the overall workflow just by reading the `main` method in `EtlPipeline`, without 
 getting bogged down in the details of CSV parsing or price calculations.

#### Which OO ideas did you use?

>Object & Class: The design is fundamentally built around classes (Product, CsvExtractor, etc.) 
 that are instantiated into objects at runtime.

>Encapsulation: As mentioned before, the Product class is the primary example, bundling data (attributes) 
 and methods together.

>Polymorphism/Inheritance: This wasn't used since it wasn't needed but , the structure is now set up to 
 easily accommodate them. For example, one could create a Transformer interface and implement different 
 transformation strategies (`ProductTransformer`, `SalesDataTransformer`), allowing the pipeline to be 
 extended polymorphically. Similar ideas could be put forward for the extractor class. 

#### How did  you test to confirm it works the same?

Tested the code with same edge cases used in Assignment 2(Missing Input,Empty Input and header row only)

Ran test where product.csv had the same inputs in assignment2 and assignment3 and used a file comparison tool
to check if the transformed_products.csv produced by assignment3 is the same as the one produced by 
assignment2.




