import Vue from 'vue'
import Vuex from 'vuex'
import ClinicModules from "./ClinicModules";

Vue.use(Vuex);

export default new Vuex.Store({
  namespaced: true,
  modules: {
    clinics: ClinicModules
  }
})
