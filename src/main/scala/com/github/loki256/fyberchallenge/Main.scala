package com.github.loki256.fyberchallenge

import scala.io.Source

object Main extends App {

  def parseInputFile(fileName: String): Iterator[PriceRatio] = {
    Source.fromFile(fileName).getLines.map { line =>
      val arr = line.replace("\t", " ").split(" ")
      require(arr.length == 2)
      PriceRatio(arr(0).toLong, arr(1).toDouble)
    }
  }

  args.toList match {
    case inputFile :: Nil =>

      System.out.println(TimeWindow.headerString)
      System.out.println("-" * TimeWindow.headerString.length)

      parseInputFile(inputFile).foldLeft(TimeWindow.empty) { (timeWindow, priceRatio) =>
        val newTimeWindow = TimeWindow.recalculate(timeWindow, priceRatio)
        System.out.println(newTimeWindow)
        newTimeWindow
      }
    case _ =>
      System.out.println("You need to specify input file")
      System.exit(1)
  }
}
