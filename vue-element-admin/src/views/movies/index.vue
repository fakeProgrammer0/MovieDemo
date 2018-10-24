<template>
  <div class="app-container">
    <!--<el-row>-->
      <!--<el-button type="primary" @click="getTotalCnt">load</el-button>-->
    <!--</el-row>-->

    <el-table :data="tableData" border fit style="width: 100%">
      <el-table-column type="index" :index="indexMethod" align="center"></el-table-column>
      <el-table-column prop="rank" label="豆瓣排名" width="100" align="center"></el-table-column>

      <!--<el-table-column prop="title" label="影片" align="center" min-width="200">-->
      <el-table-column label="影片" align="center" min-width="200">
        <template slot-scope="scope">
          <!--<span class="link-type" @click="handleClickTitle(scope.row.title)">{{scope.row.title}}</span>-->
          <router-link :to="'/movies/desc/'+scope.row.title"><span class="link-type">{{scope.row.title}}</span></router-link>
        </template>
      </el-table-column>

      <el-table-column prop="category" label="类别" align="center" min-width="100"></el-table-column>
      <el-table-column prop="score" label="豆瓣评分" width="100" align="center"></el-table-column>
      <el-table-column prop="year" label="上映年份" width="100" align="center"></el-table-column>
      <el-table-column prop="country" label="国家" align="center"></el-table-column>
    </el-table>

    <el-pagination class="pagination-container"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCnt">
    </el-pagination>

  </div>
</template>

<script>
  import * as RestAPI from '@/api/RestAPI'
  import * as RestUrl from '@/api/RestUrl'

  export default {
    name: "index",
    data() {
      return {
        tableData: null,
        // rankBase: 1,
        pageSize: 20,
        currentPage: 1,
        totalCnt: 400
      }
    },
    created() {
      this.loadMoviesInfo();
      this.getTotalCnt();
    },
    methods: {
      indexMethod(index) {
        return index + 1;
      },
      loadMoviesInfo() {
        var _this = this;
        var data = {
          "rankBase": 1 + (_this.currentPage - 1) * _this.pageSize,
          "size": _this.pageSize
        };
        RestAPI.getMoivesInfo(data).then(function (response) {
          console.log("requesting moives");
          if (response.data.msg = "ok") {
            console.log("receive ok!");
            _this.tableData = response.data.data;
          } else {
            console.log("error");
            console.log(response);
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      handleSizeChange(val){
        this.pageSize = val;
        this.loadMoviesInfo();
      },
      handleCurrentChange(val){
        this.currentPage = val;
        this.loadMoviesInfo();
      },
      getTotalCnt(){
        var _this = this;
        RestAPI.getMoviesCnt(_this.totalCnt).then(function (response) {
          console.log("requesting moives cnt");
          if (response.data.msg = "ok") {
            console.log("receive ok!");
            _this.totalCnt = response.data.data;
          } else {
            console.log("error");
            console.log(response);
          }
        }).catch(function (error) {
          console.log(error);
        });
      }
    }
  }
</script>

<style scoped>

</style>
