<template>
  <div>
    <h3>运输订单</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加新订单</el-button>
        </el-col>
      </el-row>

      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="订单id" prop="orderId"></el-table-column>
        <el-table-column label="托运方" prop="shipper"></el-table-column>
        <el-table-column label="运费是支付方" prop="freightPayer"></el-table-column>
        <el-table-column label="联系人" prop="contact"></el-table-column>
        <el-table-column label="装货地址" prop="loadingAddress"></el-table-column>
        <el-table-column label="卸货地址" prop="unloadingAddress"></el-table-column>
        <el-table-column label="计费方式" prop="chargeMode"></el-table-column>
        <el-table-column label="结算方式" prop="clearingForm"></el-table-column>
        <el-table-column label="分配情况" prop="distribute"></el-table-column>
        <el-table-column label="总重量" prop="weight"></el-table-column>
        <el-table-column label="运费" prop="freight"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.orderId)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeOrderById(scope.row.orderId)"
            ></el-button>
            <el-button
              type="warning"
              icon="el-icon-setting"
              size="mini"
              @click="showDistributDialog(scope.row.orderId,scope.row.distribute,scope.row.weight)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加订单弹出来的对话框-->
    <el-dialog title="添加订单" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="托运方" prop="shipper">
          <el-input v-model="addForm.shipper"></el-input>
        </el-form-item>
        <el-form-item label="运费支付方" prop="freightPayer">
          <el-input v-model="addForm.freightPayer"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="addForm.contact"></el-input>
        </el-form-item>
        <el-form-item label="装货地址" prop="loadingAddress">
          <el-input v-model="addForm.loadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="卸货地址" prop="unloadingAddress">
          <el-input v-model="addForm.unloadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="公里数" prop="distance">
          <el-input v-model="addForm.distance"></el-input>
        </el-form-item>
        <el-form-item label="总重量" prop="weight">
          <el-input v-model="addForm.weight"></el-input>
        </el-form-item>
        <el-form-item label="计费方式" prop="chargeMode">
          <el-input v-model="addForm.chargeMode"></el-input>
        </el-form-item>
        <el-form-item label="结算方式" prop="clearingForm">
          <el-input v-model="addForm.clearingForm"></el-input>
        </el-form-item>
        <el-form-item label="运费" prop="freight">
          <el-input v-model="addForm.freight"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addOrder()">确 定</el-button>
      </span>
    </el-dialog>

    <!--修改订单弹出来的对话框-->
    <el-dialog title="修改订单" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!--form表单-->
      <el-form :model="editForm" :rules="addFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="托运方" prop="shipper">
          <el-input v-model="editForm.shipper"></el-input>
        </el-form-item>
        <el-form-item label="运费支付方" prop="freightPayer">
          <el-input v-model="editForm.freightPayer"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="editForm.contact"></el-input>
        </el-form-item>
        <el-form-item label="装货地址" prop="loadingAddress">
          <el-input v-model="editForm.loadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="卸货地址" prop="unloadingAddress">
          <el-input v-model="editForm.unloadingAddress"></el-input>
        </el-form-item>
        <el-form-item label="计费方式" prop="chargeMode">
          <el-input v-model="editForm.chargeMode"></el-input>
        </el-form-item>
        <el-form-item label="结算方式" prop="clearingForm">
          <el-input v-model="editForm.clearingForm"></el-input>
        </el-form-item>
        <el-form-item label="总重量" prop="weight">
          <el-input v-model="editForm.weight"></el-input>
        </el-form-item>
        <el-form-item label="运费" prop="freight">
          <el-input v-model="editForm.freight"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editOrder()">确 定</el-button>
      </span>
    </el-dialog>

    <!--分配方式 对话框-->
    <el-dialog title="分配方式" :visible.sync="distributeDialogVisible" width="50%">
      <div v-for="(item,name,index) in distributePlanList" :key="index">
        <el-table :data="distributePlanList[name].cars" border stripe>
          <el-table-column type="index"></el-table-column>
          <el-table-column label="汽车id" prop="carId"></el-table-column>
          <el-table-column label="容量" prop="capacity"></el-table-column>
          <el-table-column label="每公里损耗" prop="loss"></el-table-column>
        </el-table>
        <span slot="footer" class="dialog-footer">
          <el-button @click="distributeDialogVisible= false">取消</el-button>
          <el-button type="primary" @click="distributeDialogVisible= false">确 定</el-button>
        </span>
      </div>
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
      distributeQuryInfo: {
        id: "",
        weight: "",
        distance: ""
      },
      distributePlanList: [
        {
          cars: [
            {
              carId: "1",
              capacity: "5",
              loss: 50
            },
            {
              carId: "1",
              capacity: "5",
              loss: 50
            }
          ],
          totalCost: 230
        },
        {
          cars: [
            {
              carId: "3",
              capacity: "5",
              loss: 50
            },
            {
              carId: "4",
              capacity: "5",
              loss: 50
            }
          ],
          totalCost: 230
        }
      ],
      userlist: [],
      totol: 0,
      //添加对话框显示
      addDialogVisible: false,
      //编辑对话框显示
      editDialogVisible: false,
      //分配对话框显示
      distributeDialogVisible: false,
      //添加用户表单数据
      addForm: {
        shipper: "",
        freightPayer: "",
        freight: "",
        contact: "",
        loadingAddress: "",
        unloadingAddress: "",
        chargeMode: "",
        cleaningform: "",
        distribute: "",
        weight: ""
      },
      //分配方式数据
      distributeList: [],
      //编辑表单
      editForm: {},
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
        .get("/orderinfo", {
          params: this.queryInfo
        })
        .then(res => {
          _this.userlist = res.data;
        });
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    distributeDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    //显示编辑对话框
    async showEditDialog(id) {
      const _this = this;
      await this.$http.get("/orderinfo/edit/" + id).then(res => {
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    showDistributDialog(id, distance, weight) {
      // const _this=this
      this.distributeDialogVisible = true;
      this.distributeQuryInfo.id = id;
      this.distributeQuryInfo.distance = distance;
      this.distributeQuryInfo.weight = weight;
      this.$http
        .post("/orderinfo/distribute", this.distributeQuryInfo)
        .then(res => {
          console.log(res);
        });
      console.log(id);
    },
    //添加订单对话框
    addOrder() {
      const _this = this;
      this.$http.post("/orderinfo/addorderinfo", this.addForm).then(res => {
        if (res.data == "成功") {
          _this.$message.success("添加成功");
          _this.addDialogVisible = false;
          // 重新获取表数据
          _this.getUserList();
        } else {
          _this.$message.error("添加失败");
        }
      });
    },
    // 编辑订单确认
    editOrder() {
      const _this = this;
      this.$http.post("/orderinfo/editinfo", this.editForm).then(res => {
        if (res.data == "成功") {
          console.log(res.data);
          _this.$message.success("修改成功");
          _this.editDialogVisible = false;
          _this.getUserList();
        } else {
          console.log(res.data);
          _this.$message.error("添加失败");
        }
      });
    },
    //删除订单
    removeOrderById(id) {
      const _this = this;
      this.$confirm("此操作将永久删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete("orderinfo/delete/" + id).then(res => {
            if (res.data == "成功") {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              _this.getUserList();
            } else {
              this.$message({
                type: "info",
                message: "删除失败"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  }
};
</script>

<style lang="less" scoped>
</style>