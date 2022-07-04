// Drelovey.aidl
package com.zt.server;

// Declare any non-default types here with import statements
import com.zt.server.ServerBean;
interface Drelovey {
    void showContent(String content);
    void sendData(in ServerBean bean);
}