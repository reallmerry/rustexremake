package optifine;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import net.daporkchop.lib.noise.engine.NoopNoiseEngine;
import net.daporkchop.lib.primitive.lambda.ShortObjByteFunction;
import net.minecraft.client.gui.GuiTextField;
import org.apache.commons.io.IOUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class InstallerFrame extends BufferedImage implements AutoCloseable {
   private final int null;
   private final int int;
   private long final;
   private final int this;

   private InstallerFrame(int arg0, int arg1, long arg2) {
      super(arg0, arg1, 2);
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg0 * arg1 * 4;
   }

   public int getWidth() {
      return thisx.null;
   }

   public int getHeight() {
      return thisx.int;
   }

   public int[] getRGB(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5, int arg6) {
      for(int var8 = arg1; var8 < arg3; ++var8) {
         for(int var9 = arg0; var9 < arg2; ++var9) {
            int var10 = MemoryUtil.memGetInt(thisx.final + (long)((var9 + var8 * thisx.null) * 4));
            int var11 = var10 >> 24 & 255;
            int var12 = var10 >> 16 & 255;
            int var13 = var10 >> 8 & 255;
            int var14 = var10 >> 0 & 255;
            int var15 = var11 << 24 | var14 << 16 | var13 << 8 | var12;
            arg4[var9 + var8 * thisx.null] = var15;
         }
      }

      return arg4;
   }

   public int getRGB(int arg0, int arg1) {
      thisx.null(arg0, arg1);
      return MemoryUtil.memGetInt(thisx.final + (long)((arg0 + arg1 * thisx.null) * 4));
   }

   public void setRGB(int arg0, int arg1, int arg2) {
      thisx.null(arg0, arg1);
      MemoryUtil.memPutInt(thisx.final + (long)((arg0 + arg1 * thisx.null) * 4), arg2);
   }

   public BufferedImage getSubimage(int arg0, int arg1, int arg2, int arg3) {
      throw new UnsupportedOperationException(GuiTextField.null("\u001a  o=\"$#1\"1! *0"));
   }

   public void close() throws Exception {
      if (thisx.final != 0L) {
         STBImage.nstbi_image_free(thisx.final);
         thisx.final = 0L;
      }

   }

   private void null(int arg0, int arg1) {
      if (arg0 < 0 || arg0 >= thisx.null || arg1 < 0 || arg1 >= thisx.int) {
         throw new IllegalStateException(ShortObjByteFunction.null("%!\u001et\u00052J6\u0005!\u00040\u0019nJ") + arg0 + GuiTextField.null("ct") + arg1 + ShortObjByteFunction.null("J|\u001d=\u000e \u0002nJ") + thisx.null + GuiTextField.null("ct'1&3' ut") + thisx.int + ShortObjByteFunction.null("C"));
      }
   }

   public static InstallerFrame null(InputStream arg0) throws IOException {
      ByteBuffer var1 = null;

      InstallerFrame var8;
      try {
         var1 = null(arg0);
         var1.rewind();
         MemoryStack var2 = MemoryStack.stackPush();
         Throwable var3 = null;

         try {
            IntBuffer var4 = var2.mallocInt(1);
            IntBuffer var5 = var2.mallocInt(1);
            IntBuffer var6 = var2.mallocInt(1);
            ByteBuffer var7 = STBImage.stbi_load_from_memory(var1, var4, var5, var6, 4);
            if (var7 == null) {
               throw new IOException(GuiTextField.null("\f;:8+t!;;t#;.0o=\"5(1ut") + STBImage.stbi_failure_reason());
            }

            var8 = new InstallerFrame(var4.get(0), var5.get(0), MemoryUtil.memAddress(var7));
         } catch (Throwable var24) {
            var3 = var24;
            throw var24;
         } finally {
            if (var2 != null) {
               if (var3 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var23) {
                     var3.addSuppressed(var23);
                  }
               } else {
                  var2.close();
               }
            }

         }
      } finally {
         MemoryUtil.memFree(var1);
         IOUtils.closeQuietly(arg0);
      }

      return var8;
   }

   private static ByteBuffer null(InputStream arg0) throws IOException {
      ByteBuffer var1;
      if (arg0 instanceof FileInputStream) {
         FileChannel var2 = ((FileInputStream)arg0).getChannel();
         var1 = MemoryUtil.memAlloc((int)var2.size() + 1);

         while(true) {
            if (var2.read(var1) != -1) {
               continue;
            }
         }
      } else {
         int var5 = 4096;

         try {
            var5 = Math.max(4096, arg0.available());
         } catch (IOException var4) {
         }

         var1 = MemoryUtil.memAlloc(var5 * 2);
         NoopNoiseEngine var3 = new NoopNoiseEngine(arg0);

         while(var3.read(var1) != -1) {
            if (var1.remaining() == 0) {
               var1 = MemoryUtil.memRealloc(var1, var1.capacity() * 2);
            }
         }
      }

      return var1;
   }
}
