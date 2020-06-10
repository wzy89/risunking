<template>
  <div style="display:flex;justify-content:space-between;align-items:center;width:100%;height:100%;">
    <div style="font-size: 17px;font-weight: 600;">
      <img style="width:44px;height:44px;margin-left:22px;margin-right:10px;" :src="iconImg" alt>信息发布系统
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      iconImg: require("../../assets/logo.jpeg")
    }
  },
  created() {},
  methods: {
    handleCommand(command) {
      if (command === "logout") {
        this.$store
          .dispatch("FedLogOut")
          .then(() => {
            this.loading = false;
            this.$router.push({ path: "/login" });
          })
          .catch(err => {
            _this.loading = false;
            _this.$message.error(err);
          })
      }
    }
  }
}
</script>