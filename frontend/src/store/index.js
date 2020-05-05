import Vue from 'vue'
import Vuex from 'vuex'
import ClinicModules from "./ClinicModules";
import ClinicAdministratorModules from "./ClinicAdministratorModules";
import ClinicRoomModules from "./ClinicRoomModules";
import DoctorModules from "./DoctorModules";
import AuthModule from "./AuthModule";

Vue.use(Vuex);

export default new Vuex.Store({
  namespaced: true,
  modules: {
    clinics: ClinicModules,
    clinicAdmins: ClinicAdministratorModules,
    clinicRooms: ClinicRoomModules,
    doctor: DoctorModules,
    auth: AuthModule
  }
})
