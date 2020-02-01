/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */

import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class NBTFile extends NBTCompound
/*    */ {
/*    */   private final File file;
/*    */   private Object nbt;
/*    */   
/*    */   public NBTFile(File file) throws IOException
/*    */   {
/* 16 */     super(null, null);
/* 17 */     this.file = file;
/* 18 */     if (file.exists()) {
/* 19 */       FileInputStream inputsteam = new FileInputStream(file);
/* 20 */       this.nbt = NBTReflectionUtil.readNBTFile(inputsteam);
/*    */     } else {
/* 22 */       this.nbt = NBTReflectionUtil.getNewNBTTag();
/* 23 */       save();
/*    */     }
/*    */   }
/*    */   
/*    */   public void save() throws IOException {
/* 28 */     if (!this.file.exists()) {
/* 29 */       this.file.getParentFile().mkdirs();
/* 30 */       this.file.createNewFile();
/*    */     }
/* 32 */     FileOutputStream outStream = new FileOutputStream(this.file);
/* 33 */     NBTReflectionUtil.saveNBTFile(this.nbt, outStream);
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 37 */     return this.file;
/*    */   }
/*    */   
/*    */   protected Object getCompound() {
/* 41 */     return this.nbt;
/*    */   }
/*    */   
/*    */   protected void setCompound(Object tag) {
/* 45 */     this.nbt = tag;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 49 */     return null;
/*    */   }
/*    */   
/*    */   protected void setItem(ItemStack item) {}
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */