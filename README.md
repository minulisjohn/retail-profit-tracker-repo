Profit Tracker App
Implementation-

Backend -
  Implemented using SpringBoot, JPA and H2 database.
  Exceptions are handled using @ExceptionHandler and @ControllerAdvice annotations in SpringBoot.
  Profit is calculated as TotalSoldPrice - TotalSoldQty*(TotalPurchasePrice/TotalPurchasedQty).
  Insert queries are available in data.sql file.
  
Frontend -
  A single page application is implemented using React.
  Links are handled using Router in React.
  Client side validations are handled using Javascript.
  
Assumptions -
Calculate Profit Module -
  For calculating profit, the start date is considered as the start day of the current year.
  Either category or product can be included in the search criteria.
   
Improvements -
Tests for backend(JUnit) and frontend(Jest).
Webpackaging for frontend(Webpack).
CORS can be improved in production by allowing only the required domain.

Screenshots -
Screenshots are attached in Screenshots file.
