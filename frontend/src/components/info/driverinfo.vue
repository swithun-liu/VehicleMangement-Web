<template>
  <div>
    <h3>司机信息组件</h3>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible=true">添加司机</el-button>
        </el-col>
      </el-row>
      <!-- table==>显示用户列表 -->
      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="汽车id" prop="carId"></el-table-column>
        <el-table-column label="司机id" prop="driverId"></el-table-column>
        <el-table-column label="用户名" prop="driverName"></el-table-column>
        <el-table-column label="注册日期" prop="entryDate"></el-table-column>
        <el-table-column label="性别" prop="sex"></el-table-column>
        <el-table-column label="电话" prop="tel"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.driverId)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeDriverById(scope.row.driverId)"
            ></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加司机弹出来的对话框-->
    <el-dialog title="添加司机" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="汽车id" prop="carId">
          <el-input v-model="addForm.carId"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.driverName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="addForm.sex"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="addForm.tel"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="addDriver">确 定</el-button>
      </span>
    </el-dialog>

    <!--修改司机弹出来的对话框-->
    <el-dialog
      title="修改司机信息"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!--form表单-->
      <el-form :model="addForm" :rules="addFormRules" ref="editFormRef" label-width="70px"></el-form>

      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="司机id" prop="driverId">
          <el-input v-model="editForm.driverId"></el-input>
        </el-form-item>
        <el-form-item label="司机姓名" prop="driverName">
          <el-input v-model="editForm.driverName"></el-input>
        </el-form-item>
        <el-form-item label="汽车id" prop="carId">
          <el-input v-model="editForm.carId"></el-input>
        </el-form-item>
        <el-form-item label="注册日期" prop="entryDate">
          <el-input v-model="editForm.entryDate"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="editForm.sex"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="editForm.tel"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible= false">取 消</el-button>
        <el-button type="primary" @click="editDriverInfo()">确 定</el-button>
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
      //修改对话框显示
      editDialogVisible: false,
      //添加用户表单数据
      addForm: {
        carId: "",
        driverName: "",
        sex: "",
        tel: ""
      },
      //编辑信息的表单数据
      editForm: {},
      //添加表单验证规则
      addFormRules: {
        username: [
          {
            required: true,
            message: "请输入",
            tirgger: "blur"
          },
          {
            min: 3,
            max: 10,
            message: "用户名长度3-10",
            tirgger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      const _this = this;
      this.$http
        .get("/driverinfo", {
          params: this.queryInfo
        })
        .then(res => {
          // console.log(res.data)
          _this.userlist = res.data;
        });
    },
    // 关闭 添加用户 对话框是 清空所有填写的数据
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    eidtDialogClosed() {
      this.$refs.eidtFormRef.resetFiles();
    },
    // 显示 编辑 对话框
    async showEditDialog(id) {
      const _this = this;
      await this.$http.get("/driverinfo/edit/" + id).then(res => {
        _this.editForm = res.data;
      });
      this.editDialogVisible = true;
    },
    addDriver() {
      const _this = this;
      this.$http.post("/driverinfo/adduser", this.addForm).then(res => {
        if (res.data == "成功") {
          // console.log(res.data)
          _this.$message.success("添加成功");
          _this.addDialogVisible = false;
          //重新获取用户列表数据
          _this.getUserList();
        } else {
          _this.$message.error("添加失败");
        }
      });
    },
    //编辑用户确认
    editDriverInfo() {
      const _this = this;
      this.$http.post("/driverinfo/editinfo", this.editForm).then(res => {
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
    //删除司机
    removeDriverById(id) {
      const _this=this
      this.$confirm("此操作将永久删除该司机, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete("driverinfo/delete/" + id).then(res => {
            if (res.data == "成功") {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              _this.getUserList()
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