package javax.vecmath;

import java.io.Serializable;

public class Point3d extends Tuple3d implements Serializable {
   public static final long null = 5718062286069042927L;

   public Point3d(double arg0, double arg1, double arg2) {
      super(arg0, arg1, arg2);
   }

   public Point3d(double[] arg0) {
      super(arg0);
   }

   public Point3d(Point3d arg0) {
      super((Tuple3d)arg0);
   }

   public Point3d(Point3f arg0) {
      super((Tuple3f)arg0);
   }

   public Point3d(Tuple3f arg0) {
      super(arg0);
   }

   public Point3d(Tuple3d arg0) {
      super(arg0);
   }

   public Point3d() {
   }

   public final double const(Point3d arg0) {
      double var2 = this.int - arg0.int;
      double var4 = this.final - arg0.final;
      double var6 = this.this - arg0.this;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public final double long(Point3d arg0) {
      double var2 = this.int - arg0.int;
      double var4 = this.final - arg0.final;
      double var6 = this.this - arg0.this;
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public final double class(Point3d arg0) {
      return Math.abs(this.int - arg0.int) + Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final double null(Point3d arg0) {
      double var2 = Math.max(Math.abs(this.int - arg0.int), Math.abs(this.final - arg0.final));
      return Math.max(var2, Math.abs(this.this - arg0.this));
   }

   public final void null(Point4d arg0) {
      double var2 = 1.0D / arg0.this;
      this.int = arg0.null * var2;
      this.final = arg0.int * var2;
      this.this = arg0.final * var2;
   }
}
