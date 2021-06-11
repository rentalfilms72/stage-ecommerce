# Command to create the jar file
	mvn clean install -DskipTests

# Command to buil de image docker:
	docker build -f BuildProductImage -t product-service .
	