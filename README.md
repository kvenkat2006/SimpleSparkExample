# Compiling and Running the examples 
In order to compile and run the examples here, please have the following installed on your PC:
1. Git  (this is needed to clone the project)
2. JDK1.8 
3. Maven

# To run SparkPi, a program that is usually shipped with Spark packages:
java -Dlog4j.configuration="file:src/main/resources/log4j.xml" -cp target/uber-simplesparkprog-1.0-SNAPSHOT.jar com.dhee.SparkPi 20

# To compile SimpleSalesAggregator:
Change to the directory where you have cloned SimpleSalesAggregator

cd <SalesAggregatorFolder>
mvn clean
mvn package


# To run SimpleSalesAggregator:
java -Dlog4j.configuration="file:src/main/resources/log4j.xml" -cp target/uber-simplesparkprog-1.0-SNAPSHOT.jar com.dhee.SimpleSalesAggregator

SimpleSalesAggregator reads data in data/somegeneratedData.csv, and produces aggregated data as in results.txt on your console. 
