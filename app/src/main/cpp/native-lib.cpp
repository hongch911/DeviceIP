#include <jni.h>
#include <string>
#include <sys/select.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <cstring>
#include <ifaddrs.h>
#include <netdb.h>
#include <netinet/in.h>
#include "ifaddrs.h"
#include "android/log.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_yx_netprobe_MainActivity_cpp_1test(JNIEnv *env, jobject thiz) {
    std::string respstr = "Get JNI local IP!\n";
    int family, s;
    char host[NI_MAXHOST];
    struct ifaddrs *ifap;
    getifaddrs(&ifap);
    for (auto ifa = ifap; ifa; ifa = ifa->ifa_next) {
        if (ifa->ifa_addr == NULL)
            continue;
        family = ifa->ifa_addr->sa_family;
        if (family == AF_INET || family == AF_INET6) {
            s = getnameinfo(ifa->ifa_addr,
                            (family == AF_INET) ? sizeof(struct sockaddr_in) :
                            sizeof(struct sockaddr_in6),
                            host, NI_MAXHOST, NULL, 0, NI_NUMERICHOST);
            if (s != 0) {
                __android_log_print(ANDROID_LOG_INFO, "getifaddrs", "getnameinfo() failed: %s", gai_strerror(s));
                continue;
            }
            __android_log_print(ANDROID_LOG_INFO, "getifaddrs", "address: <%s>", host);
            respstr += "address: [";
            respstr += ifa->ifa_name;
            respstr += "  ";
            respstr += host;
            respstr += "]\n";
        }
    }
    freeifaddrs(ifap);

    return env->NewStringUTF(respstr.c_str());
}