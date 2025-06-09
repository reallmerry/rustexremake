package optifine;

import com.google.common.collect.Lists;
import com.modularmods.mcgltf.forge.ForgeHooksClient;
import java.util.Collections;
import java.util.List;
import net.daporkchop.lib.noise.filter.FilterNoiseSource;

public class OptiFineTransformer implements Runnable {
   private static final OptiFineTransformer false = new OptiFineTransformer();
   private final List<FilterNoiseSource> null = Collections.synchronizedList(Lists.newArrayList());
   private volatile long int;
   private volatile long final;
   private volatile boolean this;

   private OptiFineTransformer() {
      Thread var1 = new Thread(thisx, ForgeHooksClient.null("#z\tvEZ*31{\u0017v\u0004w"));
      var1.setPriority(1);
      var1.start();
   }

   public static OptiFineTransformer null() {
      return false;
   }

   public void run() {
      while(true) {
         thisx.class();
      }
   }

   private void class() {
      for(int var1 = 0; var1 < thisx.null.size(); ++var1) {
         FilterNoiseSource var2 = (FilterNoiseSource)thisx.null.get(var1);
         boolean var3 = var2.null();
         if (!var3) {
            thisx.null.remove(var1--);
            ++thisx.final;
         }

         try {
            Thread.sleep(thisx.this ? 0L : 10L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }

      if (thisx.null.isEmpty()) {
         try {
            Thread.sleep(25L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }
      }

   }

   public void null(FilterNoiseSource arg0) {
      if (!thisx.null.contains(arg0)) {
         ++thisx.int;
         thisx.null.add(arg0);
      }

   }

   public void null() throws InterruptedException {
      thisx.this = true;

      while(thisx.int != thisx.final) {
         Thread.sleep(10L);
      }

      thisx.this = false;
   }
}
