<template>
  <div>
    <h3>运输单</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加新运输单</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="运输单id" prop="transportationId"></el-table-column>
        <el-table-column label="订单id" prop="orderId"></el-table-column>
        <el-table-column label="单价" prop="price"></el-table-column>
        <el-table-column label="计费方式" prop="chargeMode"></el-table-column>
        <el-table-column label="损耗比" prop="lossRario"></el-table-column>
        <el-table-column label="损耗金额" prop="lossAmount"></el-table-column>
        <el-table-column label="往返" prop="roundTrip"></el-table-column>
        <el-table-column label="加油费用" prop="oilCost"></el-table-column>
        <el-table-column label="载重公里" prop="loadMieage"></el-table-column>
        <el-table-column label="空车公里" prop="noloadMileage"></el-table-column>
        <el-table-column label="油耗" prop="fuelConsumption"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button 
              type="primary" 
              icon="el-icon-edit" 
              size="mini"
              @click="showEditDialog(scope.row.transportationId)"
            ></el-button>
            <el-button 
              type="danger" 
              icon="el-icon-delete" 
              size="mini"
              @click="removeTransportationById(scope.row.transportationId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加运输单弹出来的对话框-->
    <el-dialog
      title="添加运输单"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="addForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="addForm.price"></el-input>
        </el-form-item>
        <el-form-item label="计费方式" prop="chargeMode">
          <el-input v-model="addForm.chargeMode"></el-input>
        </el-form-item>
        <el-form-item label="损耗比" prop="lossRario">
          <el-input v-model="addForm.lossRario"></el-input>
        </el-form-item>
        <el-form-item label="损耗金额" prop="lossAmount">
          <el-input v-model="addForm.lossAmount"></el-input>
        </el-form-item>
        <el-form-item label="往返" prop="roundTrip">
          <el-input v-model="addForm.roundTrip"></el-input>
        </el-form-item>
        <el-form-item label="加油费用" prop="oilCost">
          <el-input v-model="addForm.oilCost"></el-input>
        </el-form-item>
        <el-form-item label="载重公里" prop="loadMieage">
          <el-input v-model="addForm.loadMieage"></el-input>
        </el-form-item>
        <el-form-item label="空车公里" prop="noloadMileage">
          <el-input v-model="addForm.noloadMileage"></el-input>
        </el-form-item>
        <el-form-item label="油耗" prop="fuelConsumption">
          <el-input v-model="addForm.fuelConsumption"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addTransportation()">确 定</el-button>
      </span>
    </el-dialog>

     <!--修改运输单弹出来的对话框-->
    <el-dialog
      title="修改运输单"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!--form表单-->
      <el-form :model="editForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="editForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="editForm.price"></el-input>
        </el-form-item>
        <el-form-item label="计费方式" prop="chargeMode">
          <el-input v-model="editForm.chargeMode"></el-input>
        </el-form-item>
        <el-form-item label="损耗比" prop="lossRario">
          <el-input v-model="editForm.lossRario"></el-input>
        </el-form-item>
        <el-form-item label="损耗金额" prop="lossAmount">
          <el-input v-model="editForm.lossAmount"></el-input>
        </el-form-item>
        <el-form-item label="往返" prop="roundTrip">
          <el-input v-model="editForm.roundTrip"></el-input>
        </el-form-item>
        <el-form-item label="加油费用" prop="oilCost">
          <el-input v-model="editForm.oilCost"></el-input>
        </el-form-item>
        <el-form-item label="载重公里" prop="loadMieage">
          <el-input v-model="editForm.loadMieage"></el-input>
        </el-form-item>
        <el-form-item label="空车公里" prop="noloadMileage">
          <el-input v-model="editForm.noloadMileage"></el-input>
        </el-form-item>
        <el-form-item label="油耗" prop="fuelConsumption">
          <el-input v-model="editForm.fuelConsumption"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editTransportation()">确 定</el-button>
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
      //编辑对话框显示
      editDialogVisible: false,
      //添加用户表单数据
      addForm: {
        orderId: "",
        price: "",
        chargeMode: "",
        lossRario: "",
        lossAmount: "",
        roundTrip: "",
        oilCost: "",
        loadMieage: "",
        noloadMileage: "",
        fuelConsumption: ""
      },
      editForm:{},
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
        .get("/transportationinfo", {
          params: this.queryInfo
        })
        .then(res => {
          _this.userlist = res.data;
        });
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    editDialogClosed(){
      this.$refs.editFormRef.resetFields();
    },
    //显示编辑对话框
    async showEditDialog(id){
      const _this=this;
      await this.$http.get("/transportationinfo/edit/" + id).then(res=>{
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    //添加运输单
    addTransportation() {
      const _this=this;
      this.$http.post("/transportationinfo/addtransportationinfo",this.addForm).then(res =>{
        if(res.data == "成功"){
          _this.$message.success("添加成功");
          _this.addDialogVisible = false;
          // 重新获取表数据
          _this.getUserList();
        }else{
          _this.$message.error("添加失败");
        }
      });
    },
    // 编辑运输单确认
    editTransportation(){
      const _this=this;
      this.$http.post("/transportationinfo/editinfo", this.editForm).then(res =>{
        if(res.data == "成功"){
          console.log(res.data);
          _this.$message.success("修改成功");
          _this.editDialogVisible = false;
          _this.getUserList();
        }else{
          console.log(res.data);
          _this.$message.error("添加失败");
        }
      });
    },
    //删除运输单
    removeTransportationById(id){
      const _this=this;
      this.$confirm("此操作将永久删除该运输单, 是否继续?","提示",{
        confirmButtonText:"确定",
        cancelButtonText:"取消",
        type:"warning"
      }).then(()=>{
        this.$http.delete("transportationinfo/delete/" + id).then(res=>{
          if(res.data == "成功"){
            this.$message({
              type:"success",
              message:"删除成功!"
            });
            _this.getUserList();
          }else{
            this.$message({
              type:"info",
              message:"删除失败"
            });
          }
        });
      }).catch(()=>{
        this.$message({
          type:"info",
          message:"已取消删除"
        });
      });
    }
  }
};
</script>

<style lang="less" scoped>
</style>