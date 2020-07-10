<template>
  <div>
    <h3>汽车维护信息组件</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加新维护信息</el-button>
        </el-col>
      </el-row>
      <!-- 显示维修列表 -->
      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="维修单id" prop="maintenanceId"></el-table-column>
        <el-table-column label="汽车id" prop="carId"></el-table-column>
        <el-table-column label="送修人" prop="deliveryMan"></el-table-column>
        <el-table-column label="送修日期" prop="repairDate"></el-table-column>
        <el-table-column label="维修项目" prop="repairItem"></el-table-column>
        <el-table-column label="维修费用" prop="repairCost"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button 
              type="primary" 
              icon="el-icon-edit" 
              size="mini" 
              @click="showEditDialog(scope.row.maintenanceId)"
            ></el-button>
            <el-button 
              type="danger" 
              icon="el-icon-delete" 
              size="mini"
              @click="removeMaintenanceById(scope.row.maintenanceId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加维修单弹出来的对话框-->
    <el-dialog
      title="添加维护信息" :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="汽车id" prop="carId">
          <el-select v-model="addForm.carId" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.carId"
              :label="item.carId"
              :value="item.carId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="送修人">
          <el-input v-model="addForm.deliveryMan"></el-input>
        </el-form-item>
        <el-form-item label="送修日期">
          <el-input v-model="addForm.repairDate"></el-input>
        </el-form-item>
        <el-form-item label="送修项目">
          <el-input v-model="addForm.repairItem"></el-input>
        </el-form-item>
        <el-form-item label="送修费用">
          <el-input v-model="addForm.repairCost"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addMaintenance()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改维修信息弹出对话框 -->
    <el-dialog
      title="修改维修单信息"
      :visible.sync="editDialogVisible"  
      width="50%"
      @close="editDialogClosed"
    >
      <!-- form表单 -->
      <el-form :model="addForm" :rules="addFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="汽车id" prop="carId">
          <el-select v-model="editForm.carId" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.carId"
              :label="item.carId"
              :value="item.carId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="送修人" prop="deliveryMan">
          <el-input v-model="editForm.deliveryMan"></el-input>
        </el-form-item>
        <el-form-item label="送修日期" prop="repairDate">
          <el-input v-model="editForm.repairDate"></el-input>
        </el-form-item>
        <el-form-item label="送修项目" prop="repairItem">
          <el-input v-model="editForm.repairItem"></el-input>
        </el-form-item>
        <el-form-item label="送修费用" prop="repairCost">
          <el-input v-model="editForm.repairCost"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editMaintenanceInfo()">确 定</el-button>
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
      carqueryInfo:{
        query: "",
        pagenum:1,
        pagesize:2
      },
      userlist: [],
      totol: 0,
      //添加对话框显示
      addDialogVisible: false,
      //修改对话框显示
      editDialogVisible:false,
      //添加用户表单数据
      addForm: {
        carId: "",
        deliveryMan: "",
        repairDate: "",
        repairItem: "",
        repairCost: "",
      },
      editForm:{},
      //添加表单验证规则
      addFormRules: {},
      options:[]
    };
  },
  created() {
    this.getUserList();
    this.getCarList();
  },
  methods: {
    getUserList() {
      const _this = this;
      this.$http
        .get("/maintenanceinfo", {
          params: this.queryInfo
        })
        .then(res => {
          // console.log(res.data)
          _this.userlist = res.data;
          // console.log(_this.userlist)
        });
    },
    //获取所有车辆
    getCarList(){
      const _this=this;
      this.$http.get("/carinfo",{
        params: this.carqueryInfo
      })
      .then(res =>{
        console.log(res.data);
        _this.options=res.data;
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
      this.getCarList();
      const _this=this;
      await this.$http.get("/maintenanceinfo/edit/" + id).then(res=>{
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    //添加维修单
    addMaintenance() {
      const _this=this;
      this.$http.post("/maintenanceinfo/addmaintenance",this.addForm).then(res =>{
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
    // 编辑维修单确认
    editMaintenanceInfo(){
      const _this=this;
      this.$http.post("/maintenanceinfo/editinfo", this.editForm).then(res =>{
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
    //删除维修单
    removeMaintenanceById(id){
      const _this=this;
      this.$confirm("此操作将永久删除该维修单, 是否继续?","提示",{
        confirmButtonText:"确定",
        cancelButtonText:"取消",
        type:"warning"
      }).then(()=>{
        this.$http.delete("maintenanceinfo/delete/" + id).then(res=>{
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