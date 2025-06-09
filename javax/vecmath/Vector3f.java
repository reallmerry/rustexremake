package javax.vecmath;

import java.io.Serializable;

public class Vector3f extends Tuple3f implements Serializable {
   public static final long null = -7031930069184524614L;

   public Vector3f(float arg0, float arg1, float arg2) {
      super(arg0, arg1, arg2);
   }

   public Vector3f(float[] arg0) {
      super(arg0);
   }

   public Vector3f(Vector3f arg0) {
      super((Tuple3f)arg0);
   }

   public Vector3f(Vector3d arg0) {
      super((Tuple3d)arg0);
   }

   public Vector3f(Tuple3f arg0) {
      super(arg0);
   }

   public Vector3f(Tuple3d arg0) {
      super(arg0);
   }

   public Vector3f() {
   }

   public final float true() {
      return this.int * this.int + this.final * this.final + this.this * this.this;
   }

   public final float const() {
      return (float)Math.sqrt((double)(this.int * this.int + this.final * this.final + this.this * this.this));
   }

   public final void null(Vector3f arg0, Vector3f arg1) {
      float var3 = arg0.final * arg1.this - arg0.this * arg1.final;
      float var4 = arg1.int * arg0.this - arg1.this * arg0.int;
      this.this = arg0.int * arg1.final - arg0.final * arg1.int;
      this.int = var3;
      this.final = var4;
   }

   public final float class(Vector3f arg0) {
      return this.int * arg0.int + this.final * arg0.final + this.this * arg0.this;
   }

   public final void null(Vector3f arg0) {
      float var2 = (float)(1.0D / Math.sqrt((double)(arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this)));
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      float var1 = (float)(1.0D / Math.sqrt((double)(this.int * this.int + this.final * this.final + this.this * this.this)));
      this.int *= var1;
      this.final *= var1;
      this.this *= var1;
   }

   public final float null(Vector3f arg0) {
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
