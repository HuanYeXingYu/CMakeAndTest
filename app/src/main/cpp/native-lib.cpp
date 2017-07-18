#include <jni.h>
#include <string>
#include <termios.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <jni.h>

//#include "SerialPort.h"

#include "android/log.h"

static const char *TAG = "serial_port";
#define LOGI(fmt, args...) __android_log_print(ANDROID_LOG_INFO,  TAG, fmt, ##args)
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##args)
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

static speed_t getBaudrate(jint baudrate) {
    switch (baudrate) {
        case 0:
            return B0;
        case 50:
            return B50;
        case 75:
            return B75;
        case 110:
            return B110;
        case 134:
            return B134;
        case 150:
            return B150;
        case 200:
            return B200;
        case 300:
            return B300;
        case 600:
            return B600;
        case 1200:
            return B1200;
        case 1800:
            return B1800;
        case 2400:
            return B2400;
        case 4800:
            return B4800;
        case 9600:
            return B9600;
        case 19200:
            return B19200;
        case 38400:
            return B38400;
        case 57600:
            return B57600;
        case 115200:
            return B115200;
        case 230400:
            return B230400;
        case 460800:
            return B460800;
        case 500000:
            return B500000;
        case 576000:
            return B576000;
        case 921600:
            return B921600;
        case 1000000:
            return B1000000;
        case 1152000:
            return B1152000;
        case 1500000:
            return B1500000;
        case 2000000:
            return B2000000;
        case 2500000:
            return B2500000;
        case 3000000:
            return B3000000;
        case 3500000:
            return B3500000;
        case 4000000:
            return B4000000;
        default:
            return -1;
    }
}

extern "C" {

//串口通信

int fd = 0;
JNIEXPORT jstring JNICALL
Java_com_nzsc_cmakeandtest_MySerial_SerialPort_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

/**
*@brief   设置串口数据位，停止位和效验位
*@param  fd     类型  int  打开的串口文件句柄
*@param  databits 类型  int 数据位   取值 为 7 或者8
*@param  stopbits 类型  int 停止位   取值为 1 或者2
*@param  parity  类型  int  效验类型 取值为N,E,O,,S
*/
jboolean set_Parity(int fd, int databits, int stopbits, int parity) {
    struct termios options;
    if (tcgetattr(fd, &options) != 0) {
        perror("SetupSerial 1");
        return (JNI_FALSE);
    }
    options.c_cflag &= ~CSIZE;
    switch (databits) /*设置数据位数*/
    {
        case 7:
            options.c_cflag |= CS7;
            break;
        case 8:
            options.c_cflag |= CS8;
            break;
        default:
            fprintf(stderr, "Unsupported data size\n");
            return (JNI_FALSE);
    }
    switch (parity) {
        case 'n':
        case 'N':
            options.c_cflag &= ~PARENB;   /* Clear parity enable */
            options.c_iflag &= ~INPCK;     /* Enable parity checking */
            break;
        case 'o':
        case 'O':
            options.c_cflag |= (PARODD | PARENB); /* 设置为奇效验*/
            options.c_iflag |= INPCK;             /* Disnable parity checking */
            break;
        case 'e':
        case 'E':
            options.c_cflag |= PARENB;     /* Enable parity */
            options.c_cflag &= ~PARODD;   /* 转换为偶效验*/
            options.c_iflag |= INPCK;       /* Disnable parity checking */
            break;
        case 'S':
        case 's':  /*as no parity*/
            options.c_cflag &= ~PARENB;
            options.c_cflag &= ~CSTOPB;
            break;
        default:
            fprintf(stderr, "Unsupported parity\n");
            return (JNI_FALSE);
    }
/* 设置停止位*/
    switch (stopbits) {
        case 1:
            options.c_cflag &= ~CSTOPB;
            break;
        case 2:
            options.c_cflag |= CSTOPB;
            break;
        default:
            fprintf(stderr, "Unsupported stop bits\n");
            return (JNI_FALSE);
    }
/* Set input parity option */
    if (parity != 'n')
        options.c_iflag |= INPCK;
    tcflush(fd, TCIFLUSH);
    options.c_cc[VTIME] = 150; /* 设置超时15 seconds*/
    options.c_cc[VMIN] = 0; /* Update the options and do it NOW */
    if (tcsetattr(fd, TCSANOW, &options) != 0) {
        perror("SetupSerial 3");
        return (JNI_FALSE);
    }
    return (JNI_TRUE);
}



JNIEXPORT jobject Java_com_nzsc_cmakeandtest_MySerial_SerialPort_Open
        (JNIEnv *env, jobject obj, jint Port, jint Rate) {
    jobject mFileDescriptor;
    if (fd <= 0) {
        if (0 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttySAC0");
            fd = open("/dev/ttySAC0", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (1 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttySAC1");
            fd = open("/dev/ttySAC1", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (2 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttySAC2");
            fd = open("/dev/ttySAC2", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (3 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttySAC3");
            fd = open("/dev/ttySAC3", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (4 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttyUSB0");
            fd = open("/dev/ttyUSB0", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (5 == Port) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttyUSB1");
            fd = open("/dev/ttyUSB1", O_RDWR | O_NDELAY | O_NOCTTY);
        } else if (6 == Port) {
            // name==ttyS0 (uart)Path==/dev/ttyS0
            __android_log_print(ANDROID_LOG_INFO, "serial", "open fd /dev/ttyS0");
            fd = open("/dev/ttyS0", O_RDWR | O_NDELAY | O_NOCTTY);
        } else {
            __android_log_print(ANDROID_LOG_INFO, "serial", "Parameter Error serial not found");
            fd = 0;
            return NULL;
        }
#if 1
        if (fd > 0) {
            __android_log_print(ANDROID_LOG_INFO, "serial", "serial open ok fd=%d", fd);
            // disable echo on serial ports
            struct termios ios;
            tcgetattr(fd, &ios);
            ios.c_oflag &= ~(INLCR | IGNCR | ICRNL);
            ios.c_oflag &= ~(ONLCR | OCRNL);
            ios.c_iflag &= ~(ICRNL | IXON);
            ios.c_iflag &= ~(INLCR | IGNCR | ICRNL);
            ios.c_iflag &= ~(ONLCR | OCRNL);
            tcflush(fd, TCIFLUSH);

            if (Rate == 2400) {
                cfsetospeed(&ios, B2400);
                cfsetispeed(&ios, B2400);
            }
            if (Rate == 4800) {
                cfsetospeed(&ios, B4800);
                cfsetispeed(&ios, B4800);
            }
            if (Rate == 9600) {
                cfsetospeed(&ios, B9600);
                cfsetispeed(&ios, B9600);
            }
            if (Rate == 19200) {
                cfsetospeed(&ios, B19200);
                cfsetispeed(&ios, B19200);
            }
            if (Rate == 38400) {
                cfsetospeed(&ios, B38400);
                cfsetispeed(&ios, B38400);
            }
            if (Rate == 57600) {
                cfsetospeed(&ios, B57600);
                cfsetispeed(&ios, B57600);
            }
            if (Rate == 115200) {
                cfsetospeed(&ios, B115200);
                cfsetispeed(&ios, B115200);
            }

            ios.c_cflag |= (CLOCAL | CREAD);
            set_Parity(fd,8,1,'s');
//            ios.c_cflag &= ~PARENB;
//            ios.c_cflag &= ~CSTOPB;
//            ios.c_cflag &= ~CSIZE;
//            ios.c_cflag |= CS8;
          //  ios.c_lflag = 0;
            tcsetattr(fd, TCSANOW, &ios);
        }
#endif
    }
    /* Create a corresponding file descriptor */
    {
        jclass cFileDescriptor = env->FindClass("java/io/FileDescriptor");
        jmethodID iFileDescriptor = env->GetMethodID(cFileDescriptor, "<init>", "()V");
        jfieldID descriptorID = env->GetFieldID(cFileDescriptor, "descriptor", "I");
        mFileDescriptor = env->NewObject(cFileDescriptor, iFileDescriptor);
        env->SetIntField(mFileDescriptor, descriptorID, (jint) fd);
    }
    return mFileDescriptor;
}


/*
 * Class:     cedric_serial_SerialPort
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_nzsc_cmakeandtest_MySerial_SerialPort_close
        (JNIEnv *env, jobject thiz) {
    jclass SerialPortClass = (env)->GetObjectClass(thiz);
    jclass FileDescriptorClass = (env)->FindClass("java/io/FileDescriptor");

    jfieldID mFdID = (env)->GetFieldID(SerialPortClass, "mFd", "Ljava/io/FileDescriptor;");
    jfieldID descriptorID = (env)->GetFieldID(FileDescriptorClass, "descriptor", "I");

    jobject mFd = (env)->GetObjectField(thiz, mFdID);
    jint descriptor = (env)->GetIntField(mFd, descriptorID);

    LOGD("close(fd = %d)", descriptor);
    close(descriptor);
}


/*
 * Class:     com_topeet_serialtest_serial
 * Method:    Read
 * Signature: ()[I
 */
JNIEXPORT jint Java_com_nzsc_cmakeandtest_MySerial_SerialPort_Write
        (JNIEnv *env, jobject obj, jintArray buf, jint buflen) {
    jsize len = buflen;

    if (len <= 0)
        return -1;

    jintArray array = env->NewIntArray(len);

    if (array == NULL) {
        array = NULL;
        return -1;
    }

    jint *body = env->GetIntArrayElements(buf, 0);

    jint i = 0;
    unsigned char num[len];

    for (; i < len; i++)
        num[i] = body[i];

    write(fd, num, len);

    array = NULL;

    return 0;
}
}
