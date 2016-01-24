1. Used software
	- I worked under Windows using Eclipse
	- jdk used is jdk1.8.0_60
	- some java classes have Hibernate used classes imported, but they are only like a check for me, that they are working.
2. General considerations on problem:
	- I had try to think the solution with elements already prepared to be introduced in a database using Hibernate,
			since in a real world for sure this will be the case, not a HashMap, like I used here
	- for some of the field values I used "double" type. I know that depending on case, in Java this will not return
			a exact value on calculations, but for this example I think that this is ok
	- for not critical cases, but just for user messages, or for log files, I preferred to throw verified Exceptions.
			These exceptions will be catch, and show like user messages, in a GUI.
			
3. Used objects
	- POJO's:  StockObject and TradeRecord
	- these two objects were written to be added to a database
	- for these two classes were added "Calcul's" classes, and HashMap classes to track records
	- to simplify thinks, I considered that only one StockObject can be traded, and of course, we can have multiple
		Trade's on the same StockObject. In any case a many-to-many behavior can be imagine
	
4. Tests
	- tests are not testing setters and getters, since the behaviors are trivial, and are automatically generated.
	- only the setter for FixedDividend from StockObject is tested, because has a special behavior
	- test's names are self explanatory
	- some test's are using verified exceptions