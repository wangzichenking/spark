package com.atguigu.sparksql

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.slf4j.LoggerFactory

object HelloWorld {

  val logger = LoggerFactory.getLogger(HelloWorld.getClass)

  def main(args: Array[String]) {
    //创建SparkConf()并设置App名称
       val spark = SparkSession.builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    //通过隐式转换将RDD操作添加到DataFrame上
    import spark.implicits._
    //通过spark.read操作读取JSON数据
    val df = spark.read.json("hdfs:///people.json")
    //show操作类似于Action，将DataFrame直接打印到Console上
    // Displays the content of the DataFrame to stdout
    df.show()
    //DSL 风格的使用方式中 属性的获取方法
    df.filter($"age">20).show()
    //将DataFrame注册为表
    df.createOrReplaceTempView("persons")
    spark.sql("select * from perosns where age > 20").show()
    spark.stop()

  }

}


