package javax.vecmath;

import java.io.Serializable;

public class TexCoord4f extends Tuple4f implements Serializable {
   public static final long false = -3517736544731446513L;

   public TexCoord4f(float arg0, float arg1, float arg2, float arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public TexCoord4f(float[] arg0) {
      super(arg0);
   }

   public TexCoord4f(TexCoord4f arg0) {
      super((Tuple4f)arg0);
   }

   public TexCoord4f(Tuple4f arg0) {
      super(arg0);
   }

   public TexCoord4f(Tuple4d arg0) {
      super(arg0);
   }

   public TexCoord4f() {
   }
}
