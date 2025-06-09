package com.alibaba.druid.support.json;

import breeze.linalg.Axis;
import net.daporkchop.lib.primitive.list.array.IntArrayList;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class JSONWriter extends IntArrayList {
   private final CapabilityFluidHandler this;

   public JSONWriter(CapabilityFluidHandler arg0) {
      super(arg0, Axis.class, 8.0F);
      thisx.this = arg0;
   }

   public boolean long() {
      if (thisx.this.G()) {
         thisx.false = thisx.this.null();
         return true;
      } else {
         return false;
      }
   }
}
