<template>
  <div class="heart">
  </div>
</template>

<script>
  export default {
    name: "Heart",
    data() {
      return {
        days: 0
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
