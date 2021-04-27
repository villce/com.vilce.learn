<template>
  <div class="heart">
    <span style="float: right;font-size: 20px;color: #000000;margin-top: 35px">{{days}}天</span>
  </div>
</template>

<script>
  export default {
    name: "Heart",
    data() {
      return {
        days: 0
      }
    },
    mounted() {
      this.dateDiffer();
    },
    methods: {
      dateDiffer(){
        var date_start = "2019-03-01"
        //date1结束时间
        let date1 = new Date(date_start);
        //date2当前时间
        let date2 = new Date();
        date1 = new Date(date1.getFullYear(), date1.getMonth(), date1.getDate());
        date2 = new Date(date2.getFullYear(), date2.getMonth(), date2.getDate());
        const diff = date2.getTime() - date1.getTime(); //目标时间减去当前时间
        const diffDate = diff / (24 * 60 * 60 * 1000);  //计算当前时间与结束时间之间相差天数
        this.days = diffDate;
      }
    }
  }
</script>

<style scoped>
  body {
    /*background: url("images/1.jpg") no-repeat;*/
    /*background-size: cover;*/
  }

  .heart {
    width: 200px;
    height: 200px;
    background-color: red;
    position: relative;
    margin: 100px auto;
    animation: size 1s infinite;
    animation-direction: alternate;
  }

  .heart::before,
  .heart::after {
    content: "";
    width: 200px;
    height: 200px;
    background-color: red;
    position: absolute;
    border-radius: 50%;
  }

  .heart::before {
    left: -90px;
  }

  .heart::after {
    left: 0px;
    top: -90px;
  }

  @keyframes size {
    0% {
      transform: scale(0.6) rotate(45deg);
    }

    100% {
      transform: scale(1) rotate(45deg);
    }
  }
</style>
