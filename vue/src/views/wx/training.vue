<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="trainingName" label="活动名称" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="集训时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.trainingDate}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="trainingContent" label="集训内容" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="打卡记录" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showCheckLog(scope.$index)">查看打卡记录</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempTraining" label-position="left" label-width="80px" size="mini"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="集训名称">
          <el-input type="text" v-model="tempTraining.trainingName">
          </el-input>
        </el-form-item>
        <el-form-item label="集训内容">
          <el-input type="text" v-model="tempTraining.trainingContent">
          </el-input>
        </el-form-item>
        <el-form-item label="集训时间">
          <el-date-picker v-model="tempTraining.trainingDate" type="date" placeholder="选择日期"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签到开始时间">
          <el-date-picker v-model="tempTraining.checkBeginTime" type="datetime" placeholder="签到开始时间"
                          format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="签到结束时间">
          <el-date-picker v-model="tempTraining.checkEndTime" type="datetime" placeholder="签到结束时间"
                          format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="集训地点">
          <el-input type="text" v-model="tempTraining.longitude" placeholder="经度" readonly="true">
          </el-input>
          <el-input type="text" v-model="tempTraining.latitude" placeholder="纬度" readonly="true">
          </el-input>
          <qq-map @location="updateAddress"></qq-map>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createTc">创 建</el-button>
        <el-button type="primary" v-else @click="updateTc">修 改</el-button>
      </div>
    </el-dialog>
    <!-- 签到记录 -->
    <el-dialog title="签到记录" :visible.sync="logDialogVisible" width="60%">
      <el-table :data="logList" v-loading.body="logListLoading" element-loading-text="拼命加载中" border fit
                highlight-current-row>
        <el-table-column align="center" label="序号" width="80">
          <template slot-scope="scope">
            <span v-text="getLogIndex(scope.$index)"> </span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="wxUser.nickName" label="名称" style="width: 60px;"></el-table-column>
        <el-table-column align="center" prop="checkTime" label="签到时间" style="width: 60px;"></el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="logListQuery.pageNum"
        :page-size="logListQuery.pageRow"
        :total="logTotalCount"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </el-dialog>
  </div>
</template>
<script>
  import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item.vue";
  import QqMap from "../layout/components/QQMap";
  export default {
    components: {
      ElFormItem,
      QqMap
    },
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '创建集训'
        },
        tempTraining: {
          id: "",
          trainingName:"",
          trainingContent: "",
          trainingDate:"",
          latitude:"",
          longitude:"",
          checkBeginTime:"",
          checkEndTime:"",
          version:""
        },
        logDialogVisible:false,
        logList: [],
        logListLoading: false,
        logTotalCount:0,
        logListQuery: {
          pageNum: 1,
          pageRow: 10,
          name: ''
        },

      }
    },
    created() {
      this.getList();
    },
    mounted: {

    },
    methods: {
      getList() {
        this.listLoading = true;
        this.api({
          url: "/sys/trainingCheck/",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempTraining.id = "";
        this.tempTraining.trainingName = "";
        this.tempTraining.trainingDate = "";
        this.tempTraining.trainingContent = "";
        this.tempTraining.longitude = "";
        this.tempTraining.latitude = "";
        this.tempTraining.checkBeginTime = "";
        this.tempTraining.checkEndTime = "";
        this.tempTraining.version = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempTraining.id = this.list[$index].id;
        this.tempTraining.trainingName = this.list[$index].trainingName;
        this.tempTraining.trainingDate = this.list[$index].trainingDate;
        this.tempTraining.trainingContent = this.list[$index].trainingContent;
        this.tempTraining.longitude = this.list[$index].longitude;
        this.tempTraining.latitude = this.list[$index].latitude;
        this.tempTraining.checkBeginTime = this.list[$index].checkBeginTime;
        this.tempTraining.checkEndTime = this.list[$index].checkEndTime;
        this.tempTraining.version = this.list[$index].version;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createTc() {
        //保存
        this.api({
          url: "/sys/trainingCheck/addTc",
          method: "post",
          data: this.tempTraining
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateTc() {
        //修改
        this.api({
          url: "/sys/trainingCheck/updateTc",
          method: "post",
          data: this.tempTraining
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateAddress(data){
        console.log(data)
        this.tempTraining.latitude = data.latlng.lat
        this.tempTraining.longitude = data.latlng.lng
      },
      showCheckLog($index){
        this.logListQuery.trainingCheckId = this.list[$index].id;
        this.logListLoading = true;
        this.api({
          url: "/sys/trainingCheck/byTcId",
          method: "get",
          params: this.logListQuery
        }).then(data => {
          this.logListLoading = false;
          this.logList = data.list;
          this.logTotalCount = data.totalCount;
        })
        this.logDialogVisible = true
      },
      getLogIndex($index) {
        //表格序号
        return (this.logListQuery.pageNum - 1) * this.logListQuery.pageRow + $index + 1
      },
    }
  }
</script>
<style>
  #container {
    min-width:600px;
    min-height:767px;
  }
  .qqmap { width: 700px;
    height: 600px;
  }
</style>
