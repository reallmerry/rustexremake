package javax.vecmath;

import java.io.Serializable;

public class Vector4f extends Tuple4f implements Serializable {
   public static final long false = 8749319902347760659L;

   public Vector4f(float arg0, float arg1, float arg2, float arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Vector4f(float[] arg0) {
      super(arg0);
   }

   public Vector4f(Vector4f arg0) {
      super((Tuple4f)arg0);
   }

   public Vector4f(Vector4d arg0) {
      super((Tuple4d)arg0);
   }

   public Vector4f(Tuple4f arg0) {
      super(arg0);
   }

   public Vector4f(Tuple4d arg0) {
      super(arg0);
   }

   public Vector4f(Tuple3f arg0) {
      super(arg0.int, arg0.final, arg0.this, 0.0F);
   }

   public Vector4f() {
   }

   public final void null(Tuple3f arg0) {
      this.null = arg0.int;
      this.int = arg0.final;
      this.final = arg0.this;
      this.this = 0.0F;
   }

   public final float do() {
      return (float)Math.sqrt((double)(this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this));
   }

   public final float true() {
      return this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this;
   }

   public final float class(Vector4f arg0) {
      return this.null * arg0.null + this.int * arg0.int + this.final * arg0.final + this.this * arg0.this;
   }

   public final void null(Vector4f arg0) {
      float var2 = (float)(1.0D / Math.sqrt((double)(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this)));
      this.null = arg0.null * var2;
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      float var1 = (float)(1.0D / Math.sqrt((double)(this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this)));
      this.null *= var1;
      this.int *= var1;
      this.final *= var1;
      this.this *= var1;
   }

   public final float null(Vector4f arg0) {
      double var2 = (double)(this.class(arg0) / (this.do() * arg0.do()));
      if (var2 < -1.0D) {
         var2 = -1.0D;
      }

      if (var2 > 1.0D) {
         var2 = 1.0D;
      }

      return (float)Math.acos(var2);
   }
}
