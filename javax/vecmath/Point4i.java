package javax.vecmath;

import java.io.Serializable;

public class Point4i extends Tuple4i implements Serializable {
   public static final long false = 620124780244617983L;

   public Point4i(int arg0, int arg1, int arg2, int arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Point4i(int[] arg0) {
      super(arg0);
   }

   public Point4i(Tuple4i arg0) {
      super(arg0);
   }

   public Point4i() {
   }
}
