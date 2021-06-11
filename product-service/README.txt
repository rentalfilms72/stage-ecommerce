# Command to create the jar file
	mvn clean install -DskipTests

# Command to buil de image docker:
	docker build -f BuildProductImage -t product-service .
	
	
 Exception in thread "main" java.lang.UnsupportedClassVersionError: it/stage/microservices/product/ProductServiceApplication has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0   
