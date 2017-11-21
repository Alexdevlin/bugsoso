package com.livgo.model.http;

import com.livgo.common.util.tip.TipUtil;
import com.livgo.common.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:公用响应对象
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean implements Serializable{
    private static final long serialVersionUID = -6437800749411518984L;

    private String status;
    private String message;
    private Object result;

    public static ResponseBean SUCCESS() {
        return SUCCESS(null);
    }

    public static ResponseBean SUCCESS(Object result) {
        return new ResponseBean("1", Const.SUCCESS, result);
    }

    public static ResponseBean SUCCESSBUT(Object result) {
        return new ResponseBean("1", Const.BUT, result);
    }

    public static ResponseBean TIP(String message) {
        return new ResponseBean("99000", TipUtil.get(message), null);
    }

    public static ResponseBean LOGIN() {
        return new ResponseBean("99005", TipUtil.get("tip.user.token"), null);
    }

    public static ResponseBean FAIL(String message) {
        return new ResponseBean("0", message, null);
    }

    public static ResponseBean FAIL(String status, String message) {
        return new ResponseBean(status, message, null);
    }

    public static ResponseBean FAIL_SYS() {
        return FAIL(TipUtil.get("tip.public.error.system"));
    }

    public static ResponseBean FAIL_TOKEN() {
        return FAIL("99999", TipUtil.get("tip.public.error.token"));
    }

    public static ResponseBean FAIL_ARG() {
        return FAIL("99001", TipUtil.get("tip.public.error.arg"));
    }

    public static ResponseBean FAIL_REQUEST_FAST() {
        return FAIL("99002", TipUtil.get("tip.public.error.fast"));
    }

    public static ResponseBean FAIL_404() {
        return FAIL("99003", TipUtil.get("tip.public.error.404"));
    }

    public static ResponseBean FAIL_REFERER() {
        return FAIL("99004", TipUtil.get("tip.public.error.referer"));
    }
}
