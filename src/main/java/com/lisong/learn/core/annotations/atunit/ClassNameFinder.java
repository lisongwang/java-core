package com.lisong.learn.core.annotations.atunit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Find out the class name by read the byte codes in class file.
 */
public class ClassNameFinder {

    private static Map<Integer, Integer> offsetTable = new HashMap<>();
    private static Map<Integer, String> classNameTable = new HashMap<>();

    public static String thisClass(byte[] classBytes) {
        try (DataInputStream data = new DataInputStream(new ByteArrayInputStream(classBytes))) {
            int magic = data.readInt();
            if(magic != 0xCAFEBABE)
                return null;
            short minorVersion = data.readShort();
            short majorVersion = data.readShort();
            int constant_pool_count = data.readShort();
            for(int i = 1; i < constant_pool_count; i++) {
                int tag = data.read();
                switch(tag) {
                    case 1:  {//UTF-8
                        int length = data.readShort();
                        char[] chars = new char[length];
                        for(int j = 0; j < length; j++)
                            chars[j] = (char)data.read();
                        String className = new String(chars);
                        classNameTable.put(i, className);
                        break;
                    }
                    case 5: //LONG
                    case 6:  {//DOUBLE
                        data.readLong(); //discard 8 bytes
                        i++;
                        break;
                    }
                    case 7:  {//CLASS
                        int offset = data.readShort();
                        offsetTable.put(i, offset);
                        break;
                    }
                    case 8:  //String
                    case 16:  {//METHOD_TYPE_REF
                        data.readShort(); //discard 2 bytes
                        break;
                    }
                    case 3:  //INTEGER
                    case 4:  //FLOAT
                    case 9:  //FIELD_REF
                    case 10:  //METHOD_REF
                    case 11:  //INTERFACE_METHOD_REF
                    case 12:  //NAME_AND_TYPE
                    case 18:  {//INVOKE_DYNAMIC_REF
                        data.readInt();//discard 4 bytes
                        break;
                    }
                    case 15: {//METHOD_HANDLE_REF
                        data.readByte();
                        data.readShort();//discard 3 bytes
                        break;
                    }
                    default: throw new RuntimeException("Bad tag" + tag);
                }
            }
            short accessFlag = data.readShort();
            int this_class = data.readShort();
            int super_class = data.readShort();
            return classNameTable.get(offsetTable.get(this_class)).replace('/','.');
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}