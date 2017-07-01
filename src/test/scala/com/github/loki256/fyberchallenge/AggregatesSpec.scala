package com.github.loki256.fyberchallenge

import org.scalatest.{FunSpec, Matchers}

class AggregatesSpec extends FunSpec with Matchers {

  describe("recalculate") {
    it("should set value for empty aggregates") {
      val ratio = PriceRatio(0, 1.0)
      val result = Aggregates.recalculate(Aggregates.empty, ratio)
      result.max should be (ratio.price)
      result.min should be (ratio.price)
      result.sum should be (ratio.price)
      result.count should be (1)
    }

    it("should set correct min, max sum and count") {
      val aggregates = Aggregates(
        min = 10.0,
        max = 100.0,
        sum = 200.0,
        count = 3
      )
      val first = Aggregates.recalculate(aggregates, PriceRatio(0, 1.0))
      val result = Aggregates.recalculate(first, PriceRatio(1, 101.0))

      result.min should be (1)
      result.max should be (101)
      result.sum should be (302)
      result.count should be (5)
    }
  }
}
