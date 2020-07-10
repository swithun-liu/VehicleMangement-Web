<template>
  <div>
    <h3>汽车费用</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加新车辆费用信息</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="汽车id" prop="carId"></el-table-column>
        <el-table-column label="汽车类型" prop="carType"></el-table-column>
        <el-table-column label="总损耗金额" prop="totalCost"></el-table-column>
        <el-table-column label="总公里数" prop="totalMiles"></el-table-column>
        <el-table-column label="总加油费用" prop="totalOilCost"></el-table-column>
        <el-table-column label="总油耗" prop="totalFuelConsumption"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button 
              type="primary" 
              icon="el-icon-edit" 
              size="mini" 
              @click="showEditDialog(scope.row.carId)"
            ></el-button>
            <el-button 
              type="danger" 
              icon="el-icon-delete" 
              size="mini"
              @click="removeVehicleCostById(scope.row.carId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加汽车费用单弹出来的对话框-->
    <el-dialog
      title="添加费用信息"
      :visible.sync="addDialogVisible"
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
        <el-form-item label="汽车类型">
          <el-input v-model="addForm.carType"></el-input>
        </el-form-item>
        <el-form-item label="总损耗金额">
          <el-input v-model="addForm.totalCost"></el-input>
        </el-form-item>
        <el-form-item label="总里程">
          <el-input v-model="addForm.totalMiles"></el-input>
        </el-form-item>
        <el-form-item label="总加油费用">
          <el-input v-model="addForm.totalOilCost"></el-input>
        </el-form-item>
        <el-form-item label="总油耗">
          <el-input v-model="addForm.totalFuelConsumption"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addVehicleCost()">确 定</el-button>
      </span>
    </el-dialog>

    <!--修改汽车费用单弹出来的对话框-->
    <el-dialog
      title="修改费用信息"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="汽车类型">
          <el-input v-model="editForm.carType"></el-input>
        </el-form-item>
        <el-form-item label="总损耗金额">
          <el-input v-model="editForm.totalCost"></el-input>
        </el-form-item>
        <el-form-item label="总里程">
          <el-input v-model="editForm.totalMiles"></el-input>
        </el-form-item>
        <el-form-item label="总加油费用">
          <el-input v-model="editForm.totalOilCost"></el-input>
        </el-form-item>
        <el-form-item label="总油耗">
          <el-input v-model="editForm.totalFuelConsumption"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editVehicleCost()">确 定</el-button>
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
        query:"",
        pagenum:1,
        pagesize:2
      },
      userlist: [],
      totol: 0,
      //添加对话框显示
      addDialogVisible: false,
      //修改对话框显示
      editDialogVisible: false,
      //添加用户表单数据
      addForm: {
        carId:"",
        carType:"",
        totalCost: "",
        totalMiles: "",
        totalOilCost: "",
        totalFuelConsumption: "",
      },
      editForm:{},
      //添加表单验证规则
      addFormRules: {
        carId:[
          {required: true, message:"请选择车辆id", trigger:"blur"}
        ]
      },
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
        .get("/vehiclecostinfo", {
          params: this.queryInfo
        })
        .then(res => {
          _this.userlist = res.data;
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
      await this.$http.get("/vehiclecostinfo/edit/" + id).then(res=>{
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    //添加汽车费用单
    addVehicleCost() {
      const _this=this;
      this.$http.post("/vehiclecostinfo/addvehiclecost",this.addForm).then(res =>{
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
    // 编辑汽车费用单确认
    editVehicleCost(){
      const _this=this;
      this.$http.post("/vehiclecostinfo/editinfo", this.editForm).then(res =>{
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
    //删除汽车费用单
    removeVehicleCostById(id){
      const _this=this;
      this.$confirm("此操作将永久删除该汽车费用单, 是否继续?","提示",{
        confirmButtonText:"确定",
        cancelButtonText:"取消",
        type:"warning"
      }).then(()=>{
        this.$http.delete("vehiclecostinfo/delete/" + id).then(res=>{
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