<template>
  <div class="app-container">

    <!--<div style="padding-bottom: 50px;max-height: 400px;">-->
      <!--<el-input v-model="title" style="width: 300px;"></el-input>-->
      <!--<el-button @click="loadMovieDesc">loadDesc</el-button>-->
      <!--<el-button @click="loadShortComments">loadComments</el-button>-->
      <!--<el-button @click="handleTest">Test</el-button>-->
      <!--<el-button type="info" icon="el-icon-star-off"></el-button>-->
      <!--<el-button type="info" icon="el-icon-star-on"></el-button>-->
    <!--</div>-->

    <el-card class="box-card-component" style="margin-top: 20px;margin-bottom: 50px">
      <div slot="header" class="box-card-header">
        <span style="font-size: 36px;padding-top: 5px;padding-bottom: 5px">{{movie.title}}</span>
        <div style="float: right;margin-right: 20px"><el-button :type="collectedBtnType" :icon="collectedBtnIcon" @click="handleCollected"></el-button></div>
      </div>

      <div>
        <div style="float: left;padding-right: 40px;min-height: 400px;height: auto !important;height: 400px">
          <img :src="movie.pic_url" height="350px">
        </div>

        <div style="margin-right: 20px">
          <div class="movie-desc">类别：{{movie.category}}</div>
          <div class="movie-desc">上映年份：{{movie.year}}</div>
          <div class="movie-desc">国家：{{movie.country}}</div>
          <div class="movie-desc">导演：{{movie.directors}}</div>

          <div class="movie-desc">主演：{{trimStarsList(movie.stars)}}</div>

          <div class="movie-desc">上映日期：{{movie.releaseDate}}</div>
          <div class="movie-desc">时长：{{movie.running_time}}</div>
          <div class="movie-desc">格言：{{movie.quote}}</div>
          <br/>

          <div class="movie-desc">豆瓣排名：{{movie.rank}}</div>
          <div class="movie-desc">豆瓣评分：{{movie.score}}</div>
          <div class="movie-desc">豆瓣投票数：{{movie.votes}}</div>
          <div class="movie-desc">豆瓣链接：{{movie.douban_url}}</div>
        </div>

      </div>

      <!--<el-container>-->
      <!--<el-aside>-->
      <!--<img src="https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg" height="250px">-->
      <!--</el-aside>-->

      <!--<el-container>-->
      <!--<el-main>-->
      <!--<span>豆瓣排名：{{movie.rank}}</span>-->
      <!--<span>豆瓣评分：{{movie.score}}</span>-->
      <!--<span>豆瓣链接：{{movie.douban_url}}</span>-->
      <!--<span>豆瓣投票数：{{movie.votes}}</span>-->

      <!--<span>类别：{{movie.category}}</span>-->
      <!--<span>上映年份：{{movie.year}}</span>-->
      <!--<span>国家：{{movie.country}}</span>-->
      <!--<span>格言：{{movie.quote}}</span>-->
      <!--<span>导演：{{movie.directors}}</span>-->
      <!--<span>主演：{{movie.stars}}</span>-->
      <!--<span>上映日期：{{movie.releaseDate}}</span>-->
      <!--<span>时长：{{movie.running_time}}</span>-->
      <!--&lt;!&ndash;<span>：{{movie.pic_url}}</span>&ndash;&gt;-->
      <!--</el-main>-->
      <!--</el-container>-->
      <!--</el-container>-->

    </el-card>

    <!--<el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px">-->
    <!--<div slot="header" class="box-card-header">-->
    <!--<span>豆瓣短评</span>-->
    <!--</div>-->
    <!--<li v-for="comment in shortComments">-->
    <!--{{comment}}-->
    <!--</li>-->
    <!--<div>-->

    <!--</div>-->
    <!--</el-card>-->

    <el-card class="box-card-component" style="margin-top: 50px;margin-bottom: 50px;background-color: #263445;">
      <div slot="header">
        <span style="font-size: 32px;color: #FFFFFF;padding-top: 5px;padding-bottom: 5px">豆瓣短评</span>
      </div>

      <el-card class="box-card-component" style="margin-top: 20px;margin-bottom: 20px;"
               v-for="comment in shortComments">
        <div style="font-size: 16px;line-height:20pt;">
          {{comment}}
        </div>
      </el-card>

    </el-card>

    <!--<div style="float: left;margin-left: 10px;margin-right: 10px;width: 300px;" v-for="comment in shortComments">-->
    <!--<el-card style="min-height: 300px">-->
    <!--<span>-->
    <!--{{comment}}-->
    <!--</span>-->
    <!--</el-card>-->
    <!--</div>-->


  </div>
</template>

<style>
  .movie-desc {
    padding-bottom: 5px;
    padding-top: 5px;
  }
</style>

<script>
  import * as RestAPI from "@/api/RestAPI"

  export default {
    name: "desc",
    props: {
      movie_title: {
        type: String,
        default: "肖申克的救赎 The Shawshank Redemption"
      }
    },
    data() {
      return {
        movie: {
          rank: 0,
          title: "ABC",
          score: 7.5,
          country: "EU",
          year: 1970,
          category: "Learning",
          votes: 1000,
          douban_url: "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg",
          pic_url: "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg",
          quote: "A B C D E F G",
          directors: "English Teacher",
          stars: "ABC",
          releaseDate: "1970-01-01",
          running_time: "100min"
        },
        title: this.movie_title,
        shortComments: [],
        collected: false,
        collectedBtnType: "info",
        collectedBtnIcon: "el-icon-star-off"
      }
    },
    created() {
      this.loadMovieDesc();
      this.loadShortComments();
      this.checkMovieCollected();
    },
    methods: {
      loadMovieDesc() {
        var _this = this;
        var data = {
          title: _this.title
        };
        RestAPI.getMovieDesc(data).then(function (response) {
          if (response.data.msg = "ok") {
            _this.movie = response.data.data;
          } else {
            console.log("error");
            console.log(response);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      loadShortComments() {
        var _this = this;
        var data = {
          title: _this.title
        };
        RestAPI.getShortComments(data).then(function (response) {
          if (response.data.msg = "ok") {
            _this.shortComments = response.data.data;
            console.log(_this.shortComments);
          } else {
            console.log("error");
            console.log(response);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      trimStarsList(stars) {
        var maxLen = 80;
        if (stars.length > maxLen) {
          // stars = stars.substr(0, maxLen)+"......";
          return stars.substr(0, stars.indexOf(",", maxLen)) + "......";
        } else
          return stars;
      },
      handleCollected() {
        if (this.collectedBtnType === "info") {
          this.addFavoriteMovie();
        } else {
          this.removeFavoriteMovie();
        }
      },
      addFavoriteMovie(){
        this.collectedBtnType = "danger";
        this.collectedBtnIcon = "el-icon-star-on";

        var data ={
          username: this.$store.getters.username,
          title: this.title
        };
        console.log(data);
        RestAPI.addCollectedMovie(data).then(function (response) {
          if(response.data.msg == "ok"){
            console.log("collected!");
          }
        });

      },
      removeFavoriteMovie(){
        this.collectedBtnType = "info";
        this.collectedBtnIcon = "el-icon-star-off";

        var data ={
          username: this.$store.getters.username,
          title: this.title
        };
        RestAPI.removeCollectedMovie(data).then(function (response) {
          if(response.data.msg == "ok"){
            console.log("remove collected!");
          }
        });

      },
      checkMovieCollected(){
        if(this.$store.getters.username === ""){
          console.log("null username");
          this.$store.commit("SET_USERNAME", "admin");
          console.log("set username: " + this.$store.getters.username);
        }
        var data ={
          username: this.$store.getters.username,
          title: this.title
        };
        var _this = this;
        RestAPI.checkMovieCollected(data).then(function (response) {
          if(response.data.msg == "ok"){
            console.log("collected!");
            _this.collectedBtnType = "danger";
            _this.collectedBtnIcon = "el-icon-star-on";
          }else{
            _this.collectedBtnType = "info";
            __this.collectedBtnIcon = "el-icon-star-off";
          }
        });
      },
      handleTest() {
        var name = this.$store.getters.username;
        var roles = this.$store.getters.roles;
        console.log("name: " + name);
        console.log("role: " + roles);
      }
    }
  }
</script>

<style scoped>

</style>
