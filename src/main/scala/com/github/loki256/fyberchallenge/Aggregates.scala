package com.github.loki256.fyberchallenge

case class Aggregates(min: Double, max: Double, sum: Double, count: Int)

object Aggregates {

  def recalculate(aggregates: Aggregates, priceRatio: PriceRatio): Aggregates = {
    aggregates.copy(
      min = Math.min(aggregates.min, priceRatio.price),
      max = Math.max(aggregates.max, priceRatio.price),
      sum = aggregates.sum + priceRatio.price,
      count = aggregates.count + 1
    )
  }

  def empty: Aggregates = Aggregates(min = Double.MaxValue, max = Double.MinValue, sum = 0, count = 0)
}