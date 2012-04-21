/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.tranfer;

import java.net.Socket;

/**
 *
 * @author phu.huynh
 */
public class ClientInfor {
    /* socket use to tranfer String */

    public Socket socketString;
    /* socket use to tranfer image */
    public Socket socketImage;

    public void ClientConnect() {
        socketString = new Socket();
        socketImage = new Socket();
    }
}