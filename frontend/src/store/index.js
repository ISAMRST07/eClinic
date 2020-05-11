import Vue from 'vue'
import Vuex from 'vuex'
import ClinicModules from "./ClinicModules";
import ClinicAdministratorModules from "./ClinicAdministratorModules";
import ClinicRoomModules from "./ClinicRoomModules";
import DoctorModules from "./DoctorModules";
import NurseModules from "./NurseModules";
import InterventionTypeModule from "./InterventionTypeModule";
import AuthModule from "./AuthModule";
import MedicinesModules from "./MedicinesModules";
import DiseaseModules from "./DiseaseModules";
import UnregisteredUsersModule from "./UnregisteredUsersModules";

Vue.use(Vuex);

export default new Vuex.Store({
  namespaced: true,
  modules: {
    clinics: ClinicModules,
    clinicAdmins: ClinicAdministratorModules,
    clinicRooms: ClinicRoomModules,
    medicines : MedicinesModules,
    doctor: DoctorModules,
    nurse: NurseModules,
    auth: AuthModule,
    disease: DiseaseModules,
    unregisteredUsers: UnregisteredUsersModule,
    interventionType : InterventionTypeModule
  }
})
