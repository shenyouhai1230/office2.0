<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="需求编号" prop="xqid">
        <el-input
          v-model="queryParams.xqid"
          placeholder="请输入需求编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务名称" prop="taskname">
        <el-input
          v-model="queryParams.taskname"
          placeholder="请输入任务名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="begintime">
        <el-input
          v-model="queryParams.begintime"
          placeholder="请输入开始时间"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="endtime">
        <el-input
          v-model="queryParams.endtime"
          placeholder="请输入结束时间"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务人员" prop="ywname">
        <el-input
          v-model="queryParams.ywname"
          placeholder="请输入业务人员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="后台开发" prop="hdname">
        <el-input
          v-model="queryParams.hdname"
          placeholder="请输入后台开发"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="前台开发" prop="qdname">
        <el-input
          v-model="queryParams.qdname"
          placeholder="请输入前台开发"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="state">
        <el-select
          v-model="queryParams.state"
          placeholder="请选择任务状态"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in stateOptions"
            :key="dict.dictValue"
            :label="dict.dictLable"
            :value="dict.dictValue"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="xqquestion">
        <el-input
          v-model="queryParams.xqquestion"
          placeholder="备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规模估算状态" prop="kfdays">
        <el-select v-model="queryParams.kfdays" placeholder="请选择规模估算状态" clearable size="small">
          <el-option
            v-for="dict in kfdaysOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="性能测试状态" prop="usedmanhours">
        <el-select v-model="queryParams.usedmanhours" placeholder="请选择性能测试状态" clearable size="small">
          <el-option
            v-for="dict in usedmanhoursOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="质验文档状态" prop="totalmanhours">
        <el-select v-model="queryParams.totalmanhours" placeholder="请选择质验文档状态" clearable size="small">
          <el-option
            v-for="dict in totalmanhoursOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['jobtask:taskdetailed:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['jobtask:taskdetailed:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['jobtask:taskdetailed:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['jobtask:taskdetailed:export']"
          >导出</el-button
        >
      </el-col>
       <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:user:import']"
          >需求导入</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="taskdetailedList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="需求编号"
        align="center"
        prop="xqid"
        width="140"
      />
      <el-table-column
        label="任务名称"
        align="left"
        prop="taskname"
        width="220"
      />
      <el-table-column label="开始时间" align="center" prop="begintime" />
      <el-table-column label="结束时间" align="center" prop="endtime" />
      <el-table-column label="业务人员" align="center" prop="ywname" />
      <el-table-column label="后台开发" align="center" prop="hdname" />
      <el-table-column label="前台开发" align="center" prop="qdname" />
      <el-table-column label="状态" align="center" prop="state" />
      <el-table-column
        label="组员"
        align="center"
        width="220"
      >
      <template slot-scope="scope">
       <el-select v-model="scope.row.jkquestion" multiple filterable placeholder="" disabled>
                        <el-option 
                            v-for="item in userOptions"
                            :key="item.userId"
                            :label="item.nickName"
                            :value="item.userId"
                        >
                        </el-option>
        </el-select>
      </template>
      </el-table-column>
      <el-table-column label="规模估算状态" align="center" prop="kfdays" :formatter="kfdaysFormat" />
      <el-table-column label="性能测试状态" align="center" prop="usedmanhours" :formatter="usedmanhoursFormat" />
      <el-table-column label="质验文档状态" align="center" prop="totalmanhours" :formatter="totalmanhoursFormat" />
       <el-table-column
        label="备注"
        align="left"
        prop="xqquestion"
        width="220"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="120"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['jobtask:taskdetailed:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jobtask:taskdetailed:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改开发任务表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="需求编号" prop="xqid">
          <el-input
            v-model="form.xqid"
            placeholder="请输入需求编号"
            v-if="title != '添加开发任务表'"
            v-hasRole="['admin']"
          />
          <el-input
            v-model="form.xqid"
            placeholder="请输入需求编号"
            v-else
            ref="addinput"
          />
        </el-form-item>
        <el-form-item label="任务名称" prop="taskname">
          <el-input v-model="form.taskname" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="开始时间" prop="begintime">
          <el-input v-model="form.begintime" placeholder="请输入开始时间" />
        </el-form-item>
        <el-form-item label="结束时间" prop="endtime">
          <el-input v-model="form.endtime" placeholder="请输入结束时间" />
        </el-form-item>
        <el-form-item label="业务人员" prop="ywname">
          <el-input v-model="form.ywname" placeholder="请输入业务人员" />
        </el-form-item>
        <el-form-item label="后台开发" prop="hdname">
          <el-input v-model="form.hdname" placeholder="请输入后台开发" />
        </el-form-item>
        <el-form-item label="前台开发" prop="qdname">
          <el-input
            v-model="form.qdname"
            placeholder="请输入前台开发"
            v-if="title != '添加开发任务表'"
            v-hasRole="['admin']"
          />
          <el-input
            v-model="form.qdname"
            placeholder="请输入前台开发"
            v-else
            ref="addinput1"
          />
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择任务状态">
            <el-option
              v-for="dict in stateOptions"
              :key="dict.dictValue"
              :label="dict.dictLable"
              :value="dict.dictValue"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="组员" prop="jkquestion">
           <el-select v-model="form.jkquestion" multiple filterable placeholder="请选择组员" >
                        <el-option 
                            v-for="item in userOptions"
                            :key="item.userId"
                            :label="item.nickName"
                            :value="item.userId"
                        >
                        </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="规模估算状态" prop="kfdays">
          <el-select v-model="form.kfdays" placeholder="请选择规模估算状态">
            <el-option
              v-for="dict in kfdaysOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性能测试状态" prop="usedmanhours">
          <el-select v-model="form.usedmanhours" placeholder="请选择性能测试状态">
            <el-option
              v-for="dict in usedmanhoursOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="质验文档状态" prop="totalmanhours">
          <el-select v-model="form.totalmanhours" placeholder="请选择质验文档状态">
            <el-option
              v-for="dict in totalmanhoursOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="xqquestion">
          <el-input v-model="form.xqquestion" placeholder="备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 需求导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload ref="upload" :limit="1" accept=".xlsx, .xls" :headers="upload.headers" :action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading" :on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :auto-upload="false" drag>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的需求数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTaskdetailed,
  getTaskdetailed,
  delTaskdetailed,
  addTaskdetailed,
  updateTaskdetailed,
  exportTaskdetailed,
} from "@/api/jobtask/taskdetailed";
import { getToken } from "@/utils/auth";
import {
  listUser
} from "@/api/system/user";
export default {
  name: "Taskdetailed",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 开发任务表表格数据
      taskdetailedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 规模估算状态字典
      kfdaysOptions: [],
      // 性能测试状态字典
      usedmanhoursOptions: [],
      // 质验文档状态字典
      totalmanhoursOptions: [],

      stateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xqid: null,
        bgid: null,
        taskname: null,
        begintime: null,
        endtime: null,
        ywname: null,
        hdname: null,
        qdname: null,
        state: null,
        xqquestion: null,
        jkquestion: null,
        csquestion: null,
        kfdays: null,
        usedmanhours: null,
        totalmanhours: null

      },
        // 查询参数
      userQueryParams: {
        pageNum: 1,
        pageSize: 100,
        userName: undefined,
        phonenumber: undefined,
        status: '0',
        deptId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskname: [
          { required: true, message: "任务名称不能为空", trigger: "blur" },
        ],
        xqid: [{ required: true, message: "需求不能为空", trigger: "blur" }],
        qdname: [
          { required: true, message: "前台人员不能为空", trigger: "blur" },
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" },
        ],
        kfdays: [
          { required: true, message: "规模估算状态不能为空", trigger: "change" }
        ],
        usedmanhours: [
          { required: true, message: "性能测试状态不能为空", trigger: "change" }
        ],
        totalmanhours: [
          { required: true, message: "质验文档状态不能为空", trigger: "change" }
        ]

      },
       // 需求导入参数
      upload: {
        // 是否显示弹出层（需求导入）
        open: false,
        // 弹出层标题（需求导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData",
      },
      userOptions: [],
    };
  },
  created() {
    this.getList();
    this.getDicts("zjnx_job_status").then((response) => {
      this.stateOptions = response.data;
    });
    this.getUserList()
  },
  methods: {
     /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "需求导入";
      this.upload.open = true;
    },
    /** 查询开发任务表列表 */
    getList() {
      this.loading = true;
      listTaskdetailed(this.queryParams).then((response) => {
        response.rows.forEach(element => {
          if(element['jkquestion']){
            element['jkquestion'] = element['jkquestion'].split(',');
          }
        });
        this.taskdetailedList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
      /** 查询用户列表 */
    getUserList() {
      listUser(this.userQueryParams).then(
        (response) => {
          response.rows.forEach(element => {
          if(element['userId']){
            element['userId'] = element['userId']+'';
          }
        });
          this.userOptions = response.rows;
        }
      );
    },
    stateFormat(row, column) {
      return this.selectDictLable(this.stateOptions, row.state);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        xqid: null,
        bgid: null,
        taskname: null,
        begintime: null,
        endtime: null,
        ywname: null,
        hdname: null,
        qdname: null,
        state: null,
        xqquestion: null,
        jkquestion: null,
        csquestion: null,
        updateTime: null,
        isdelay: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.xqid);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.title = "添加开发任务表";
      this.open = true;
      this.$nextTick(() => {
        let el = this.$refs.addinput.$el;
        el.classList.remove("is-disabled");
        el.children[0].readOnly = false;
        let el1 = this.$refs.addinput1.$el;
        el1.classList.remove("is-disabled");
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const xqid = row.xqid || this.ids;
      getTaskdetailed(xqid).then((response) => {
        response.data.jkquestion = response.data.jkquestion.split(',')
        this.form = response.data;
        this.open = true;
        this.title = "修改开发任务表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.title != "添加开发任务表") {
             this.form.jkquestion = this.form.jkquestion.toString()
            updateTaskdetailed(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
             this.form.jkquestion = this.form.jkquestion.toString()
            addTaskdetailed(this.form).then((response) => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const xqids = row.xqid || this.ids;
      this.$confirm(
        '是否确认删除开发任务表编号为"' + xqids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delTaskdetailed(xqids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有开发任务表数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportTaskdetailed(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>
