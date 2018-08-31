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
      <el-table-column align="center" prop="ranking"label="排名" width="80"></el-table-column>
      <el-table-column align="center" prop="wxUser.nickName" label="姓名" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="avgSpeed" label="平均速度" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="totalMlie" label="总公里数" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="totalIntegral" label="积分" style="width: 60px;"></el-table-column>
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
          name: '',
          runGroupId:1
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
          url: "/sys/runningData/ranking",
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
      }
    }
  }
</script>
