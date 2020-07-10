<template>
  <div>
    <h3>调度单</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加新调度单</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="调度单id" prop="dispatchId"></el-table-column>
        <el-table-column label="订单id" prop="orderId"></el-table-column>
        <el-table-column label="目的地" prop="destination"></el-table-column>
        <el-table-column label="约定完成时间" prop="appointTime"></el-table-column>
        <el-table-column label="收货方" prop="consignee"></el-table-column>
        <el-table-column label="托运方" prop="shipper"></el-table-column>
        <el-table-column label="运费" prop="freight"></el-table-column>
        <el-table-column label="已调度数量" prop="scheduled"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button 
              type="primary" 
              icon="el-icon-edit" 
              size="mini"
              @click="showEditDialog(scope.row.dispatchId)"
            ></el-button>
            <el-button 
              type="danger" 
              icon="el-icon-delete" 
              size="mini"
              @click="removeDispatchById(scope.row.dispatchId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加调度单弹出来的对话框-->
    <el-dialog
      title="添加调度单"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="addForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input v-model="addForm.destination"></el-input>
        </el-form-item>
        <el-form-item label="约定完成时间" prop="appointTime">
          <el-input v-model="addForm.appointTime"></el-input>
        </el-form-item>
        <el-form-item label="收货方" prop="consignee">
          <el-input v-model="addForm.consignee"></el-input>
        </el-form-item>
        <el-form-item label="托运方" prop="shipper">
          <el-input v-model="addForm.shipper"></el-input>
        </el-form-item>
        <el-form-item label="运费" prop="freight">
          <el-input v-model="addForm.freight"></el-input>
        </el-form-item>
        <el-form-item label="已调度数量" prop="scheduled">
          <el-input v-model="addForm.scheduled"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addDispatch()">确 定</el-button>
      </span>
    </el-dialog>

     <!--修改调度单弹出来的对话框-->
    <el-dialog
      title="修改调度单"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!--form表单-->
      <el-form :model="editForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="editForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input v-model="editForm.destination"></el-input>
        </el-form-item>
        <el-form-item label="约定完成时间" prop="appointTime">
          <el-input v-model="editForm.appointTime"></el-input>
        </el-form-item>
        <el-form-item label="收货方" prop="consignee">
          <el-input v-model="editForm.consignee"></el-input>
        </el-form-item>
        <el-form-item label="托运方" prop="shipper">
          <el-input v-model="editForm.shipper"></el-input>
        </el-form-item>
        <el-form-item label="运费" prop="freight">
          <el-input v-model="editForm.freight"></el-input>
        </el-form-item>
        <el-form-item label="已调度数量" prop="scheduled">
          <el-input v-model="editForm.scheduled"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editDispatch()">确 定</el-button>
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
        destination: "",
        appointTime: "",
        consignee: "",
        shipper: "",
        freight: "",
        scheduled: ""
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
        .get("/dispatchinfo", {
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
      await this.$http.get("/dispatchinfo/edit/" + id).then(res=>{
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    //添加调度单
    addDispatch() {
      const _this=this;
      this.$http.post("/dispatchinfo/adddispatchinfo",this.addForm).then(res =>{
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
    // 编辑调度单确认
    editDispatch(){
      const _this=this;
      this.$http.post("/dispatchinfo/editinfo", this.editForm).then(res =>{
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
    //删除调度单
    removeDispatchById(id){
      const _this=this;
      this.$confirm("此操作将永久删除该调度单, 是否继续?","提示",{
        confirmButtonText:"确定",
        cancelButtonText:"取消",
        type:"warning"
      }).then(()=>{
        this.$http.delete("dispatchinfo/delete/" + id).then(res=>{
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