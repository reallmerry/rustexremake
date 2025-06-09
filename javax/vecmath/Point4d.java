package javax.vecmath;

import java.io.Serializable;

public class Point4d extends Tuple4d implements Serializable {
   public static final long false = 1733471895962736949L;

   public Point4d(double arg0, double arg1, double arg2, double arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Point4d(double[] arg0) {
      super(arg0);
   }

   public Point4d(Point4d arg0) {
      super((Tuple4d)arg0);
   }

   public Point4d(Point4f arg0) {
      super((Tuple4f)arg0);
   }

   public Point4d(Tuple4f arg0) {
      super(arg0);
   }

   public Point4d(Tuple4d arg0) {
      super(arg0);
   }

   public Point4d(Tuple3d arg0) {
      super(arg0.int, arg0.final, arg0.this, 1.0D);
   }

   public Point4d() {
   }

   public final void null(Tuple3d arg0) {
      this.null = arg0.int;
      this.int = arg0.final;
      this.final = arg0.this;
      this.this = 1.0D;
   }

   public final double const(Point4d arg0) {
      double var2 = this.null - arg0.null;
      double var4 = this.int - arg0.int;
      double var6 = this.final - arg0.final;
      double var8 = this.this - arg0.this;
      return var2 * var2 + var4 * var4 + var6 * var6 + var8 * var8;
   }

   public final double long(Point4d arg0) {
      double var2 = this.null - arg0.null;
      double var4 = this.int - arg0.int;
      double var6 = this.final - arg0.final;
      double var8 = this.this - arg0.this;
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6 + var8 * var8);
   }

   public final double class(Point4d arg0) {
      return Math.abs(this.null - arg0.null) + Math.abs(this.int - arg0.int) + Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final double null(Point4d arg0) {
      double var2 = Math.max(Math.abs(this.null - arg0.null), Math.abs(this.int - arg0.int));
      double var4 = Math.max(Math.abs(this.final - arg0.final), Math.abs(this.this - arg0.this));
      return Math.max(var2, var4);
   }

   public final void null(Point4d arg0) {
      double var2 = 1.0D / arg0.this;
      this.null = arg0.null * var2;
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = 1.0D;
   }
}
