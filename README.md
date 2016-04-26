# multipleStringMatching
This java program searches a supplier name in a given invoice file according to strings specified in a supplier names' file.

# Format
The supplier name file is assume to be given in a CSV format as follows:

Id,SupplierName

298972,supplier name 1


The invoice file is assumed to be given in a Python output format as follows:

{'pos_id': 0, 'cspan_id': 2, 'rspan_id': 0, 'right': 12.04, 'word': '(PI', 'line_id': 0, 'top': 3.64, 'height': 5.64, 'width': 7.65, 'left': 4.39, 'page_id': 1, 'word_id': 2}

# Execution
The easiest option for executing the program is to download the provided multiStringMatching-all-1.0.jar file and run it using:
java -jar multiStringMatching-all-1.0.jar -sf supplierfile.txt -if invoicefile.txt

# Code inspection
A UML class diagram for the underlying class model is given in model.png and could be a good starting point for reviewing the code.

Javadoc generated documentation is available under multipleStringMatching/doc

UnitTests are available under multipleStringMatching/src/test/java

# Building
The program is configured to be built with gradle. Example gradle tasks:
- gradle test - run the unit tests
- gradle fatJar - build the jar file including all the needed dependencies
- gradle javadoc - generate java documentation from the source code

# Algorithms
The program uses the Aho-Corasick algorithm to efficienty solve the string search problem with multiple patterns.
This allows the program to run on hundreds of thousands of supplier names as requested, and even to scale
beyond that using cluster friendly implementations of the algorithm.
More in depth information is provided in the javadoc / source code.

# Dependencies
The program depends on multilpe third-party libraries, inculding CSV parser, JSON parser, and Aho-Corasick multi-threaded implementation. 
The full list is available in the gradle build script multipleStringMatching/gradle.build

# Limitations
While the Python output wrapps fields in the invoice file with single quote, the JSON standard specifies double quotes.
To parse the Python output with the JSON library, the single quote is replaced with a double quote.
This conversion creates a limitation where the supplier name's cannot contain a single quote.
The limitation can be easily addressed by producing an invoice file that is JSON-compatible

# License
This code is distributed under the GNU General Public License v3.0
