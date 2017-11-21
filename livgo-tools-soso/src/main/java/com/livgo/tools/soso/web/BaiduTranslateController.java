package com.livgo.tools.soso.web;

import com.livgo.tools.soso.utils.translate.BaiduTranslateUtil;
import com.livgo.common.util.valid.ValidUtil;
import com.livgo.core.exception.BasicException;
import com.livgo.model.http.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:百度翻译
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
@Controller
@RequestMapping("baidutranslate")
public class BaiduTranslateController extends  BaseController{


    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseBean translate(String str) {
        try {
            if(ValidUtil.isEmpty(str)){
                return ResponseBean.FAIL_ARG();
            }
            return ResponseBean.SUCCESS(BaiduTranslateUtil.fanyi(str));
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

}
