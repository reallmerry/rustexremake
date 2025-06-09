package javax.vecmath;

import java.io.Serializable;

public class Vector2f extends Tuple2f implements Serializable {
   public static final long int = -2168194326883512320L;

   public Vector2f(float arg0, float arg1) {
      super(arg0, arg1);
   }

   public Vector2f(float[] arg0) {
      super(arg0);
   }

   public Vector2f(Vector2f arg0) {
      super((Tuple2f)arg0);
   }

   public Vector2f(Vector2d arg0) {
      super((Tuple2d)arg0);
   }

   public Vector2f(Tuple2f arg0) {
      super(arg0);
   }

   public Vector2f(Tuple2d arg0) {
      super(arg0);
   }

   public Vector2f() {
   }

   public final float class(Vector2f arg0) {
      return this.final * arg0.final + this.this * arg0.this;
   }

   public final float const() {
      return (float)Math.sqrt((double)(this.final * this.final + this.this * this.this));
   }

   public final float long() {
      return this.final * this.final + this.this * this.this;
   }

   public final void null(Vector2f arg0) {
      float var2 = (float)(1.0D / Math.sqrt((double)(arg0.final * arg0.final + arg0.this * arg0.this)));
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      float var1 = (float)(1.0D / Math.sqrt((double)(this.final * this.final + this.this * this.this)));
      this.final *= var1;
      this.this *= var1;
   }

   public final float null(Vector2f arg0) {
      double var2 = (double)(this.class(arg0) / (this.const() * arg0.const()));
      if (var2 < -1.0D) {
         var2 = -1.0D;
      }

      if (var2 > 1.0D) {
         var2 = 1.0D;
      }

      return (float)Math.acos(var2);
   }
}
