/***********************************************************************
 * Module:  Metric.java
 * Author:  T@urus
 * Purpose: Defines the Interface Metric
 ***********************************************************************/

import java.util.*;

public interface Metric {
   Integer getId();
   /** @param id */
   void setId(Integer id);
   String getValue();
   /** @param value */
   void setValue(String value);

}