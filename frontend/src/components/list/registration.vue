<template>
  <div>
    <h3>运输订单</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加出车信息</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="用户id" prop="driverId"></el-table-column>
        <!--  <el-table-column label="用户密码" prop="password"></el-table-column>
        <el-table-column label="用户类型" prop="level"></el-table-column>-->
        <el-table-column label="操作" width="180px">
          <template>
            <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加汽车弹出来的对话框-->
    <el-dialog
      title="添加订单"
      :visible.sync="addDialogVisible"
      width="50%"
      :before-close="handleClose"
      @close="addDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="" prop="shipper">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="运费支付方" prop="feightpayer">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="运费" prop="freight">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="装货地址" prop="chargeaddress">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="卸货地址" prop="unloadaddress">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="计费方式" prop="chargemode">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="结算方式" prop="cleaningform">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addDriver">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2
      },
      userlist: [],
      totol: 0,
      //添加对话框显示
      addDialogVisible: false,
      //添加用户表单数据
      addForm: {
        shipper: "",
        feightpayer: "",
        freight: "",
        contact: "",
        chargeaddress: "",
        unloadaddress: "",
        chargemode: "",
        cleaningform: ""
      },
      //添加表单验证规则
      addFormRules: {}
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      const _this = this;
      this.$http
        .get("/vehiclecostinfo", {
          params: this.queryInfo
        })
        .then(res => {
          _this.userlist = res.data;
        });
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    addDriver() {}
  }
};
</script>

<style lang="less" scoped>
</style>