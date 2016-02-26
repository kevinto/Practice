/**
 * Problem 1: Apple Stock Object
 *
 * Solves the problem:
 * Suppose we could access yesterday's stock prices as an array, where:
 *  - The indices are the time in minutes past trade opening time, which was
 *    9:30am local time.
 *  - The values are the price in dollars of Apple stock at that time.
 *
 * So if the stock cost $500 at 10:30am, stockPricesYesterday[60] = 500.
 *
 * Write an efficient function that takes stockPricesYesterday and returns
 * the best profit I could have made from 1 purchase and 1 sale of 1 Apple
 * stock yesterday.
 *
 * Example:
 *   int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};
 *   getMaxProfit(stockPricesYesterday);
 *   // returns 6 (buying for $5 and selling for $11)
 */
public class p1AppleStocks {
  public static void main(String[] Args) {
    int max = solve();
    System.out.println("Max: " + Integer.toString(max));  
  }
  
  public static int solve() {
    int[] stockPrices = new int[]{10, 7, 5, 8, 11, 9};
    
    int currLow = stockPrices[0];
    int currMaxProfit = stockPrices[1] - currLow;
    int currProfit = 0;
    
    for(int i = 1; i < stockPrices.length; i++) {
      currProfit = stockPrices[i] - currLow;
      currMaxProfit = Math.max(currMaxProfit, currProfit);
      currLow = Math.min(currLow, stockPrices[i]);
    }
    
    return currMaxProfit;
  }
}
