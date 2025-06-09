package optifine.json;

import android.renderscript.AllocationAdapter;
import breeze.math.Semiring;
import net.daporkchop.lib.primitive.list.DoubleList;
import net.minecraft.client.renderer.entity.RenderStray;
import net.minecraft.command.ICommand;
import net.minecraft.entity.ai.EntityAIVillagerInteract;

public enum JSONStreamAware {
   goto(0, 0, 28, 32, 8),
   case(84, 0, 28, 32, 8),
   short(0, 64, 32, 28, 5),
   long(96, 64, 32, 28, 5);

   public static final int new;
   private final int const;
   private final int false;
   private final int null;
   private final int int;
   private final int final;

   private JSONStreamAware(int arg2, int arg3, int arg4, int arg5, int arg6) {
      thisx.const = arg2;
      thisx.false = arg3;
      thisx.null = arg4;
      thisx.int = arg5;
      thisx.final = arg6;
   }

   public int null() {
      return thisx.final;
   }

   public void null(RenderStray arg0, int arg1, int arg2, boolean arg3, int arg4) {
      int var6 = thisx.const;
      if (arg4 > 0) {
         var6 += thisx.null;
      }

      if (arg4 == thisx.final - 1) {
         var6 += thisx.null;
      }

      int var7 = arg3 ? thisx.false + thisx.int : thisx.false;
      arg0.null(arg1 + thisx.class(arg4), arg2 + thisx.null(arg4), var6, var7, thisx.null, thisx.int);
   }

   public void null(int arg0, int arg1, int arg2, AllocationAdapter arg3, ICommand arg4) {
      int var6 = arg0 + thisx.class(arg2);
      int var7 = arg1 + thisx.null(arg2);
      switch(thisx) {
      case goto:
         var6 += 6;
         var7 += 9;
         break;
      case case:
         var6 += 6;
         var7 += 6;
         break;
      case short:
         var6 += 10;
         var7 += 5;
         break;
      case long:
         var6 += 6;
         var7 += 5;
      }

      arg3.null((DoubleList)null, arg4, var6, var7);
   }

   public int class(int arg0) {
      switch(thisx) {
      case goto:
         return (thisx.null + 4) * arg0;
      case case:
         return (thisx.null + 4) * arg0;
      case short:
         return -thisx.null + 4;
      case long:
         return 248;
      default:
         throw new UnsupportedOperationException(Semiring.null("\u0012\u00158]\"Z=\u00149\rv\r>\u001b\"Z\"\u0012?\tv\u000e7\u0018v\u000e/\n3Z?\tw") + thisx);
      }
   }

   public int null(int arg0) {
      switch(thisx) {
      case goto:
         return -thisx.int + 4;
      case case:
         return 136;
      case short:
         return thisx.int * arg0;
      case long:
         return thisx.int * arg0;
      default:
         throw new UnsupportedOperationException(EntityAIVillagerInteract.null("myG1]6BxFa\taAw]6]~@e\tbHt\tbPfL6@e\b") + thisx);
      }
   }

   public boolean null(int arg0, int arg1, int arg2, int arg3, int arg4) {
      int var6 = arg0 + thisx.class(arg2);
      int var7 = arg1 + thisx.null(arg2);
      return arg3 > var6 && arg3 < var6 + thisx.null && arg4 > var7 && arg4 < var7 + thisx.int;
   }

   static {
      int var0 = 0;
      JSONStreamAware[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         JSONStreamAware var4 = var1[var3];
         var0 += var4.final;
      }

      new = var0;
   }
}
