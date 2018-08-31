<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>

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
      <el-table-column align="center" prop="nickName" label="昵称" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="nickName" label="总公里" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="创建时间" width="170">
        <template slot-scope="scope">
          <span>{{scope.row.createdDateTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('article:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">查看打卡记录</el-button>
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
    <el-dialog title="用户打卡记录" :visible.sync="dialogFormVisible" width="60%">
      <el-table :data="runDataList" v-loading.body="runDataListLoading" element-loading-text="拼命加载中" border fit
                highlight-current-row>
        <el-table-column align="center" label="序号" width="80">
          <template slot-scope="scope">
            <span v-text="getRunDataIndex(scope.$index)"> </span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="totalMile" label="公里数" style="width: 60px;"></el-table-column>
        <el-table-column align="center" prop="speed" label="配速" style="width: 60px;"></el-table-column>
        <el-table-column align="center" prop="timeCost" label="用时" style="width: 60px;"></el-table-column>
        <el-table-column align="center" label="图片" style="width: 60px;">
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="top">
              <img :src="scope.row.imageData">
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">图片</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column align="center" label="打卡时间" width="100">
          <template slot-scope="scope">
            <span>{{scope.row.runningDateTime}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="管理" width="200" v-if="hasPerm('article:update')">
          <template slot-scope="scope">
            <el-button v-if="scope.row.status==0" type="primary" icon="edit" @click="updateRunData(scope.row.id,1)">通过</el-button>
            <el-button v-if="scope.row.status==0" type="danger" icon="edit" @click="updateRunData(scope.row.id,-1)">不通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="runDataListQuery.pageNum"
        :page-size="runDataListQuery.pageRow"
        :total="runDataTotalCount"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </el-dialog>
  </div>
</template>
<script>
  export default {
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
        runDataList: [],
        runDataListLoading:false,
        runDataTotalCount: 0,
        runDataListQuery:{
          pageNum: 1,//页码
          pageRow: 10,//每页条数
          name: ''
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('article:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/sys/wxUser/",
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
      showUpdate($index) {
        this.runDataListQuery.wxUserId = this.list[$index].id;
        this.runDataListQuery.groupId = 1;
        //显示修改对话框
        this.runDataListLoading = true;
        this.api({
          url: "/sys/runningData/",
          method: "get",
          params: this.runDataListQuery
        }).then(data => {
          this.runDataListLoading = false;
          this.runDataList = data.list;
          this.runDataTotalCount = data.totalCount;
        })
        this.dialogFormVisible = true
      },
      getRunDataIndex($index) {
        //表格序号
        return (this.runDataListQuery.pageNum - 1) * this.runDataListQuery.pageRow + $index + 1
      },
      updateRunData(id,status){
        this.api({
          url: "/sys/runningData/check",
          method: "post",
          params: {runDataId:id,status}
        }).then(data => {
          this.dialogFormVisible = false;
        })
      }
    }
  }
</script>
