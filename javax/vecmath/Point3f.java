package javax.vecmath;

import java.io.Serializable;

public class Point3f extends Tuple3f implements Serializable {
   public static final long null = -8689337816398030143L;

   public Point3f(float arg0, float arg1, float arg2) {
      super(arg0, arg1, arg2);
   }

   public Point3f(float[] arg0) {
      super(arg0);
   }

   public Point3f(Point3f arg0) {
      super((Tuple3f)arg0);
   }

   public Point3f(Point3d arg0) {
      super((Tuple3d)arg0);
   }

   public Point3f(Tuple3f arg0) {
      super(arg0);
   }

   public Point3f(Tuple3d arg0) {
      super(arg0);
   }

   public Point3f() {
   }

   public final float const(Point3f arg0) {
      float var2 = this.int - arg0.int;
      float var3 = this.final - arg0.final;
      float var4 = this.this - arg0.this;
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public final float long(Point3f arg0) {
      float var2 = this.int - arg0.int;
      float var3 = this.final - arg0.final;
      float var4 = this.this - arg0.this;
      return (float)Math.sqrt((double)(var2 * var2 + var3 * var3 + var4 * var4));
   }

   public final float class(Point3f arg0) {
      return Math.abs(this.int - arg0.int) + Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final float null(Point3f arg0) {
      float var2 = Math.max(Math.abs(this.int - arg0.int), Math.abs(this.final - arg0.final));
      return Math.max(var2, Math.abs(this.this - arg0.this));
   }

   public final void null(Point4f arg0) {
      float var2 = 1.0F / arg0.this;
      this.int = arg0.null * var2;
      this.final = arg0.int * var2;
      this.this = arg0.final * var2;
   }
}
