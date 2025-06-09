package paulscode.sound.libraries;

import com.alibaba.druid.sql.ast.statement.SQLAlterTableReplaceColumn;
import com.sk89q.worldedit.fabric.FabricPermissionsProvider;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.sound.sampled.AudioFormat;
import net.daporkchop.lib.primitive.lambda.BoolFloatLongConsumer;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.Source;

public class LibraryLWJGLOpenAL extends Library {
   private static final boolean GET = false;
   private static final boolean SET = true;
   private static final boolean XXX = false;
   private FloatBuffer listenerPositionAL = null;
   private FloatBuffer listenerOrientation = null;
   private FloatBuffer listenerVelocity = null;
   private HashMap<String, IntBuffer> ALBufferMap = null;
   private static boolean alPitchSupported = true;

   public LibraryLWJGLOpenAL() throws SoundSystemException {
      this.ALBufferMap = new HashMap();
      this.reverseByteOrder = true;
   }

   public void init() throws SoundSystemException {
      boolean var1 = false;

      try {
         FabricPermissionsProvider.create();
         var1 = this.checkALError();
      } catch (GuiStats var5) {
         this.errorMessage(MovingSoundMinecart.null("\u000by?u2r~c177y7c7v2~$r~X.r0V\u00129~7\u000ee1u?u2r~t?b-rd7\u0011g;y\u001f[~y1c~d+g.x,c;sp"));
         this.printStackTrace(var5);
         throw new LibraryLWJGLOpenAL.Exception(var5.getMessage(), 101);
      }

      if (var1) {
         this.importantMessage(BoolFloatLongConsumer.null("{IQWuu\u0014]]]\u0014W[M\u0014PZP@PUU]CQ\u0019DK[IQKX@\u0015"));
      } else {
         this.message(MovingSoundMinecart.null("\u0011g;y\u001f[~~0~*~?{7m;sp"));
      }

      this.listenerPositionAL = ISpectatorMenuView.createFloatBuffer(3).put(new float[]{this.listener.position.x, this.listener.position.y, this.listener.position.z});
      this.listenerOrientation = ISpectatorMenuView.createFloatBuffer(6).put(new float[]{this.listener.lookAt.x, this.listener.lookAt.y, this.listener.lookAt.z, this.listener.up.x, this.listener.up.y, this.listener.up.z});
      this.listenerVelocity = ISpectatorMenuView.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F});
      this.listenerPositionAL.flip();
      this.listenerOrientation.flip();
      this.listenerVelocity.flip();
      SQLAlterTableReplaceColumn.alListener(4100, this.listenerPositionAL);
      var1 = this.checkALError() || var1;
      SQLAlterTableReplaceColumn.alListener(4111, this.listenerOrientation);
      var1 = this.checkALError() || var1;
      SQLAlterTableReplaceColumn.alListener(4102, this.listenerVelocity);
      var1 = this.checkALError() || var1;
      SQLAlterTableReplaceColumn.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
      var1 = this.checkALError() || var1;
      SQLAlterTableReplaceColumn.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
      var1 = this.checkALError() || var1;
      if (var1) {
         this.importantMessage(BoolFloatLongConsumer.null("{IQWuu\u0014]]]\u0014W[M\u0014PZP@PUU]CQ\u0019DK[IQKX@\u0015"));
         throw new LibraryLWJGLOpenAL.Exception(MovingSoundMinecart.null("\u000ee1u2r37;y=x+y*r,r:7)\u007f7{;72x?s7y97\u0011g;y\u001f[~x,7=e;v*~0p~c6r~{7d*r0r,9~7\u000ee1u?u2r~t?b-rd7~X.r0V\u001270x*7-b.g1e*r:"), 101);
      } else {
         super.init();
         ChannelLWJGLOpenAL var2 = (ChannelLWJGLOpenAL)this.normalChannels.get(1);

         try {
            SQLAlterTableReplaceColumn.alSourcef(var2.ALSource.get(0), 4099, 1.0F);
            if (this.checkALError()) {
               alPitchSupported(true, false);
               throw new LibraryLWJGLOpenAL.Exception(BoolFloatLongConsumer.null("{IQWuu\u000e\u0019uuki}mwq\u0014W[M\u0014JAIDVFMQ]\u001a"), 108);
            } else {
               alPitchSupported(true, true);
            }
         } catch (java.lang.Exception var4) {
            alPitchSupported(true, false);
            throw new LibraryLWJGLOpenAL.Exception(MovingSoundMinecart.null("\u0011g;y\u001f[d7\u001f[\u0001G\u0017C\u001d_~y1c~d+g.x,c;sp"), 108);
         }
      }
   }

   public static boolean libraryCompatible() {
      if (FabricPermissionsProvider.isCreated()) {
         return true;
      } else {
         try {
            FabricPermissionsProvider.create();
         } catch (java.lang.Exception var2) {
            return false;
         }

         try {
            FabricPermissionsProvider.destroy();
         } catch (java.lang.Exception var1) {
         }

         return true;
      }
   }

   public Channel createChannel(int arg0) {
      IntBuffer var3 = ISpectatorMenuView.createIntBuffer(1);

      try {
         SQLAlterTableReplaceColumn.alGenSources(var3);
      } catch (java.lang.Exception var5) {
         SQLAlterTableReplaceColumn.alGetError();
         return null;
      }

      if (SQLAlterTableReplaceColumn.alGetError() != 0) {
         return null;
      } else {
         ChannelLWJGLOpenAL var2 = new ChannelLWJGLOpenAL(arg0, var3);
         return var2;
      }
   }

   public void cleanup() {
      super.cleanup();
      Set var1 = this.bufferMap.keySet();
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         IntBuffer var4 = (IntBuffer)this.ALBufferMap.get(var3);
         if (var4 != null) {
            SQLAlterTableReplaceColumn.alDeleteBuffers(var4);
            this.checkALError();
            var4.clear();
         }
      }

      this.bufferMap.clear();
      FabricPermissionsProvider.destroy();
      this.bufferMap = null;
      this.listenerPositionAL = null;
      this.listenerOrientation = null;
      this.listenerVelocity = null;
   }

   public boolean loadSound(FilenameURL arg0) {
      if (this.bufferMap == null) {
         this.bufferMap = new HashMap();
         this.importantMessage(BoolFloatLongConsumer.null("vLR_QK\u0014tUI\u0014NUJ\u0014WAUX\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
      }

      if (this.ALBufferMap == null) {
         this.ALBufferMap = new HashMap();
         this.importantMessage(MovingSoundMinecart.null("X.r07\u001f[~U+q8r,7\u0013v.7)v-70b2{~~073r*\u007f1sy{1v:D1b0sy"));
      }

      if (this.errorCheck(arg0 == null, BoolFloatLongConsumer.null("\u007f]UQWUTQ\u0016akx\u0019ZV@\u0019GIQZ]_]\\P\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"))) {
         return false;
      } else if (this.bufferMap.get(arg0.getFilename()) != null) {
         return true;
      } else {
         ICodec var2 = SoundSystemConfig.getCodec(arg0.getFilename());
         if (this.errorCheck(var2 == null, MovingSoundMinecart.null("\u0010x~t1s;t~q1b0s~q1e~q7{;7y") + arg0.getFilename() + BoolFloatLongConsumer.null("\u0013\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"))) {
            return false;
         } else {
            var2.reverseByteOrder(true);
            URL var3 = arg0.getURL();
            if (this.errorCheck(var3 == null, MovingSoundMinecart.null("\u000by?u2r~c171g;y~q7{;7y") + arg0.getFilename() + BoolFloatLongConsumer.null("\u0013\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"))) {
               return false;
            } else {
               var2.initialize(var3);
               SoundBuffer var4 = var2.readAll();
               var2.cleanup();
               var2 = null;
               if (this.errorCheck(var4 == null, MovingSoundMinecart.null("\rx+y:7<b8q;e~y+{277y~z;c6x:7y{1v:D1b0sy"))) {
                  return false;
               } else {
                  this.bufferMap.put(arg0.getFilename(), var4);
                  AudioFormat var5 = var4.audioFormat;
                  boolean var6 = false;
                  short var8;
                  if (var5.getChannels() == 1) {
                     if (var5.getSampleSizeInBits() == 8) {
                        var8 = 4352;
                     } else {
                        if (var5.getSampleSizeInBits() != 16) {
                           this.errorMessage(BoolFloatLongConsumer.null("}UX\\SXX\u0019GXYIX\\\u0014J]CQ\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
                           return false;
                        }

                        var8 = 4353;
                     }
                  } else {
                     if (var5.getChannels() != 2) {
                        this.errorMessage(BoolFloatLongConsumer.null("\u007f]UQ\u0019Z\\]M\\\\F\u0019YVZV\u0014W[K\u0014J@\\F\\[\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
                        return false;
                     }

                     if (var5.getSampleSizeInBits() == 8) {
                        var8 = 4354;
                     } else {
                        if (var5.getSampleSizeInBits() != 16) {
                           this.errorMessage(MovingSoundMinecart.null("\u0017{2r9v27-v3g2r~d7m;77y~z;c6x:7y{1v:D1b0sy"));
                           return false;
                        }

                        var8 = 4355;
                     }
                  }

                  IntBuffer var7 = ISpectatorMenuView.createIntBuffer(1);
                  SQLAlterTableReplaceColumn.alGenBuffers(var7);
                  if (this.errorCheck(SQLAlterTableReplaceColumn.alGetError() != 0, MovingSoundMinecart.null("v2P;y\u001cb8q;e-7;e,x,7)\u007f;y~{1v:~0p~") + arg0.getFilename())) {
                     return false;
                  } else {
                     SQLAlterTableReplaceColumn.alBufferData(var7.get(0), var8, (ByteBuffer)((ByteBuffer)ISpectatorMenuView.createByteBuffer(var4.audioData.length).put(var4.audioData).flip()), (int)var5.getSampleRate());
                     if (this.errorCheck(SQLAlterTableReplaceColumn.alGetError() != 0, BoolFloatLongConsumer.null("XX{A_R\\F}UMU\u0019QKFVF\u0019CQQW\u0014U[XPPZ^\u0014") + arg0.getFilename()) && this.errorCheck(var7 == null, MovingSoundMinecart.null("\rx+y:7<b8q;e~`?d~y1c~t,r?c;s~q1e~") + arg0.getFilename())) {
                        return false;
                     } else {
                        this.ALBufferMap.put(arg0.getFilename(), var7);
                        return true;
                     }
                  }
               }
            }
         }
      }
   }

   public boolean loadSound(SoundBuffer arg0, String arg1) {
      if (this.bufferMap == null) {
         this.bufferMap = new HashMap();
         this.importantMessage(BoolFloatLongConsumer.null("vLR_QK\u0014tUI\u0014NUJ\u0014WAUX\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
      }

      if (this.ALBufferMap == null) {
         this.ALBufferMap = new HashMap();
         this.importantMessage(MovingSoundMinecart.null("X.r07\u001f[~U+q8r,7\u0013v.7)v-70b2{~~073r*\u007f1sy{1v:D1b0sy"));
      }

      if (this.errorCheck(arg1 == null, BoolFloatLongConsumer.null("pP\\ZM]_]\\F\u0019ZV@\u0019GIQZ]_]\\P\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"))) {
         return false;
      } else if (this.bufferMap.get(arg1) != null) {
         return true;
      } else if (this.errorCheck(arg0 == null, MovingSoundMinecart.null("\rx+y:7<b8q;e~y+{277y~z;c6x:7y{1v:D1b0sy"))) {
         return false;
      } else {
         this.bufferMap.put(arg1, arg0);
         AudioFormat var3 = arg0.audioFormat;
         boolean var4 = false;
         short var6;
         if (var3.getChannels() == 1) {
            if (var3.getSampleSizeInBits() == 8) {
               var6 = 4352;
            } else {
               if (var3.getSampleSizeInBits() != 16) {
                  this.errorMessage(BoolFloatLongConsumer.null("}UX\\SXX\u0019GXYIX\\\u0014J]CQ\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
                  return false;
               }

               var6 = 4353;
            }
         } else {
            if (var3.getChannels() != 2) {
               this.errorMessage(BoolFloatLongConsumer.null("\u007f]UQ\u0019Z\\]M\\\\F\u0019YVZV\u0014W[K\u0014J@\\F\\[\u0019]W\u0014TQM\\VP\u0019\u0013U[XPj[LZ]\u0013"));
               return false;
            }

            if (var3.getSampleSizeInBits() == 8) {
               var6 = 4354;
            } else {
               if (var3.getSampleSizeInBits() != 16) {
                  this.errorMessage(MovingSoundMinecart.null("\u0017{2r9v27-v3g2r~d7m;77y~z;c6x:7y{1v:D1b0sy"));
                  return false;
               }

               var6 = 4355;
            }
         }

         IntBuffer var5 = ISpectatorMenuView.createIntBuffer(1);
         SQLAlterTableReplaceColumn.alGenBuffers(var5);
         if (this.errorCheck(SQLAlterTableReplaceColumn.alGetError() != 0, MovingSoundMinecart.null("?{\u0019r0U+q8r,d~r,e1e~`6r07-v(~0p~") + arg1)) {
            return false;
         } else {
            SQLAlterTableReplaceColumn.alBufferData(var5.get(0), var6, (ByteBuffer)((ByteBuffer)ISpectatorMenuView.createByteBuffer(arg0.audioData.length).put(arg0.audioData).flip()), (int)var3.getSampleRate());
            if (this.errorCheck(SQLAlterTableReplaceColumn.alGetError() != 0, BoolFloatLongConsumer.null("UUvLR_QKpX@X\u0014\\FK[K\u0014N\\\\Z\u0019GXBPZ^\u0014") + arg1) && this.errorCheck(var5 == null, MovingSoundMinecart.null("\rx+y:7<b8q;e~`?d~y1c~t,r?c;s~q1e~") + arg1)) {
               return false;
            } else {
               this.ALBufferMap.put(arg1, var5);
               return true;
            }
         }
      }
   }

   public void unloadSound(String arg0) {
      this.ALBufferMap.remove(arg0);
      super.unloadSound(arg0);
   }

   public void setMasterVolume(float arg0) {
      super.setMasterVolume(arg0);
      SQLAlterTableReplaceColumn.alListenerf(4106, arg0);
      this.checkALError();
   }

   public void newSource(boolean arg0, boolean arg1, boolean arg2, String arg3, FilenameURL arg4, float arg5, float arg6, float arg7, int arg8, float arg9) {
      IntBuffer var11 = null;
      if (!arg1) {
         var11 = (IntBuffer)this.ALBufferMap.get(arg4.getFilename());
         if (var11 == null && !this.loadSound(arg4)) {
            this.errorMessage(BoolFloatLongConsumer.null("j[LFZQ\u0019\u0013") + arg3 + MovingSoundMinecart.null("0~`?d~y1c~t,r?c;s~u;t?b-r~v07;e,x,71t=b,e;s~`6~2r~{1v:~0p~") + arg4.getFilename());
            return;
         }

         var11 = (IntBuffer)this.ALBufferMap.get(arg4.getFilename());
         if (var11 == null) {
            this.errorMessage(BoolFloatLongConsumer.null("j[LFZQ\u0019\u0013") + arg3 + MovingSoundMinecart.null("y7)v-70x*7=e;v*r:7<r=v+d;7?7-x+y:7<b8q;e~`?d~y1c~q1b0s~q1e~") + arg4.getFilename());
            return;
         }
      }

      SoundBuffer var12 = null;
      if (!arg1) {
         var12 = (SoundBuffer)this.bufferMap.get(arg4.getFilename());
         if (var12 == null && !this.loadSound(arg4)) {
            this.errorMessage(BoolFloatLongConsumer.null("j[LFZQ\u0019\u0013") + arg3 + MovingSoundMinecart.null("0~`?d~y1c~t,r?c;s~u;t?b-r~v07;e,x,71t=b,e;s~`6~2r~{1v:~0p~") + arg4.getFilename());
            return;
         }

         var12 = (SoundBuffer)this.bufferMap.get(arg4.getFilename());
         if (var12 == null) {
            this.errorMessage(BoolFloatLongConsumer.null("j[LFZQ\u0019\u0013") + arg3 + MovingSoundMinecart.null("y7)v-70x*7=e;v*r:7<r=v+d;7?b:~17:v*v~`?d~y1c~q1b0s~q1e~") + arg4.getFilename());
            return;
         }
      }

      this.sourceMap.put(arg3, new SourceLWJGLOpenAL(this.listenerPositionAL, var11, arg0, arg1, arg2, arg3, arg4, var12, arg5, arg6, arg7, arg8, arg9, false));
   }

   public void rawDataStream(AudioFormat arg0, boolean arg1, String arg2, float arg3, float arg4, float arg5, int arg6, float arg7) {
      this.sourceMap.put(arg2, new SourceLWJGLOpenAL(this.listenerPositionAL, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7));
   }

   public void quickPlay(boolean arg0, boolean arg1, boolean arg2, String arg3, FilenameURL arg4, float arg5, float arg6, float arg7, int arg8, float arg9, boolean arg10) {
      IntBuffer var12 = null;
      if (!arg1) {
         var12 = (IntBuffer)this.ALBufferMap.get(arg4.getFilename());
         if (var12 == null) {
            this.loadSound(arg4);
         }

         var12 = (IntBuffer)this.ALBufferMap.get(arg4.getFilename());
         if (var12 == null) {
            this.errorMessage(BoolFloatLongConsumer.null("gVAWP\u0019VLR_QK\u0014NUJ\u0014W[M\u0014ZF\\UMQ]\u0014_[K\u0014") + arg4.getFilename());
            return;
         }
      }

      SoundBuffer var13 = null;
      if (!arg1) {
         var13 = (SoundBuffer)this.bufferMap.get(arg4.getFilename());
         if (var13 == null && !this.loadSound(arg4)) {
            this.errorMessage(MovingSoundMinecart.null("D1b,t;7y") + arg3 + BoolFloatLongConsumer.null("\u001e\u0014NUJ\u0014W[M\u0014ZF\\UMQ]\u0014[QZULG\\\u0014XZ\u0019QKFVF\u0019[ZWLFKQ]\u0014N\\PX\\\u0014U[XPPZ^\u0014") + arg4.getFilename());
            return;
         }

         var13 = (SoundBuffer)this.bufferMap.get(arg4.getFilename());
         if (var13 == null) {
            this.errorMessage(MovingSoundMinecart.null("D1b,t;7y") + arg3 + BoolFloatLongConsumer.null("\u0013\u0019CXG\u0019ZV@\u0019WKQX@\\P\u0019V\\WXAJQ\u0019ULPP[\u0019PX@X\u0014NUJ\u0014W[M\u0014_[LZ]\u0014_[K\u0014") + arg4.getFilename());
            return;
         }
      }

      SourceLWJGLOpenAL var14 = new SourceLWJGLOpenAL(this.listenerPositionAL, var12, arg0, arg1, arg2, arg3, arg4, var13, arg5, arg6, arg7, arg8, arg9, false);
      this.sourceMap.put(arg3, var14);
      this.play(var14);
      if (arg10) {
         var14.setTemporary(true);
      }

   }

   public void copySources(HashMap<String, Source> arg0) {
      if (arg0 != null) {
         Set var2 = arg0.keySet();
         Iterator var3 = var2.iterator();
         if (this.bufferMap == null) {
            this.bufferMap = new HashMap();
            this.importantMessage(MovingSoundMinecart.null("\u001cb8q;e~Z?g~`?d~y+{277y~z;c6x:7yt1g'D1b,t;dy"));
         }

         if (this.ALBufferMap == null) {
            this.ALBufferMap = new HashMap();
            this.importantMessage(BoolFloatLongConsumer.null("vD\\Z\u0019uu\u0014{A_R\\F\u0019yXD\u0019CXG\u0019ZLXU\u0014PZ\u0019Y\\@Q[]\u0013Z[IMj[LFZQJ\u0013"));
         }

         this.sourceMap.clear();

         while(true) {
            String var4;
            Source var5;
            SoundBuffer var6;
            do {
               do {
                  if (!var3.hasNext()) {
                     return;
                  }

                  var4 = (String)var3.next();
                  var5 = (Source)arg0.get(var4);
               } while(var5 == null);

               var6 = null;
               if (!var5.toStream) {
                  this.loadSound(var5.filenameURL);
                  var6 = (SoundBuffer)this.bufferMap.get(var5.filenameURL.getFilename());
               }
            } while(!var5.toStream && var6 == null);

            this.sourceMap.put(var4, new SourceLWJGLOpenAL(this.listenerPositionAL, (IntBuffer)this.ALBufferMap.get(var5.filenameURL.getFilename()), var5, var6));
         }
      }
   }

   public void setListenerPosition(float arg0, float arg1, float arg2) {
      super.setListenerPosition(arg0, arg1, arg2);
      this.listenerPositionAL.put(0, arg0);
      this.listenerPositionAL.put(1, arg1);
      this.listenerPositionAL.put(2, arg2);
      SQLAlterTableReplaceColumn.alListener(4100, this.listenerPositionAL);
      this.checkALError();
   }

   public void setListenerAngle(float arg0) {
      super.setListenerAngle(arg0);
      this.listenerOrientation.put(0, this.listener.lookAt.x);
      this.listenerOrientation.put(2, this.listener.lookAt.z);
      SQLAlterTableReplaceColumn.alListener(4111, this.listenerOrientation);
      this.checkALError();
   }

   public void setListenerOrientation(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) {
      super.setListenerOrientation(arg0, arg1, arg2, arg3, arg4, arg5);
      this.listenerOrientation.put(0, arg0);
      this.listenerOrientation.put(1, arg1);
      this.listenerOrientation.put(2, arg2);
      this.listenerOrientation.put(3, arg3);
      this.listenerOrientation.put(4, arg4);
      this.listenerOrientation.put(5, arg5);
      SQLAlterTableReplaceColumn.alListener(4111, this.listenerOrientation);
      this.checkALError();
   }

   public void setListenerData(ListenerData arg0) {
      super.setListenerData(arg0);
      this.listenerPositionAL.put(0, arg0.position.x);
      this.listenerPositionAL.put(1, arg0.position.y);
      this.listenerPositionAL.put(2, arg0.position.z);
      SQLAlterTableReplaceColumn.alListener(4100, this.listenerPositionAL);
      this.checkALError();
      this.listenerOrientation.put(0, arg0.lookAt.x);
      this.listenerOrientation.put(1, arg0.lookAt.y);
      this.listenerOrientation.put(2, arg0.lookAt.z);
      this.listenerOrientation.put(3, arg0.up.x);
      this.listenerOrientation.put(4, arg0.up.y);
      this.listenerOrientation.put(5, arg0.up.z);
      SQLAlterTableReplaceColumn.alListener(4111, this.listenerOrientation);
      this.checkALError();
      this.listenerVelocity.put(0, arg0.velocity.x);
      this.listenerVelocity.put(1, arg0.velocity.y);
      this.listenerVelocity.put(2, arg0.velocity.z);
      SQLAlterTableReplaceColumn.alListener(4102, this.listenerVelocity);
      this.checkALError();
   }

   public void setListenerVelocity(float arg0, float arg1, float arg2) {
      super.setListenerVelocity(arg0, arg1, arg2);
      this.listenerVelocity.put(0, this.listener.velocity.x);
      this.listenerVelocity.put(1, this.listener.velocity.y);
      this.listenerVelocity.put(2, this.listener.velocity.z);
      SQLAlterTableReplaceColumn.alListener(4102, this.listenerVelocity);
   }

   public void dopplerChanged() {
      super.dopplerChanged();
      SQLAlterTableReplaceColumn.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
      this.checkALError();
      SQLAlterTableReplaceColumn.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
      this.checkALError();
   }

   private boolean checkALError() {
      switch(SQLAlterTableReplaceColumn.alGetError()) {
      case 0:
         return false;
      case 40961:
         this.errorMessage(MovingSoundMinecart.null("\u0017y(v2~:70v3r~g?e?z;c;ep"));
         return true;
      case 40962:
         this.errorMessage(BoolFloatLongConsumer.null("pZOUU]]\u0014IUKUTQMQK\u001a"));
         return true;
      case 40963:
         this.errorMessage(MovingSoundMinecart.null("\u0017y(v2~:7;y+z;e?c;s~g?e?z;c;e~a?{+rp"));
         return true;
      case 40964:
         this.errorMessage(BoolFloatLongConsumer.null("}UX\\SXX\u0019WXXU\u001a"));
         return true;
      case 40965:
         this.errorMessage(MovingSoundMinecart.null("B0v<{;7*x~v2{1t?c;73r3x,np"));
         return true;
      default:
         this.errorMessage(BoolFloatLongConsumer.null("uW\u0014LZKQZ[^ZPN\\P\u0019QKFVF\u0019[ZWLFKQ]\u001a"));
         return true;
      }
   }

   public static boolean alPitchSupported() {
      return alPitchSupported(false, false);
   }

   private static synchronized boolean alPitchSupported(boolean arg0, boolean arg1) {
      if (arg0) {
         alPitchSupported = arg1;
      }

      return alPitchSupported;
   }

   public static String getTitle() {
      return MovingSoundMinecart.null("[\t]\u0019[~X.r0V\u0012");
   }

   public static String getDescription() {
      return BoolFloatLongConsumer.null("m\\\\\u0014ucssu\u0014[]WPPZ^\u0014VR\u0019{IQWuu\u001a\u0019\u0014\u007f[K\u0014T[KQ\u0019]WRVFTUM]VZ\u0015\u0014JQ\\\u0014Q@MD\u0003\u001b\u0016CNC\u0017XN^^X\u0017[KS");
   }

   public String getClassName() {
      return MovingSoundMinecart.null("[7u,v,n\u0012@\u0014P\u0012X.r0V\u0012");
   }

   public static class Exception extends SoundSystemException {
      public static final int CREATE = 101;
      public static final int INVALID_NAME = 102;
      public static final int INVALID_ENUM = 103;
      public static final int INVALID_VALUE = 104;
      public static final int INVALID_OPERATION = 105;
      public static final int OUT_OF_MEMORY = 106;
      public static final int LISTENER = 107;
      public static final int NO_AL_PITCH = 108;

      public Exception(String arg0) {
         super(arg0);
      }

      public Exception(String arg0, int arg1) {
         super(arg0, arg1);
      }
   }
}
