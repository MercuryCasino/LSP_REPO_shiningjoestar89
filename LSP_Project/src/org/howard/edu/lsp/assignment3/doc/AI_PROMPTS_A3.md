# AI Prompts and Responses for Assignment 3

This file contains prompts I used with Gemini AI to brainstorm the object-oriented redesign of my ETL pipeline.

---
### Prompt 1: Brainstorming Initial Class Structure

My Prompt:
"Here is this code, can you tell me how to make it more objected oriented"  here is Assignment 2 code

**AI Response Excerpt:**
"That's a great candidate for an object-oriented redesign! The key is to separate the different responsibilities of your program into distinct classes. Here's a common approach:

1. **Data Model Class:** Create a `Product` class to represent a single row of your data. Instead of passing around string arrays, you'll pass around `Product` objects. This makes your code type-safe and easier to read.
2. **Extractor Class:** Create a class like `CsvDataReader` or `Extractor`. Its only job is to read the input file and produce a `List<Product>`.
3. **Transformer Class:** Create a `Transformer` class. It will take the `List<Product>` and apply all your business logic to it.
4. **Loader Class:** Create a `CsvDataWriter` or `Loader` class. It takes the transformed `List<Product>` and writes it to the output file.
5. **Main/Pipeline Class:** Your main class will become much simpler. It will act as an orchestrator, creating instances of the other classes and calling their methods in the correct sequence (extract -> transform -> load)."

---
### Prompt 2: Generating Javadocs

My Prompt:
"Can you help me generate a Javadoc comments for these java classes" 

**AI Response**
"Certainly. Here is a Javadoc comment for your `CsvExtractor` class and its method:

java
/**
* Handles the 'Extract' phase of the ETL pipeline.
* Its responsibility is to read data from a CSV file and convert it into a list of Product objects.
*/
public class CsvExtractor {
/**
* Reads a CSV file and extracts product data.
*
* @param filePath the relative path to the input CSV file
* @return a list of Product objects representing the data rows
* @throws IOException if the file cannot be found or read
*/
public List<Product> extract(String filePath) throws IOException {
// ... your implementation ...
}
}

