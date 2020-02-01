/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ public enum NBTType {
/*  4 */   NBTTagEnd(0), 
/*  5 */   NBTTagByte(1), 
/*  6 */   NBTTagShort(2), 
/*  7 */   NBTTagInt(3), 
/*  8 */   NBTTagLong(4), 
/*  9 */   NBTTagFloat(5), 
/* 10 */   NBTTagDouble(6), 
/* 11 */   NBTTagByteArray(7), 
/* 12 */   NBTTagIntArray(11), 
/* 13 */   NBTTagString(8), 
/* 14 */   NBTTagList(9), 
/* 15 */   NBTTagCompound(10);
/*    */   
/*    */   private NBTType(int i) {
/* 18 */     this.id = i;
/*    */   }
/*    */   
/*    */   private final int id;
/* 22 */   public int getId() { return this.id; }
/*    */   
/*    */   public static NBTType valueOf(int id) {
/*    */     NBTType[] arrayOfNBTType;
/* 26 */     int j = (arrayOfNBTType = values()).length; for (int i = 0; i < j; i++) { NBTType t = arrayOfNBTType[i];
/* 27 */       if (t.getId() == id)
/* 28 */         return t; }
/* 29 */     return NBTTagEnd;
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */