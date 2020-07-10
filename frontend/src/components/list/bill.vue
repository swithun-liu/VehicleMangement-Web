<template>
  <div>
    <h3>回车报账</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加回车账单</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="账单id" prop="billId"></el-table-column>
        <el-table-column label="司机编号" prop="driverId"></el-table-column>
        <el-table-column label="车辆编号" prop="carId"></el-table-column>
        <el-table-column label="回车日期" prop="returnDate"></el-table-column>
        <el-table-column label="加油费用" prop="oilCost"></el-table-column>
        <el-table-column label="载重公里" prop="loadMileage"></el-table-column>
        <el-table-column label="空车公里" prop="noloadMileage"></el-table-column>
        <el-table-column label="油耗" prop="fuelConsumption"></el-table-column>
        <el-table-column label="损耗金额" prop="cost"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button 
              type="primary" 
              icon="el-icon-edit" 
              size="mini"
              @click="showEditDialog(scope.row.billId)"
            ></el-button>
            <el-button 
              type="danger" 
              icon="el-icon-delete" 
              size="mini"
              @click="removeBillById(scope.row.billId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加回车账单弹出来的对话框-->
    <el-dialog
      title="添加回车账单"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
         <el-form-item label="司机编号" prop="driverId">
          <el-select v-model="addForm.driverId" placeholder="请选择">
            <el-option
              v-for="item in driveroptions"
              :key="item.driverId"
              :label="item.driverId"
              :value="item.driverId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆编号" prop="carId">
          <el-select v-model="addForm.carId" placeholder="请选择">
            <el-option
              v-for="item in caroptions"
              :key="item.carId"
              :label="item.carId"
              :value="item.carId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回车日期" prop="returnDate">
          <el-input v-model="addForm.returnDate"></el-input>
        </el-form-item>
        <el-form-item label="加油费用" prop="oilCost">
          <el-input v-model="addForm.oilCost"></el-input>
        </el-form-item>
        <el-form-item label="载重公里" prop="loadMileage">
          <el-input v-model="addForm.loadMileage"></el-input>
        </el-form-item>
        <el-form-item label="空车公里" prop="unloadingAddress">
          <el-input v-model="addForm.unloadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="油耗" prop="fuelConsumption">
          <el-input v-model="addForm.fuelConsumption"></el-input>
        </el-form-item>
        <el-form-item label="损耗金额" prop="cost">
          <el-input v-model="addForm.cost"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addBill()">确 定</el-button>
      </span>
    </el-dialog>

     <!--修改回车账单弹出来的对话框-->
    <el-dialog
      title="修改回车账单"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!--form表单-->
      <el-form :model="editForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="司机编号" prop="driverId">
          <el-select v-model="editForm.driverId" placeholder="请选择">
            <el-option
              v-for="item in driveroptions"
              :key="item.driverId"
              :label="item.driverId"
              :value="item.driverId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆编号" prop="carId">
          <el-select v-model="editForm.carId" placeholder="请选择">
            <el-option
              v-for="item in caroptions"
              :key="item.carId"
              :label="item.carId"
              :value="item.carId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="回车日期" prop="returnDate">
          <el-input v-model="editForm.returnDate"></el-input>
        </el-form-item>
        <el-form-item label="加油费用" prop="oilCost">
          <el-input v-model="editForm.oilCost"></el-input>
        </el-form-item>
        <el-form-item label="载重公里" prop="loadMileage">
          <el-input v-model="editForm.loadMileage"></el-input>
        </el-form-item>
        <el-form-item label="空车公里" prop="unloadingAddress">
          <el-input v-model="editForm.unloadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="油耗" prop="fuelConsumption">
          <el-input v-model="editForm.fuelConsumption"></el-input>
        </el-form-item>
        <el-form-item label="损耗金额" prop="cost">
          <el-input v-model="editForm.cost"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editBill()">确 定</el-button>
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
      driverqueryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2
      },
      carqueryInfo: {
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
        driverId: "",
        carId: "",
        returnDate: "",
        oilCost: "",
        loadMileage: "",
        noloadMileage: "",
        fuelConsumption: "",
        cost: ""
      },
      editForm:{},
      //添加表单验证规则
      addFormRules: {},
      driveroptions:[],
      caroptions:[]
    };
  },
  created() {
    this.getUserList();
    this.getDriverList();
    this.getCarList();
  },
  methods: {
    getUserList() {
      const _this = this;
      this.$http
        .get("/billinfo", {
          params: this.queryInfo
        })
        .then(res => {
          _this.userlist = res.data;
        });
    },
    // 获取所有车辆
    getCarList() {
      const _this = this;
      this.$http
        .get("/carinfo", {
          params: this.carqueryInfo
        })
        .then(res => {
          // console.log(res.data);
          _this.caroptions = res.data;
          // console.log(_this.userlist);
        });
    },
    // 获取所有司机
    getDriverList() {
      const _this = this;
      this.$http
        .get("/driverinfo", {
          params: this.driverqueryInfo
        })
        .then(res => {
          // console.log(res.data);
          _this.driveroptions = res.data;
          // console.log(_this.userlist);
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
      this.getDriverList();
      this.getCarList();
      const _this=this;
      await this.$http.get("/billinfo/edit/" + id).then(res=>{
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    //添加回车账单
    addBill() {
      const _this=this;
      this.$http.post("/billinfo/addbillinfo",this.addForm).then(res =>{
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
    // 编辑回车账单确认
    editBill(){
      const _this=this;
      this.$http.post("/billinfo/editinfo", this.editForm).then(res =>{
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
    //删除回车账单
    removeBillById(id){
      const _this=this;
      this.$confirm("此操作将永久删除该回车账单, 是否继续?","提示",{
        confirmButtonText:"确定",
        cancelButtonText:"取消",
        type:"warning"
      }).then(()=>{
        this.$http.delete("billinfo/delete/" + id).then(res=>{
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