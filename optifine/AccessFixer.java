package optifine;

import java.nio.ByteBuffer;
import net.daporkchop.lib.primitive.lambda.IntBoolDoubleFunction;

public final class AccessFixer implements IntBoolDoubleFunction {
   private final int width;
   private final int height;
   private final ByteBuffer pixelsRGBA;

   public AccessFixer(int arg0, int arg1, ByteBuffer arg2) {
      this.width = arg0;
      this.height = arg1;
      this.pixelsRGBA = arg2;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public ByteBuffer getPixelsRGBA() {
      return this.pixelsRGBA.slice();
   }
}
