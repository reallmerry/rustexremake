package javax.vecmath;

import java.io.Serializable;

public class TexCoord3f extends Tuple3f implements Serializable {
   public static final long null = -3517736544731446513L;

   public TexCoord3f(float arg0, float arg1, float arg2) {
      super(arg0, arg1, arg2);
   }

   public TexCoord3f(float[] arg0) {
      super(arg0);
   }

   public TexCoord3f(TexCoord3f arg0) {
      super((Tuple3f)arg0);
   }

   public TexCoord3f(Tuple3f arg0) {
      super(arg0);
   }

   public TexCoord3f(Tuple3d arg0) {
      super(arg0);
   }

   public TexCoord3f() {
   }
}
