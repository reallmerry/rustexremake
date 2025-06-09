package javax.vecmath;

import java.io.Serializable;

public class Point4f extends Tuple4f implements Serializable {
   public static final long false = 4643134103185764459L;

   public Point4f(float arg0, float arg1, float arg2, float arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Point4f(float[] arg0) {
      super(arg0);
   }

   public Point4f(Point4f arg0) {
      super((Tuple4f)arg0);
   }

   public Point4f(Point4d arg0) {
      super((Tuple4d)arg0);
   }

   public Point4f(Tuple4f arg0) {
      super(arg0);
   }

   public Point4f(Tuple4d arg0) {
      super(arg0);
   }

   public Point4f(Tuple3f arg0) {
      super(arg0.int, arg0.final, arg0.this, 1.0F);
   }

   public Point4f() {
   }

   public final void null(Tuple3f arg0) {
      this.null = arg0.int;
      this.int = arg0.final;
      this.final = arg0.this;
      this.this = 1.0F;
   }

   public final float const(Point4f arg0) {
      float var2 = this.null - arg0.null;
      float var3 = this.int - arg0.int;
      float var4 = this.final - arg0.final;
      float var5 = this.this - arg0.this;
      return var2 * var2 + var3 * var3 + var4 * var4 + var5 * var5;
   }

   public final float long(Point4f arg0) {
      float var2 = this.null - arg0.null;
      float var3 = this.int - arg0.int;
      float var4 = this.final - arg0.final;
      float var5 = this.this - arg0.this;
      return (float)Math.sqrt((double)(var2 * var2 + var3 * var3 + var4 * var4 + var5 * var5));
   }

   public final float class(Point4f arg0) {
      return Math.abs(this.null - arg0.null) + Math.abs(this.int - arg0.int) + Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final float null(Point4f arg0) {
      float var2 = Math.max(Math.abs(this.null - arg0.null), Math.abs(this.int - arg0.int));
      float var3 = Math.max(Math.abs(this.final - arg0.final), Math.abs(this.this - arg0.this));
      return Math.max(var2, var3);
   }

   public final void null(Point4f arg0) {
      float var2 = 1.0F / arg0.this;
      this.null = arg0.null * var2;
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = 1.0F;
   }
}
