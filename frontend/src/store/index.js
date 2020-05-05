import Vue from 'vue'
import Vuex from 'vuex'
import ClinicModules from "./ClinicModules";
import ClinicAdministratorModules from "./ClinicAdministratorModules";
import ClinicRoomModules from "./ClinicRoomModules";
import DoctorModules from "./DoctorModules";
import AuthModule from "./AuthModule";
import MedicinesModules from "./MedicinesModules";

Vue.use(Vuex);

export default new Vuex.Store({
  namespaced: true,
  modules: {
    clinics: ClinicModules,
    clinicAdmins: ClinicAdministratorModules,
    clinicRooms: ClinicRoomModules,
    medicines : MedicinesModules,
    doctor: DoctorModules,
    auth: AuthModule
  }
})
