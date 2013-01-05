/***********************************************************************
 * Module:  SigarGateway.java
 * Author:  T@urus
 * Purpose: Defines the Class SigarGateway
 ***********************************************************************/

import java.util.*;

public abstract class SigarGateway {
   protected static Sigar sigar = new Sigar();
   
   protected abstract String loadValue();
   
   /** @param metric */
   public void fill(Metric metric) {
      // TODO: implement
   }

}