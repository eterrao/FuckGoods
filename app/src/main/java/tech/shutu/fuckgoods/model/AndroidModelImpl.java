package tech.shutu.fuckgoods.model;

import java.util.List;

import tech.shutu.fuckgoods.model.bean.AndroidBean;
import tech.shutu.fuckgoods.interfaces.RequestCallbackalbe;
import tech.shutu.fuckgoods.listener.OnAndroidRequestListener;
import tech.shutu.fuckgoods.listener.OnRequestCallbackListener;
import tech.shutu.fuckgoods.net.AppAPI;
import tech.shutu.fuckgoods.net.HttpUtils;
import tech.shutu.fuckgoods.utils.LogUtils;

/**
 * Created by raomengyang on 8/15/16.
 */
public class AndroidModelImpl implements IAndroidModel {


    @Override
    public void requestData(OnRequestCallbackListener requestCallbackListener) {

    }

    @Override
    public void requestPage(int pageCount, final OnAndroidRequestListener requestListener) {
        String url = AppAPI.API_ANDROID_URL + "/10/" + pageCount;
        LogUtils.o(" url=> " + url);
        HttpUtils.doVolleyGetNoQA(url,
                new RequestCallbackalbe<String>() {
                    @Override
                    public void requestSuccess(String successCallback) {
                        LogUtils.o(successCallback);

                        List<AndroidBean.ResultsBean> beanList = AndroidBean.parseJsonFromString(successCallback);
                        if (beanList != null) {
                            requestListener.onPageRefreshSuccess(beanList);
                        } else {
                            requestListener.onPageRequestFailure("数据解析出错，请重试");
                        }
                    }

                    @Override
                    public void requestFailure(String failureCallback) {

                    }
                });

    }

    @Override
    public void loadImage(OnRequestCallbackListener requestCallbackListener) {

    }

    @Override
    public void parseJsonDataFromStr(String jsonStr) {

    }
}
