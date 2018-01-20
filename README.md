
# To run SparkPi, a program that is usually shipped with Spark packages:
java -Dlog4j.configuration="file:src/main/resources/log4j.xml" -cp target/uber-simplesparkprog-1.0-SNAPSHOT.jar com.dhee.SparkPi 20

# To run SimpleSalesAggregator:
java -Dlog4j.configuration="file:src/main/resources/log4j.xml" -cp target/uber-simplesparkprog-1.0-SNAPSHOT.jar com.dhee.SimpleSalesAggregator

SimpleSalesAggregator reads data in data/somegeneratedData.csv, and produces aggregated data as in results.txt on your console. 
