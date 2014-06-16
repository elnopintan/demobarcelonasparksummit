/**
  * A way to reduce the amount of imports used within a console without losing separation of concerns is to
  * create traits with utility functions and create an object extended with those traits. That object will
  * bundle all those functions
  */
object Demo extends DemoReduceByKey with RddTry with RddWithoutTry with MunicipalityParse