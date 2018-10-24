<template>
  <div class="app-container">

    <!--<el-row>-->
    <!--<el-button type="primary" @click="getCollectedMovies">load</el-button>-->
    <!--</el-row>-->

    <el-card v-for="movie in collectedMovies" class="box-card-component">
      <div slot="header" class="box-card-header">
        <router-link :to="'/movies/desc/'+movie.title"><img :src="movie.pic_url"></router-link>
      </div>
      <div style="position: relative;line-height: 16pt">
        <b>{{movie.title}}</b>
      </div>
    </el-card>
  </div>
</template>

<script>
  import * as RestAPI from "../../api/RestAPI";

  export default {
    name: "collectedList",
    data() {
      return {
        collectedMovies: null
      }
    },
    created() {
      this.getCollectedMovies();
    },
    methods: {
      getCollectedMovies() {
        if (this.$store.getters.username === "") {
          console.log("null username");
          this.$store.commit("SET_USERNAME", "admin");
          console.log("set username: " + this.$store.getters.username);
        }
        var data = {
          username: this.$store.getters.username,
        };
        var _this = this;
        RestAPI.getCollectedMovies(data).then(function (response) {
          if (response.data.msg == "ok") {
            _this.collectedMovies = response.data.data;
            console.log(_this.collectedMovies);
          }
        });
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .box-card-component {
    float: left;
    width: 200px;
    margin: 20px 10px 20px 10px;
    max-height: 500px;
  }
</style>
<style rel="stylesheet/scss" lang="scss">
  .box-card-component {
    .el-card__header {
      padding: 0px !important;
    }
  }
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
  .box-card-component {
    .box-card-header {
      position: relative;
      height: 250px;
      img {
        width: 100%;
        height: 100%;
        transition: all 0.2s linear;
        &:hover {
          transform: scale(1.1, 1.1);
          filter: contrast(130%);
        }
      }
    }
  }
</style>

