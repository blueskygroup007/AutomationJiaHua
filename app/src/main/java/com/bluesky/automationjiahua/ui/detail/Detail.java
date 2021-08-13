package com.bluesky.automationjiahua.ui.detail;

/**
 * @author BlueSky
 * @date 2021/7/7
 * Description:
 */
public class Detail {
    private String detailColumn;
    private String detailContent;

    public Detail(String detailColumn, String detailContent) {
        this.detailColumn = detailColumn;
        this.detailContent = detailContent;
    }

    public String getDetailColumn() {
        return detailColumn;
    }

    public void setDetailColumn(String detailColumn) {
        this.detailColumn = detailColumn;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }
}
