<template>
  <div class="base formed-wrap">
    <div class="formed-form"
         v-if="!isEdit">
      <div class="formed-form-part">
        <h3 class="formed-form-part-title">企业信息</h3>
        <div class="formed-form-item">
          <span>企业 ID：</span>
          <span>{{show.corpId}}</span>
        </div>
      </div>
      <div>
        <hr style="height:1px;border:none;border-top:1px solid #555555;margin-bottom:30px" />
      </div>
      <div class="formed-form-part">
        <h3 class="formed-form-part-title">应用信息</h3>
        <div class="formed-form-item formed-form-item-head clearfix">
          <div class="formed-form-item-content">Agent Id</div>
          <div class="formed-form-item-content">Secret</div>
          <div class="formed-form-item-content">回调地址</div>
        </div>
        <div v-for="(item,index) in show.scanInfoDomainList"
             :key="index"
             class="show-list formed-form-item clearfix">
          <span style="float:left;line-height: 1;">{{item.applicationName}}：</span>
          <div class="formed-form-item-content"><span>{{item.scanAppId}}</span></div>
          <div class="formed-form-item-content"><span>{{item.scanAppSecret}}</span></div>
          <div class="formed-form-item-content">
            <span>{{item.callBackUrl}}</span>
            <span class="copy link"
                  @click="copy(item.callBackUrl)">复制</span></div>
        </div>
      </div>
    </div>
    <div class="formed-form"
         v-else>
      <div class="formed-form-part">
        <h3 class="formed-form-part-title">企业信息</h3>
        <div class="formed-form-item">
          <span>企业 ID：</span>
          <span><Input v-model="corpId" /></span>
        </div>
      </div>
      <div>
        <hr style="height:1px;border:none;border-top:1px solid #555555;margin-bottom:30px" />
      </div>
      <div class="formed-form-part">
        <h3 class="formed-form-part-title">应用信息</h3>
        <div class="formed-form-item formed-form-item-head clearfix">
          <div class="formed-form-item-content">Agent Id</div>
          <div class="formed-form-item-content">Secret</div>
          <div class="formed-form-item-content">回调地址</div>
        </div>
        <div v-for="(item,index) in scanInfoDomainList"
             :key="index"
             class="formed-form-item clearfix">
          <span style="float:left;">{{item.applicationName}}：</span>
          <div class="formed-form-item-content"> <Input v-model="item.scanAppId"
                   style="width:100%"
                   placeholder="请输入" /></div>
          <div class="formed-form-item-content"><Input v-model="item.scanAppSecret"
                   style="width:100%"
                   placeholder="请输入" /></div>
          <div class="formed-form-item-content">
            <Input v-model="item.callBackUrl"
                   style="width:110px" />
            <span class="copy link"
                  @click="copy(item.callBackUrl)">复制</span>
          </div>
        </div>
      </div>
    </div>
    <div class="formed-footer"
         v-if="isEdit">
      <myButton btnType="info"
                @click="saveBaseData">保存</myButton>
      <myButton @click="cancelStatus">取消</myButton>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      isEdit: false, // 是否编辑状态
      show: {},
      scanInfoDomainList: [], // 扫描列表
      corpId: "", // 企业微信企业信息 corp_id
      corpSecret: "", // 企业微信秘钥信息 corpsecret
      callbackUrl: "" // 企业微信回调地址
    };
  },
  mounted () {
    this.getBaseData();
  },
  methods: {
    copy (data) {
      var oInput = document.createElement("input");
      oInput.value = data;
      document.body.appendChild(oInput);
      oInput.select();
      document.execCommand("Copy");
      oInput.className = "oInput";
      oInput.style.display = "none";
    },
    editStatus () {
      this.getBaseData();
      this.isEdit = true;
      this.$emit("statusChange", {
        isEdit: this.isEdit,
        name: "base"
      });
    },
    cancelStatus () {
      this.isEdit = false;
      this.$emit("statusChange", {
        isEdit: this.isEdit,
        name: "base"
      });
    },
    getBaseData () { // 获取基本配置
      let params = {};
      this.axios({
        method: "post",
        data: params,
        url: "/cipher/wxConfig/getBaseConfig"
      })
        .then(res => {
          this.show = res.data;
          this.scanInfoDomainList = res.data.scanInfoDomainList;
          this.corpId = res.data.corpId;
          // this.corpSecret = res.data.corpSecret;
          this.callbackUrl = res.data.callbackUrl;
        })
        .catch(function (error) {
          this.axios.error.handlingErrors(error);
        });
    },
    saveBaseData () { // 保存基本配置
      let params = {
        scanAppInfoList: JSON.stringify(this.scanInfoDomainList),
        corpId: this.corpId
        // corpsecret: this.corpSecret
      };
      this.axios({
        method: "post",
        data: params,
        url: "/cipher/wxConfig/baseConfig"
      })
        .then(res => {
          this.$myMessage.success("保存成功!");
          this.getBaseData();
          this.cancelStatus();
        })
        .catch(function (error) {
          this.axios.error.handlingErrors(error);
        });
    }
  }
};
</script>

<style lang="less" scoped>
@import "~@/assets/styles/formStyle.less";
.formed-form-part-title {
  width: 150px;
}
.formed-form-part {
  .formed-form-item,
  .ivu-form-item {
    margin-left: 150px;
  }
}
.show-list {
  span:not(.copy) {
    display: inline-block;
    width: 100px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .copy {
    vertical-align: top;
  }
}
.copy {
  margin-left: 8px;
}
.formed-form-item-content {
  float: left;
  width: 100px;
  text-align: center;
  margin-right: 8px;
  &:last-of-type {
    width: 150px;
  }
}
.formed-form-item-head > div:first-child {
  margin-left: 130px;
}
</style>
