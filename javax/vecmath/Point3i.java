package javax.vecmath;

import java.io.Serializable;

public class Point3i extends Tuple3i implements Serializable {
   public static final long null = 6149289077348153921L;

   public Point3i(int arg0, int arg1, int arg2) {
      super(arg0, arg1, arg2);
   }

   public Point3i(int[] arg0) {
      super(arg0);
   }

   public Point3i(Tuple3i arg0) {
      super(arg0);
   }

   public Point3i() {
   }
}
