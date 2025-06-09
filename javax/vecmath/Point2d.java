package javax.vecmath;

import java.io.Serializable;

public class Point2d extends Tuple2d implements Serializable {
   public static final long int = 1133748791492571954L;

   public Point2d(double arg0, double arg1) {
      super(arg0, arg1);
   }

   public Point2d(double[] arg0) {
      super(arg0);
   }

   public Point2d(Point2d arg0) {
      super((Tuple2d)arg0);
   }

   public Point2d(Point2f arg0) {
      super((Tuple2f)arg0);
   }

   public Point2d(Tuple2d arg0) {
      super(arg0);
   }

   public Point2d(Tuple2f arg0) {
      super(arg0);
   }

   public Point2d() {
   }

   public final double const(Point2d arg0) {
      double var2 = this.final - arg0.final;
      double var4 = this.this - arg0.this;
      return var2 * var2 + var4 * var4;
   }

   public final double long(Point2d arg0) {
      double var2 = this.final - arg0.final;
      double var4 = this.this - arg0.this;
      return Math.sqrt(var2 * var2 + var4 * var4);
   }

   public final double class(Point2d arg0) {
      return Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final double null(Point2d arg0) {
      return Math.max(Math.abs(this.final - arg0.final), Math.abs(this.this - arg0.this));
   }
}
