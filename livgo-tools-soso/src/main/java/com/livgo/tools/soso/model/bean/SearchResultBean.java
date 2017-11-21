package com.livgo.tools.soso.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:搜索结果对象
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultBean implements Serializable{
    private static final long serialVersionUID = -6437800749411518984L;

    private String title;
    private String linkUrl;
    private String introContent;

}
