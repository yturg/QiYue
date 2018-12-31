package com.xin.qiyue.common;

import java.io.File;
import java.io.Serializable;

/**
 * @author zxj.
 * @date 2016/8/5.
 */
public interface Callback extends Serializable {

    void onSingleImageSelected(String path);

    void onImageSelected(String path);

    void onImageUnselected(String path);

    void onCameraShot(File imageFile);

    void onPreviewChanged(int select, int sum, boolean visible);
}
