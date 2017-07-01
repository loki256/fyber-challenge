package com.github.loki256.fyberchallenge

import org.scalatest.{FunSpec, Matchers}

class TimeWindowSpec extends FunSpec with Matchers {

  describe("recalculate") {

    it("should add and calculate aggregates for one element") {
      val timeWindow = TimeWindow.empty
      val newWindow = TimeWindow.recalculate(timeWindow, PriceRatio(0, 1.0))
      newWindow.ratios.size should be (1)
      newWindow.ratios.head should be (PriceRatio(0, 1.0))
      newWindow.aggregates.sum should be (1)
      newWindow.aggregates.min should be (1)
      newWindow.aggregates.max should be (1)
      newWindow.aggregates.count should be (1)
    }


    it("should keep only prices within time window") {
      val timeWindow = TimeWindow(
        ratios = List(
          PriceRatio(59, 4),
          PriceRatio(40, 3),
          PriceRatio(20, 2),
          PriceRatio(0, 1)
        ),
        aggregates = Aggregates(
          min = 1,
          max = 4,
          sum = 1 + 2 + 3 + 4,
          count = 4
        )
      )

      val newWindow = TimeWindow.recalculate(timeWindow, PriceRatio(60, 5.0))
      newWindow.ratios.size should be (4)
      newWindow.ratios.head should be (PriceRatio(60, 5.0))
      newWindow.ratios(3) should be (PriceRatio(20, 2))
      newWindow.aggregates.min should be (2)
      newWindow.aggregates.max should be (5)
      newWindow.aggregates.sum should be (5 + 4 + 3 + 2)
      newWindow.aggregates.count should be (4)
    }

  }
}
