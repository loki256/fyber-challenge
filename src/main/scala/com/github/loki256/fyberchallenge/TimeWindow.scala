package com.github.loki256.fyberchallenge

case class TimeWindow(ratios: List[PriceRatio], aggregates: Aggregates) {

  override def toString: String = {
    // return nice formatted string
    // current ratio is the first element of list
    ratios.headOption match {
      case Some(priceRatio) =>
        f"${priceRatio.ts}%-10s ${priceRatio.price}%-7.5f ${aggregates.count}%-2d ${aggregates.sum}%-8.5f ${aggregates.min}%-7.5f ${aggregates.max}%-7.5f"
      case None => "Empty"
    }
  }
}


object TimeWindow {

  val DefaultTimeWindowSeconds = 60

  def recalculate(timeWindow: TimeWindow, priceRatio: PriceRatio, windowSeconds: Int = DefaultTimeWindowSeconds): TimeWindow = {

    // add new element to the window and filter out elements more then TimeWindow (prepend for list is O(1))
    val newWindowRatios = (priceRatio +: timeWindow.ratios).takeWhile(_.ts > priceRatio.ts - windowSeconds)

    // recalculate aggregates the for time window
    val newAggregates = newWindowRatios.foldLeft(Aggregates.empty) { (aggr, ratio) => Aggregates.recalculate(aggr, ratio) }

    timeWindow.copy(
      ratios = newWindowRatios,
      aggregates = newAggregates
    )
  }

  def empty: TimeWindow = TimeWindow(List(), Aggregates.empty)

  def headerString: String = {
    "%-10s %-7s %-2s %-8s %-7s %-7s".format("V", "T", "N", "RS", "MinV", "MaxV")
  }
}