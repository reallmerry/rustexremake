package javax.vecmath;

import java.io.Serializable;

public class Point2f extends Tuple2f implements Serializable {
   public static final long int = -4801347926528714435L;

   public Point2f(float arg0, float arg1) {
      super(arg0, arg1);
   }

   public Point2f(float[] arg0) {
      super(arg0);
   }

   public Point2f(Point2f arg0) {
      super((Tuple2f)arg0);
   }

   public Point2f(Point2d arg0) {
      super((Tuple2d)arg0);
   }

   public Point2f(Tuple2d arg0) {
      super(arg0);
   }

   public Point2f(Tuple2f arg0) {
      super(arg0);
   }

   public Point2f() {
   }

   public final float const(Point2f arg0) {
      float var2 = this.final - arg0.final;
      float var3 = this.this - arg0.this;
      return var2 * var2 + var3 * var3;
   }

   public final float long(Point2f arg0) {
      float var2 = this.final - arg0.final;
      float var3 = this.this - arg0.this;
      return (float)Math.sqrt((double)(var2 * var2 + var3 * var3));
   }

   public final float class(Point2f arg0) {
      return Math.abs(this.final - arg0.final) + Math.abs(this.this - arg0.this);
   }

   public final float null(Point2f arg0) {
      return Math.max(Math.abs(this.final - arg0.final), Math.abs(this.this - arg0.this));
   }
}
