#include "com_yzd_android_mcs_phone_utils_NdkUtils.h"
//
// Created by Administrator on 2015/11/9.
//
/* 高位字节的CRC 值*/
const unsigned char auchCRCHi[] = {
        0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0,
        0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1,
        0x81, 0x40, 0x01,
        0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01,
        0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40,
        0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80,
        0x41, 0x01, 0xC0,
        0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
        0x80, 0x41, 0x01,
        0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00,
        0xC1, 0x81, 0x40,
        0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81,
        0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0,
        0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1,
        0x81, 0x40, 0x01,
        0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01,
        0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81,
        0x40, 0x01, 0xC0,
        0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0,
        0x80, 0x41, 0x01,
        0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01,
        0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41,
        0x00, 0xC1, 0x81,
        0x40
} ;

/* 低位字节的CRC 值*/
const unsigned char auchCRCLo[] = {
        0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6, 0x06, 0x07, 0xC7,
        0x05, 0xC5, 0xC4,
        0x04, 0xCC, 0x0C, 0x0D, 0xCD, 0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB,
        0x0B, 0xC9, 0x09,
        0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA, 0x1A, 0x1E, 0xDE,
        0xDF, 0x1F, 0xDD,
        0x1D, 0x1C, 0xDC, 0x14, 0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2,
        0x12, 0x13, 0xD3,
        0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2, 0x32,
        0x36, 0xF6, 0xF7,
        0x37, 0xF5, 0x35, 0x34, 0xF4, 0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E,
        0xFE, 0xFA, 0x3A,
        0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B,
        0x2A, 0xEA, 0xEE,
        0x2E, 0x2F, 0xEF, 0x2D, 0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27,
        0xE7, 0xE6, 0x26,
        0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0, 0x60, 0x61, 0xA1,
        0x63, 0xA3, 0xA2,
        0x62, 0x66, 0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD,
        0x6D, 0xAF, 0x6F,
        0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8, 0x68, 0x78, 0xB8,
        0xB9, 0x79, 0xBB,
        0x7B, 0x7A, 0xBA, 0xBE, 0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4,
        0x74, 0x75, 0xB5,
        0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0,
        0x50, 0x90, 0x91,
        0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94,
        0x54, 0x9C, 0x5C,
        0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59,
        0x58, 0x98, 0x88,
        0x48, 0x49, 0x89, 0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D,
        0x4D, 0x4C, 0x8C,
        0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42, 0x43, 0x83,
        0x41, 0x81, 0x80,
        0x40
};

/* 用于计算CRC 的报文*/
/* 报文中的字节数*/
unsigned char CRC16 (const char *puchMsg, int usDataLen )
{
        unsigned char uchCRCHi = 0xFF ; /* CRC 的高字节初始化*/
        unsigned char uchCRCLo = 0xFF ; /* CRC 的低字节初始化*/
        unsigned int uIndex ; /* CRC 查询表索引*/
        while (usDataLen--) /* 完成整个报文缓冲区*/
        {
                uIndex = uchCRCLo ^ *puchMsg++ ; /* 计算CRC */
                uchCRCLo = uchCRCHi ^ auchCRCHi[uIndex] ;
                uchCRCHi = auchCRCLo[uIndex] ;
        }
        return (uchCRCHi << 8 | uchCRCLo) ;
}

JNIEXPORT jintArray JNICALL Java_com_yzd_android_mcs_1phone_utils_NdkUtils_auchCRCLo(JNIEnv *env, jobject obj) {
        jintArray s = (*env)->NewIntArray(env, 256);
        (*env)->SetIntArrayRegion(env, s, 0, 256, auchCRCLo);
        return s;
}
JNIEXPORT jintArray JNICALL Java_com_yzd_android_mcs_1phone_utils_NdkUtils_auchCRCHi(JNIEnv *env, jobject obj) {
        jintArray s = (*env)->NewIntArray(env, 256);
        (*env)->SetIntArrayRegion(env, s, 0, 256, auchCRCHi);
        return s;
}

JNIEXPORT jstring   JNICALL Java_com_yzd_android_mcs_1phone_utils_NdkUtils_getCrc16(JNIEnv *env, jobject obj, jstring msg, jint leng) {

        /**java中String类型的prompt这里，被转换成了jni中的一个类型jstring。
         * 那么这个jstring类型，我们可以直接cout输出吗？答案是否定的。
         * 我们知道在java中字符采用的是Unicode编码，在c/c++中，字符使用UTF-8编码
         * 我们不需要深入了解Unicode和UTF-8两种编码的具体方法和内容。我们只需要知道
         * Unicode采用的是16-bit编码，而UTF-8采用的是7-bit编码的，所以，我们需要
         * 完成这一转换工作，jni已经提供了相应的接口，如下：
         **/

        const char  *str;
        str = (*env)->GetStringUTFChars(env, msg, NULL);
        if (NULL == str) {
                /**
                 * GetStringUTFChars方法可能会抛出一个OutOfMemoryError的异常，在jni中的异常机制和java中的并不一样
                 * 在java中抛出异常，如果没有捕获，则程序结束运行；但是，在jni中，即使抛出异常，在本地代码的执行顺序依然不变。
                 * 所以，这里判断NULL是必须的
                 */
                return NULL;
        }
        char a = CRC16(str, leng);
        /**
         * 使用完了utf-8类型的字符后，我们需要释放由上面方法返回的字符串，这样可以释放被这些字符占用的内存空间，避免造成内存瘫痪
         */
        (*env)->ReleaseStringUTFChars(env, msg, str);

        return (*env)->NewStringUTF(env, str);  //该方法实例化一个UTF-8编码的本地字符串为java.lang.String类型，新创建的就是java中
        //Unicode类型的代表同一字符串的实例
        //该方法同样可能抛出一个OutOfMemoryError的异常并返回NULL。
}



// java中的jstring, 转化为c的一个字符数组
char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
        char*   rtn   =   NULL;
        jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
        jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
        jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
        jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
        jsize   alen   =   (*env)->GetArrayLength(env,barr);
        jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
        if(alen   >   0)
        {
                rtn   =   (char*)malloc(alen+1);         //new   char[alen+1]; "\0"
                memcpy(rtn,ba,alen);
                rtn[alen]=0;
        }
        (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //释放内存

        return rtn;
}

