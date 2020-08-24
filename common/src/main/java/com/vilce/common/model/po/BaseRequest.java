package com.vilce.common.model.po;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.po.BaseRequest
 * @Author: 雷才哲
 * @Date: 2020/7/1 16:31
 * @Version: 1.0
 */
public class BaseRequest {
    public BaseInfo baseInfo = new BaseInfo();

    private BaseInfo getBaseInfo() {
        return this.baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public class BaseInfo {
        int id;

        private BaseInfo() {
            this.id = 0;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
