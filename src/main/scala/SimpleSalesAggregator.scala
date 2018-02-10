/**
  * Created by nvnw on 2018-01-17.
  */

package com.dhee


import org.apache.spark.sql.SparkSession

object SimpleSalesAggregator {

  def addInt(a:Int, b:Int): Int = { a+b }

  def mergeStoreSalesData (a: Array[(String, Float)], b: Array[(String, Float)] ) : Array[(String, Float)] = {
    // groupBy( _._1 ) will create Array[(String, Float)] objects, one for each value of ._1. That is, you have
    // ( PRODXXX, Array[(PRODXXX, Float)] ) pair, for each PRODXXX
    // To condense this, one has to take the above, and map it to
    // (PRODXXX, SumOfFloatsInArray).
    ( a.toList ++ b.toList ).groupBy( _._1 ).map( kv => (kv._1, kv._2.map(_._2).sum)).toArray
  }

  def main(args: Array[String]) {
    val sc = SparkSession
    .builder
      //.master("local[2]")
    .appName("Simple Sales Aggregator")
    .getOrCreate().sparkContext

    val dataFileFullPath = args(0)
    //val lines = sc.textFile("hdfs:/data/somegeneratedData.csv")
    val lines = sc.textFile(dataFileFullPath)
    println("Total number of Lines: " + lines.count())

    val lineLengths = lines.map(s => s.length)
    val totalLength = lineLengths.reduce(addInt)


    // The following yields an RDD that has elements like this key-value pair:
    // ( STORE00X, [ (PROD00X, 234.45), (PROD00Y,565.234), ... ] )
    val storeSales = lines.filter(x => !x.contains("BDATE,SALEID,STORE,PRODUCTS_SOLD")).map(_.split(","))
                          .map(lineElement => ( lineElement(2), lineElement(3).split("\\|").map(_.split(":")).map(PV => (PV(0), PV(1).toFloat) ) ) )


//    println("Number of elements in storeSales RDD: " + storeSales.count())
//    storeSales.take(5).foreach{l=> println(l._1 + " : " )
//                                          l._2.foreach( xx => println(xx ) )
//                                      }

    val storeSalesXX = storeSales.reduceByKey(mergeStoreSalesData)
    println(" Complete set of aggregated values: " + storeSalesXX.count())
    storeSalesXX.collect().sortBy(_._1).foreach{l=> println(l._1 + " : " )
      l._2.sortBy(_._1) .foreach( xx => println("     " + xx ) )
    }

    sc.stop()
  }

}
