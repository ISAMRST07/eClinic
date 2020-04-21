<template>
    <router-view />
</template>

<script>

import Main from "./views/Main";

export default {
  name: 'App',

  components: {
      Main,
  },

  data: () => ({
    //
  }),
};
</script>

<style>
    ::-webkit-scrollbar {
        width: 0 !important;
        background: transparent !important;
        overflow: hidden !important;
    }
</style>
