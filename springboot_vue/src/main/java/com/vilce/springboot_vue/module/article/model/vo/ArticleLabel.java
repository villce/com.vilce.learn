package com.vilce.springboot_vue.module.article.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.*;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.module.article.model.vo.ArticleLabel
 * @Author: 雷才哲
 * @Date: 2020/11/18 14:02
 * @Version: 1.0
 */
@Data
@ApiModel(value = "文章标签")
public class ArticleLabel {
    @ApiModelProperty(value = "标签名")
    private String label;
    @ApiModelProperty(value = "文章数")
    private Integer num;

    /**
     * 统计拆分后的标签数
     *
     * @param labelList 拆分后的标签列表
     * @return
     */
    public static List<ArticleLabel> statisticsLabels(List<String> labelList) {
        List<ArticleLabel> articleLabels = new LinkedList<>();
        HashMap<String, Integer> labelMap = new HashMap<>();
        // 统计标签数
        labelList.forEach(labelStr->{
            if (!labelMap.containsKey(labelStr)) {
                labelMap.put(labelStr, 1);
            }else {
                labelMap.put(labelStr, labelMap.get(labelStr) + 1);
            }
        });
        // 赋值返回
        labelMap.forEach((label, num)->{
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setLabel(label);
            articleLabel.setNum(num);
            articleLabels.add(articleLabel);
        });
        Collections.sort(articleLabels, Comparator.comparing(ArticleLabel::getNum, Collections.reverseOrder()));
        return articleLabels;
    }
}
