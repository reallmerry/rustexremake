package paulscode.sound.libraries;

import android.view.animation.GridLayoutAnimationController;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableReplaceColumn;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.enchantment.Enchantment;
import paulscode.sound.Channel;

public class ChannelLWJGLOpenAL extends Channel {
   public IntBuffer ALSource;
   public int ALformat;
   public int sampleRate;
   public float millisPreviouslyPlayed = 0.0F;

   public ChannelLWJGLOpenAL(int arg0, IntBuffer arg1) {
      super(arg0);
      this.libraryType = LibraryLWJGLOpenAL.class;
      this.ALSource = arg1;
   }

   public void cleanup() {
      if (this.ALSource != null) {
         try {
            SQLAlterTableReplaceColumn.alSourceStop(this.ALSource);
            SQLAlterTableReplaceColumn.alGetError();
         } catch (Exception var3) {
         }

         try {
            SQLAlterTableReplaceColumn.alDeleteSources(this.ALSource);
            SQLAlterTableReplaceColumn.alGetError();
         } catch (Exception var2) {
         }

         this.ALSource.clear();
      }

      this.ALSource = null;
      super.cleanup();
   }

   public boolean attachBuffer(IntBuffer arg0) {
      if (this.errorCheck(this.channelType != 0, GridLayoutAnimationController.null("tJRKC\u0005EPACBWT\u0005JD^\u0005HKK\\\u0007GB\u0005FQSDDMBA\u0007QH\u0005IJUHFI\u0007VHPUFBV\t"))) {
         return false;
      } else {
         SQLAlterTableReplaceColumn.alSourcei(this.ALSource.get(0), 4105, arg0.get(0));
         if (this.attachedSource != null && this.attachedSource.soundBuffer != null && this.attachedSource.soundBuffer.audioFormat != null) {
            this.setAudioFormat(this.attachedSource.soundBuffer.audioFormat);
         }

         return this.checkALError();
      }
   }

   public void setAudioFormat(AudioFormat arg0) {
      boolean var2 = false;
      short var3;
      if (arg0.getChannels() == 1) {
         if (arg0.getSampleSizeInBits() == 8) {
            var3 = 4352;
         } else {
            if (arg0.getSampleSizeInBits() != 16) {
               this.errorMessage(Enchantment.null("baGhLlG-XlF}Gh\u000b~BwN-Bc\u000b`NyCbO-\f~NyjxOdDKD\u007fFl_*"));
               return;
            }

            var3 = 4353;
         }
      } else {
         if (arg0.getChannels() != 2) {
            this.errorMessage(Enchantment.null("L^iBb\u000biJyJ-EhByChY-FbEb\u000bcD\u007f\u000b~_hYhD-Bc\u000b`NyCbO-\f~NyjxOdDKD\u007fFl_*"));
            return;
         }

         if (arg0.getSampleSizeInBits() == 8) {
            var3 = 4354;
         } else {
            if (arg0.getSampleSizeInBits() != 16) {
               this.errorMessage(GridLayoutAnimationController.null("lKIBBFI\u0007VFHWIB\u0005TL]@\u0007LI\u0005J@SMHA\u0007\u0002T@SdRANJaJUHFQ\u0000"));
               return;
            }

            var3 = 4355;
         }
      }

      this.ALformat = var3;
      this.sampleRate = (int)arg0.getSampleRate();
   }

   public void setFormat(int arg0, int arg1) {
      this.ALformat = arg0;
      this.sampleRate = arg1;
   }

   public boolean preLoadBuffers(LinkedList<byte[]> arg0) {
      if (this.errorCheck(this.channelType != 1, GridLayoutAnimationController.null("ePACBWT\u0005JD^\u0005HKK\\\u0007GB\u0005VPBPBA\u0007CHW\u0007VSWBDJLIB\u0007VHPUFBV\t"))) {
         return false;
      } else if (this.errorCheck(arg0 == null, Enchantment.null("O^kMhY-gdXy\u000bc^aG-Bc\u000b`NyCbO-\f}YhgbJiixMkN\u007fX*"))) {
         return false;
      } else {
         boolean var3 = this.playing();
         if (var3) {
            SQLAlterTableReplaceColumn.alSourceStop(this.ALSource.get(0));
            this.checkALError();
         }

         int var4 = SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4118);
         IntBuffer var2;
         if (var4 > 0) {
            var2 = ISpectatorMenuView.createIntBuffer(var4);
            SQLAlterTableReplaceColumn.alGenBuffers(var2);
            if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("`UWHW\u0007FK@FWNK@\u0005TQU@FH\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002WWBiHDCgRCA@UV\u0000"))) {
               return false;
            }

            SQLAlterTableReplaceColumn.alSourceUnqueueBuffers(this.ALSource.get(0), var2);
            if (this.errorCheck(this.checkALError(), Enchantment.null("HY\u007fD\u007f\u000bxE|^h^dEj\u000b~_\u007fNlF-IxMkN\u007fX-Bc\u000b`NyCbO-\f}YhgbJiixMkN\u007fX*"))) {
               return false;
            }
         }

         if (var3) {
            SQLAlterTableReplaceColumn.alSourcePlay(this.ALSource.get(0));
            this.checkALError();
         }

         var2 = ISpectatorMenuView.createIntBuffer(arg0.size());
         SQLAlterTableReplaceColumn.alGenBuffers(var2);
         if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("`UWHW\u0007BBKBWFQNK@\u0005TQU@FH\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002WWBiHDCgRCA@UV\u0000"))) {
            return false;
         } else {
            ByteBuffer var5 = null;

            for(int var6 = 0; var6 < arg0.size(); ++var6) {
               var5 = (ByteBuffer)ISpectatorMenuView.createByteBuffer(((byte[])arg0.get(var6)).length).put((byte[])arg0.get(var6)).flip();

               try {
                  SQLAlterTableReplaceColumn.alBufferData(var2.get(var6), this.ALformat, var5, this.sampleRate);
               } catch (Exception var9) {
                  this.errorMessage(Enchantment.null("HY\u007fD\u007f\u000bnYhJyBcL-IxMkN\u007fX-Bc\u000b`NyCbO-\f}YhgbJiixMkN\u007fX*"));
                  this.printStackTrace(var9);
                  return false;
               }

               if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("bWUJU\u0005DWBDSLIB\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002WWBiHDCgRCA@UV\u0000"))) {
                  return false;
               }
            }

            try {
               SQLAlterTableReplaceColumn.alSourceQueueBuffers(this.ALSource.get(0), var2);
            } catch (Exception var8) {
               this.errorMessage(Enchantment.null("n\u007fYbY-ZxNxBcL-IxMkN\u007fX-Bc\u000b`NyCbO-\f}YhgbJiixMkN\u007fX*"));
               this.printStackTrace(var8);
               return false;
            }

            if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("`UWHW\u0007TR@RLIB\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002WWBiHDCgRCA@UV\u0000"))) {
               return false;
            } else {
               SQLAlterTableReplaceColumn.alSourcePlay(this.ALSource.get(0));
               return !this.errorCheck(this.checkALError(), Enchantment.null("HY\u007fD\u007f\u000b}GlRdEj\u000b~DxYnN-Bc\u000b`NyCbO-\f}YhgbJiixMkN\u007fX*"));
            }
         }
      }
   }

   public boolean queueBuffer(byte[] arg0) {
      if (this.errorCheck(this.channelType != 1, GridLayoutAnimationController.null("ePACBWT\u0005JD^\u0005HKK\\\u0007GB\u0005VPBPBA\u0007CHW\u0007VSWBDJLIB\u0007VHPUFBV\t"))) {
         return false;
      } else {
         ByteBuffer var2 = (ByteBuffer)ISpectatorMenuView.createByteBuffer(arg0.length).put(arg0).flip();
         IntBuffer var3 = ISpectatorMenuView.createIntBuffer(1);
         SQLAlterTableReplaceColumn.alSourceUnqueueBuffers(this.ALSource.get(0), var3);
         if (this.checkALError()) {
            return false;
         } else {
            if (SQLAlterTableReplaceColumn.alIsBuffer(var3.get(0))) {
               this.millisPreviouslyPlayed += this.millisInBuffer(var3.get(0));
            }

            this.checkALError();
            SQLAlterTableReplaceColumn.alBufferData(var3.get(0), this.ALformat, var2, this.sampleRate);
            if (this.checkALError()) {
               return false;
            } else {
               SQLAlterTableReplaceColumn.alSourceQueueBuffers(this.ALSource.get(0), var3);
               return !this.checkALError();
            }
         }
      }
   }

   public int feedRawAudioData(byte[] arg0) {
      if (this.errorCheck(this.channelType != 1, Enchantment.null("yl\\-JxOdD-Ol_l\u000bnJc\u000bbEaR-Ih\u000bkNi\u000byD-XyYhJ`BcL-Xb^\u007fHhX#"))) {
         return -1;
      } else {
         ByteBuffer var2 = (ByteBuffer)ISpectatorMenuView.createByteBuffer(arg0.length).put(arg0).flip();
         int var4 = SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4118);
         IntBuffer var3;
         if (var4 > 0) {
            var3 = ISpectatorMenuView.createIntBuffer(var4);
            SQLAlterTableReplaceColumn.alGenBuffers(var3);
            if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("`UWHW\u0007FK@FWNK@\u0005TQU@FH\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002A@BAuDPdRANJcDSD\u0000"))) {
               return -1;
            }

            SQLAlterTableReplaceColumn.alSourceUnqueueBuffers(this.ALSource.get(0), var3);
            if (this.errorCheck(this.checkALError(), Enchantment.null("HY\u007fD\u007f\u000bxE|^h^dEj\u000b~_\u007fNlF-IxMkN\u007fX-Bc\u000b`NyCbO-\fkNhO_JzjxOdDIJyJ*"))) {
               return -1;
            }

            if (SQLAlterTableReplaceColumn.alIsBuffer(var3.get(0))) {
               this.millisPreviouslyPlayed += this.millisInBuffer(var3.get(0));
            }

            this.checkALError();
         } else {
            var3 = ISpectatorMenuView.createIntBuffer(1);
            SQLAlterTableReplaceColumn.alGenBuffers(var3);
            if (this.errorCheck(this.checkALError(), GridLayoutAnimationController.null("`UWHW\u0007BBKBWFQNK@\u0005TQU@FH\u0007GRCA@UV\u0007LI\u0005J@SMHA\u0007\u0002WWBiHDCgRCA@UV\u0000"))) {
               return -1;
            }
         }

         SQLAlterTableReplaceColumn.alBufferData(var3.get(0), this.ALformat, var2, this.sampleRate);
         if (this.checkALError()) {
            return -1;
         } else {
            SQLAlterTableReplaceColumn.alSourceQueueBuffers(this.ALSource.get(0), var3);
            if (this.checkALError()) {
               return -1;
            } else {
               if (this.attachedSource != null && this.attachedSource.channel == this && this.attachedSource.active() && !this.playing()) {
                  SQLAlterTableReplaceColumn.alSourcePlay(this.ALSource.get(0));
                  this.checkALError();
               }

               return var4;
            }
         }
      }
   }

   public float millisInBuffer(int arg0) {
      return (float)SQLAlterTableReplaceColumn.alGetBufferi(arg0, 8196) / (float)SQLAlterTableReplaceColumn.alGetBufferi(arg0, 8195) / ((float)SQLAlterTableReplaceColumn.alGetBufferi(arg0, 8194) / 8.0F) / (float)this.sampleRate * 1000.0F;
   }

   public float millisecondsPlayed() {
      float var1 = (float)SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4134);
      float var2 = 1.0F;
      switch(this.ALformat) {
      case 4352:
         var2 = 1.0F;
         break;
      case 4353:
         var2 = 2.0F;
         break;
      case 4354:
         var2 = 2.0F;
         break;
      case 4355:
         var2 = 4.0F;
      }

      var1 = var1 / var2 / (float)this.sampleRate * 1000.0F;
      if (this.channelType == 1) {
         var1 += this.millisPreviouslyPlayed;
      }

      return var1;
   }

   public int buffersProcessed() {
      if (this.channelType != 1) {
         return 0;
      } else {
         int var1 = SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4118);
         return this.checkALError() ? 0 : var1;
      }
   }

   public void flush() {
      if (this.channelType == 1) {
         int var1 = SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4117);
         if (!this.checkALError()) {
            for(IntBuffer var2 = ISpectatorMenuView.createIntBuffer(1); var1 > 0; --var1) {
               try {
                  SQLAlterTableReplaceColumn.alSourceUnqueueBuffers(this.ALSource.get(0), var2);
               } catch (Exception var4) {
                  return;
               }

               if (this.checkALError()) {
                  return;
               }
            }

            this.millisPreviouslyPlayed = 0.0F;
         }
      }
   }

   public void close() {
      try {
         SQLAlterTableReplaceColumn.alSourceStop(this.ALSource.get(0));
         SQLAlterTableReplaceColumn.alGetError();
      } catch (Exception var2) {
      }

      if (this.channelType == 1) {
         this.flush();
      }

   }

   public void play() {
      SQLAlterTableReplaceColumn.alSourcePlay(this.ALSource.get(0));
      this.checkALError();
   }

   public void pause() {
      SQLAlterTableReplaceColumn.alSourcePause(this.ALSource.get(0));
      this.checkALError();
   }

   public void stop() {
      SQLAlterTableReplaceColumn.alSourceStop(this.ALSource.get(0));
      if (!this.checkALError()) {
         this.millisPreviouslyPlayed = 0.0F;
      }

   }

   public void rewind() {
      if (this.channelType != 1) {
         SQLAlterTableReplaceColumn.alSourceRewind(this.ALSource.get(0));
         if (!this.checkALError()) {
            this.millisPreviouslyPlayed = 0.0F;
         }

      }
   }

   public boolean playing() {
      int var1 = SQLAlterTableReplaceColumn.alGetSourcei(this.ALSource.get(0), 4112);
      if (this.checkALError()) {
         return false;
      } else {
         return var1 == 4114;
      }
   }

   private boolean checkALError() {
      switch(SQLAlterTableReplaceColumn.alGetError()) {
      case 0:
         return false;
      case 40961:
         this.errorMessage(Enchantment.null("DE{JaBi\u000bcJ`N-[lYlFh_hY#"));
         return true;
      case 40962:
         this.errorMessage(GridLayoutAnimationController.null("lISFINA\u0007UFWFHBQBW\t"));
         return true;
      case 40963:
         this.errorMessage(Enchantment.null("DE{JaBi\u000bhExFhYl_hO-[lYlFh_hY-]lGxN#"));
         return true;
      case 40964:
         this.errorMessage(GridLayoutAnimationController.null("nIK@@DK\u0005DDKI\t"));
         return true;
      case 40965:
         this.errorMessage(Enchantment.null("~cJoGh\u000byD-JaGbHl_h\u000b`N`D\u007fR#"));
         return true;
      default:
         this.errorMessage(GridLayoutAnimationController.null("fK\u0007PIWBFHBIL]@C\u0005BWUJU\u0005HFDPUWBA\t"));
         return true;
      }
   }
}
